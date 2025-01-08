create table if not exists system_users(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    second_name VARCHAR(64) NOT NULL,
    username VARCHAR(64) NOT NULL,
    role VARCHAR(12) NOT NULL,
    password VARCHAR(512) NOT NULL,
    is_account_non_expired BOOLEAN,
    is_account_non_locked BOOLEAN,
    is_credentials_non_expired BOOLEAN,
    mfa BOOLEAN,
    totp_code VARCHAR(256)
);