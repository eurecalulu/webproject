SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- auto-generated definition
DROP TABLE IF EXISTS `policy_split`;
create table policy_split
(
    id      int auto_increment
        primary key,
    text    text       not null,
    piId    int        null,
    is_item tinyint(1) null,
    constraint policy_split_interpretation_id_fk
        foreign key (piId) references interpretation (id)
);

SET FOREIGN_KEY_CHECKS = 1;