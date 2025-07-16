CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE email_templates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(50) UNIQUE NOT NULL,                  -- Ex: "account_activation", "reset_password", "cart_abandoned"
    description TEXT NOT NULL,
    subject_template TEXT NOT NULL,
    body_template TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE email_logs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    to_email VARCHAR(150) NOT NULL,
    subject TEXT NOT NULL,
    body TEXT,
    status VARCHAR(20) NOT NULL CHECK (status IN ('SENT', 'FAILED')),
    error_message TEXT,
    template_code VARCHAR(50),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (template_code) REFERENCES email_templates(code) ON DELETE SET NULL
);