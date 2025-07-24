package com.shopcloud.notification_mservice.application.usecases;

import com.shopcloud.notification_mservice.application.ports.in.EmailMessageListener;
import com.shopcloud.notification_mservice.application.ports.out.EmailLogPersistencePort;
import com.shopcloud.notification_mservice.application.ports.out.EmailSenderPort;
import com.shopcloud.notification_mservice.application.ports.out.EmailTemplatePersistencePort;
import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import com.shopcloud.notification_mservice.domain.models.EmailMessageModel;
import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class EmailService implements EmailMessageListener {
    private final EmailSenderPort emailSenderPort;
    private final EmailLogPersistencePort emailLogPersistencePort;
    private final EmailTemplatePersistencePort emailTemplatePersistencePort;

    @Override
    public void receiveEmailMessage(EmailMessageModel message) {
        String to = message.getTo();
        String templateCode = message.getTemplateCode();

        Optional<EmailTemplateModel> optionalTemplate = emailTemplatePersistencePort.findByCode(templateCode);

        if (optionalTemplate.isEmpty()) {
            emailLogPersistencePort.save(new EmailLogModel(
                    null, to, "[TEMPLATE NOT FOUND]", null,
                    "FAILED", "Template not found: " + templateCode, templateCode, OffsetDateTime.now()
            ));
            return;
        }

        EmailTemplateModel template = optionalTemplate.get();
        String subject = replaceVariables(template.getSubjectTemplate(), message.getVariables());
        String body = replaceVariables(template.getBodyTemplate(), message.getVariables());

        try {
            emailSenderPort.sendEmail(to, subject, body);

            emailLogPersistencePort.save(new EmailLogModel(
                    null, to, subject, body,
                    "SENT", null, templateCode, OffsetDateTime.now()
            ));

        } catch (Exception ex) {
            emailLogPersistencePort.save(new EmailLogModel(
                    null, to, subject, body,
                    "FAILED", ex.getMessage(), templateCode, OffsetDateTime.now()
            ));
        }
    }

    private String replaceVariables(String template, Map<String, String> variables) {
        if (variables == null) return template;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return template;
    }
}
