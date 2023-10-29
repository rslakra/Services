CREATE TABLE shipwrecks
(
    id              BIGINT AUTO_INCREMENT,
    name            VARCHAR(32)    NOT NULL,
    description     VARCHAR(1024),
    condition       VARCHAR(32)    NOT NULL,
    depth           INT            NOT NULL,
    latitude        NUMERIC(15, 8) NOT NULL,
    longitude       NUMERIC(15, 8) NOT NULL,
    year_discovered INT            NOT NULL,
    created_on      BIGINT         NOT NULL,
    created_at      DATETIME       NOT NULL,
    created_by      VARCHAR(64)    NOT NULL,
    updated_on      BIGINT         NOT NULL,
    updated_at      DATETIME       NOT NULL,
    updated_by      VARCHAR(64)    NOT NULL
);
