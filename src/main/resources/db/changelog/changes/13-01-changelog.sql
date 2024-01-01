-- liquibase formatted sql

-- changeset DELL:1668284977246-1
CREATE TABLE IF NOT EXISTS synonyms (
    `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `synonyms` text NOT NULL,
    `created_on` DATETIME,
    `modified_on` DATETIME,
    `deleted` BOOLEAN
);

-- changeset DELL:1668284977246-2
CREATE TABLE IF NOT EXISTS user (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `email_id` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `created_on` DATETIME NULL,
    `modified_on` DATETIME NULL,
    `deleted` BOOLEAN
);