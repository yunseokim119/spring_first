create table Event
(
    id         bigint auto_increment
        primary key,
    task       varchar(255) not null,
    manager    varchar(100) not null,
    start_time datetime     not null,
    end_time   datetime     not null,
    password   varchar(255) not null,
    created_at datetime     not null,
    updated_at datetime     not null
);

INSERT INTO Event (title, manager, start_time, end_time, password, created_at, updated_at) VALUES
('팀 회의', '김지훈', '2024-08-20 10:00:00', '2024-08-20 12:00:00', 'password123', NOW(), NOW());

INSERT INTO Event (title, manager, start_time, end_time, password, created_at, updated_at) VALUES
('프로젝트 발표', '이수진', '2024-08-21 14:00:00', '2024-08-21 15:30:00', 'project2024', NOW(), NOW());
