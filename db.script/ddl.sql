-- 게시판
CREATE TABLE `t_board`(
                          `board_seq` bigint auto_increment,
                          `title` varchar (30),
                          `contents` varchar (30),
                          `name` varchar (30),
                          `reg_date` timestamp,
                          `update_date` timestamp,
                          primary key(board_seq)
);

-- 댓글
CREATE TABLE `t_reply`(
                          `reply_seq` bigint auto_increment,
                          `board_id` bigint,
                          `reply_content` varchar (30),
                          `reply_writer` varchar (30),
                          `reg_date` timestamp,
                          `update_date` timestamp,
                          primary key(reply_seq)
);

-- 파일 관련
CREATE TABLE `t_board_file`(
                             `file_seq` bigint auto_increment,
                             `board_seq` bigint,
                             `org_file_name` varchar (250),
                             `save_file_name` varchar (500),
                             `file_path` varchar(250),
                             `reg_date` timestamp,
                             primary key(file_seq)
);


-- 사용자
CREATE TABLE `t_user`(
                       `user_seq` bigint auto_increment,
                       `user_id` varchar (250),
                       `user_password` varchar (500),
                       `user_name` varchar(250),
                       `user_birth` varchar(50),
                       `user_phone` varchar(250),
                       `user_level` varchar(10),
                       `use_yn` char(1),
                       `reg_date` timestamp,
                       `update_date` timestamp,
                       primary key(user_seq)
);

-- 게시판 메뉴
CREATE TABLE `t_menu`(
                       `menu_seq` bigint auto_increment,
                       `menu_code` varchar(100) not null,
                       `menu_name` varchar (100) not null,
                       `menu_type` varchar(100) not null,
                       `use_yn` char(1),
                       `reg_date` timestamp,
                       `update_date` timestamp,
                       primary key(menu_seq)
);
INSERT INTO t_menu
(menu_code, menu_name, menu_type, use_yn, reg_date, update_date)
VALUES
    ('title', '제목', 'T', 'Y', now(), now());
INSERT INTO t_menu
(menu_code, menu_name, menu_type, use_yn, reg_date, update_date)
VALUES
('contents', '내용', 'T', 'Y', now(), now());
INSERT INTO t_menu
(menu_code, menu_name, menu_type, use_yn, reg_date, update_date)
VALUES
('name', '작성자', 'T', 'Y', now(), now());