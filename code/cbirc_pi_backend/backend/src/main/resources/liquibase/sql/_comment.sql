SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- auto-generated definition
DROP TABLE IF EXISTS `_comment`;
create table _comment
(
    id                int auto_increment
        primary key,
    comment           varchar(1024) not null,
    user_id           int           not null,
    ref_id            int           null,
    time              datetime      not null,
    interpretation_id int           null,
    body              varchar(255)  null,
    constraint _comment_interpretation_id_fk
        foreign key (interpretation_id) references interpretation (id),
    constraint comment__user_id_fk
        foreign key (user_id) references _user (id)
);

SET FOREIGN_KEY_CHECKS = 1;