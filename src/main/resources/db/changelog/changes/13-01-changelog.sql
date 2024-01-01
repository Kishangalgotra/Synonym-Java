-- liquibase formatted sql

-- changeset DELL:1668284977246-1
create schema IF NOT EXISTS synonyms;

-- changeset DELL:1668284977246-2
CREATE TABLE IF NOT EXISTS synonyms (
    `id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `synonym_id` NVARCHAR(100) NOT NULL,
    `synonyms` text NOT NULL,
    `user_id` int(10) DEFAULT NULL,
    `created_on` DATETIME,
    `modified_on` DATETIME,
    `deleted` BOOLEAN
)   ENGINE = InnoDB
    DEFAULT CHARSET = 'UTF8';

-- changeset DELL:1668284977246-3
CREATE TABLE IF NOT EXISTS user (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `email_id` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    `created_on` DATETIME NULL,
    `modified_on` DATETIME NULL,
    `deleted` BOOLEAN
)   ENGINE = InnoDB
    DEFAULT CHARSET = 'UTF8';

-- changeset DELL:1668284977246-4
CREATE TABLE IF NOT EXISTS authorities (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    `authority` VARCHAR(255) NOT NULL,
    `created_on` DATETIME NULL,
    `modified_on` DATETIME NULL,
    `deleted` BOOLEAN
    )   ENGINE = InnoDB
    DEFAULT CHARSET = 'UTF8';