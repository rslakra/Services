-- users
CREATE TABLE IF NOT EXISTS `users`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT,
    `user_name`   VARCHAR(64) NOT NULL,
    `password`    VARCHAR(64) NOT NULL,
    `email`       VARCHAR(64) NOT NULL,
    `first_name`  VARCHAR(64) NOT NULL,
    `middle_name` VARCHAR(64),
    `last_name`   VARCHAR(64) NOT NULL,
    `status`      VARCHAR(8)  NOT NULL,
    `created_on`  BIGINT      NOT NULL,
    `created_at`  DATETIME    NOT NULL,
    `created_by`  varchar(64) NOT NULL,
    `updated_on`  BIGINT      NOT NULL,
    `updated_at`  DATETIME    NOT NULL,
    `updated_by`  VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_USER_NAME` (`user_name`),
    UNIQUE KEY `UK_EMAIL` (`email`)
);

-- roles
CREATE TABLE IF NOT EXISTS `roles`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(8)  NOT NULL,
    `status`     VARCHAR(8)  NOT NULL,
    `created_on` BIGINT      NOT NULL,
    `created_at` DATETIME    NOT NULL,
    `created_by` VARCHAR(64) NOT NULL,
    `updated_on` BIGINT      NOT NULL,
    `updated_at` DATETIME    NOT NULL,
    `updated_by` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_NAME` (`name`)
);

-- users_roles
CREATE TABLE `users_roles`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

-- roles
INSERT INTO `roles` (`name`)
VALUES ('ADMIN');
INSERT INTO `roles` (`name`)
VALUES ('USER');
INSERT INTO `roles` (`name`)
VALUES ('CREATOR');
INSERT INTO `roles` (`name`)
VALUES ('COLLABORATOR');
INSERT INTO `roles` (`name`)
VALUES ('EDITOR');

