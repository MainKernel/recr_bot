create table if not exists candidat(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    phone_number VARCHAR(64) NOT NULL,
    age int NOT NULL,
    profession VARCHAR(128) NOT NULL,
    helth_status VARCHAR(256) NOT NULL,
    recruter VARCHAR(64),
    atempt int,
    chat_id BIGINT,
    comment TEXT,
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);