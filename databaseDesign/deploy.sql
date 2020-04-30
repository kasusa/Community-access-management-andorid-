CREATE TABLE `citizen` (

`citizenID` varchar(20) CHARACTER SET utf8 NOT NULL,

`citizen_name` varchar(255) CHARACTER SET utf8 NULL,

`citizen_gender` varchar(255) CHARACTER SET utf8 NULL,

PRIMARY KEY (`citizenID`) 

);



CREATE TABLE `xiaoqu` (

`xiaoquID` bigint(20) NOT NULL AUTO_INCREMENT,

`xiaoqu_name` varchar(255) NULL,

`xiaoqu_area_id` bigint(20) NULL,

`xiaoqu_management_id` bigint(20) NULL COMMENT '小区的管理部门',

PRIMARY KEY (`xiaoquID`) 

);



CREATE TABLE `worker` (

`worker_citizenid` varchar(20) NOT NULL,

`worker_management` bigint(20) NULL COMMENT '所属单位',

`worker_position` varchar(255) NULL COMMENT '职位/职责',

PRIMARY KEY (`worker_citizenid`) 

);



CREATE TABLE `link` (

`citizenid` varchar(20) NOT NULL,

`xiaoquid` bigint(20) NOT NULL,

`buliding` varchar(20) NULL COMMENT '建筑号/楼号 如 7-20',

`unit` varchar(20) NULL COMMENT '单元号 如 1 . 2 . 3 或者 甲 / 乙',

`room` varchar(20) NULL,

`register_time` date NOT NULL COMMENT '表示这个人在这个时间标记\"我生活在这个小区\" 以为小区有可能更换'

);



CREATE TABLE `mangement` (

`managementID` bigint(20) NOT NULL AUTO_INCREMENT,

`management_name` varchar(255) NULL,

`management_area_id` bigint(20) NULL,

`management_detail` text NULL,

PRIMARY KEY (`managementID`) 

);



CREATE TABLE `area` (

`areaID` bigint(20) NOT NULL AUTO_INCREMENT,

`area_name` varchar(255) NULL,

`area_city` varchar(255) NULL,

`area_province` varchar(255) NULL,

PRIMARY KEY (`areaID`) 

);



CREATE TABLE `in_and_out` (

`citizen_id` varchar(20) NOT NULL,

`xiaoqu_id` bigint(20) NOT NULL,

`in` tinyint(1) NULL DEFAULT 0,

`out` tinyint(1) NULL DEFAULT 0,

`action_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP

);



CREATE TABLE `user` (

`user_citizenID` varchar(20) NOT NULL,

`user_email` varchar(255) NULL,

`user_phone` varchar(30) NULL,

`user_pwd` varchar(255) NULL,

`user_avtarlink` text NULL,

`user_note` varchar(255) NULL,

PRIMARY KEY (`user_citizenID`) 

);





ALTER TABLE `xiaoqu` ADD CONSTRAINT `fk_xiaoqu_area_1` FOREIGN KEY (`xiaoqu_area_id`) REFERENCES `area` (`areaID`);

ALTER TABLE `xiaoqu` ADD CONSTRAINT `fk_xiaoqu_mangement_1` FOREIGN KEY (`xiaoqu_management_id`) REFERENCES `mangement` (`managementID`);

ALTER TABLE `worker` ADD CONSTRAINT `fk_worker_citizen_1` FOREIGN KEY (`worker_citizenid`) REFERENCES `citizen` (`citizenID`);

ALTER TABLE `worker` ADD CONSTRAINT `fk_worker_mangement_1` FOREIGN KEY (`worker_management`) REFERENCES `mangement` (`managementID`);

ALTER TABLE `in_and_out` ADD CONSTRAINT `fk_in_and_out_xiaoqu_1` FOREIGN KEY (`xiaoqu_id`) REFERENCES `xiaoqu` (`xiaoquID`);

ALTER TABLE `in_and_out` ADD CONSTRAINT `fk_in_and_out_citizen_1` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`citizenID`);

ALTER TABLE `user` ADD CONSTRAINT `fk_user_citizen_1` FOREIGN KEY (`user_citizenID`) REFERENCES `citizen` (`citizenID`);

ALTER TABLE `link` ADD CONSTRAINT `fk_link_citizen_1` FOREIGN KEY (`citizenid`) REFERENCES `citizen` (`citizenID`);

ALTER TABLE `link` ADD CONSTRAINT `fk_link_xiaoqu_1` FOREIGN KEY (`xiaoquid`) REFERENCES `xiaoqu` (`xiaoquID`);

ALTER TABLE `mangement` ADD CONSTRAINT `fk_mangement_area_1` FOREIGN KEY (`management_area_id`) REFERENCES `area` (`areaID`);



