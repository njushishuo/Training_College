/*
Navicat MySQL Data Transfer

Source Server         : J2EE
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : training_college

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-03-19 13:31:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `card_num` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `balance` int(32) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_card
-- ----------------------------
INSERT INTO `bank_card` VALUES ('1', '存储卡(**** 1684)', '123456', '4480');
INSERT INTO `bank_card` VALUES ('2', '存储卡(**** 7894)', '123456', '100000');
INSERT INTO `bank_card` VALUES ('3', '存储卡(**** 1452)', '123456', '5600');
INSERT INTO `bank_card` VALUES ('4', '存储卡(**** 7825)', '123456', '20080');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `bank_card_id` int(32) unsigned DEFAULT NULL,
  `balance` int(32) DEFAULT '0',
  `score` int(32) DEFAULT '0',
  `level` int(8) DEFAULT '1',
  `status` enum('activated','frozen','disabled','newly') DEFAULT 'newly',
  `last_activated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_bcid` (`bank_card_id`),
  CONSTRAINT `card_bcid` FOREIGN KEY (`bank_card_id`) REFERENCES `bank_card` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', '1', '500', '600', '2', 'disabled', '2014-02-01');
INSERT INTO `card` VALUES ('2', '2', '500', '0', '1', 'frozen', '2016-01-01');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `balance` int(32) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', 'admin', '123', '2750');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '软件工程', '软件工程');
INSERT INTO `course` VALUES ('2', '微积分', '微积分(一层次)');
INSERT INTO `course` VALUES ('3', '线性代数', '线性代数(一层次)');
INSERT INTO `course` VALUES ('4', '离散数学', '离散数学');
INSERT INTO `course` VALUES ('5', '软件需求工程', '需求工程');

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher` (
  `course_id` int(32) unsigned NOT NULL,
  `teacher_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`course_id`,`teacher_id`),
  KEY `course_teacher_tid` (`teacher_id`),
  CONSTRAINT `course_teacher_cid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `course_teacher_tid` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_teacher
-- ----------------------------
INSERT INTO `course_teacher` VALUES ('1', '1');
INSERT INTO `course_teacher` VALUES ('1', '2');
INSERT INTO `course_teacher` VALUES ('2', '3');
INSERT INTO `course_teacher` VALUES ('3', '4');
INSERT INTO `course_teacher` VALUES ('4', '5');
INSERT INTO `course_teacher` VALUES ('5', '6');

-- ----------------------------
-- Table structure for drop_record
-- ----------------------------
DROP TABLE IF EXISTS `drop_record`;
CREATE TABLE `drop_record` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `org_system_id` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `price` int(32) unsigned NOT NULL DEFAULT '0',
  `payment` int(32) unsigned NOT NULL DEFAULT '0',
  `user_type` enum('member','non_member') NOT NULL,
  `pay_method` enum('card','cash') NOT NULL,
  `select_method` enum('reserve','select') NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_checked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `drop_pid` (`project_name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drop_record
-- ----------------------------
INSERT INTO `drop_record` VALUES ('22', '0000001', '计算机科学1班', '侍硕', '500', '250', 'member', 'card', 'reserve', '2017-03-17 09:45:35', '');

-- ----------------------------
-- Table structure for enrollment_record
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_record`;
CREATE TABLE `enrollment_record` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `org_system_id` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `price` int(32) unsigned NOT NULL DEFAULT '0',
  `payment` int(32) unsigned NOT NULL DEFAULT '0',
  `user_type` enum('member','non_member') NOT NULL,
  `pay_method` enum('card','cash') NOT NULL,
  `select_method` enum('reserve','select') NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_checked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `enroll_pid` (`project_name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enrollment_record
-- ----------------------------
INSERT INTO `enrollment_record` VALUES ('38', '0000001', '计算机科学1班', '侍硕', '500', '450', 'member', 'card', 'select', '2017-03-17 09:45:15', '\0');
INSERT INTO `enrollment_record` VALUES ('39', '0000001', '软件工程1班', '侍硕', '2000', '1800', 'member', 'card', 'reserve', '2017-03-17 09:45:42', '\0');
INSERT INTO `enrollment_record` VALUES ('40', '0000002', '计算机科学1班', '侍硕', '500', '450', 'member', 'card', 'select', '2017-03-17 09:45:45', '\0');

-- ----------------------------
-- Table structure for grade_record
-- ----------------------------
DROP TABLE IF EXISTS `grade_record`;
CREATE TABLE `grade_record` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `org_system_id` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `score` int(32) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `grade_cid` (`course_name`),
  KEY `grade_pid` (`project_name`),
  KEY `grade_sid` (`student_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade_record
-- ----------------------------
INSERT INTO `grade_record` VALUES ('1', '0000001', '软件工程1班', '思达', '软件工程', '99', '2017-03-14 23:57:28');
INSERT INTO `grade_record` VALUES ('2', '0000001', '软件工程1班', '思达', '微积分(一层次)', '95', '2017-03-15 00:03:48');
INSERT INTO `grade_record` VALUES ('3', '0000001', '软件工程1班', '侍硕', '软件工程', '91', '2017-03-15 00:03:48');
INSERT INTO `grade_record` VALUES ('4', '0000001', '软件工程1班', '侍硕', '线性代数(一层次)', '59', '2017-03-15 00:03:48');
INSERT INTO `grade_record` VALUES ('5', '0000001', '软件工程1班', '侍硕', '离散数学', '91', '2017-03-15 00:03:48');
INSERT INTO `grade_record` VALUES ('6', '0000001', '软件工程1班', '侍硕', '微积分(一层次)', '95', '2017-03-15 00:03:48');

-- ----------------------------
-- Table structure for new_schedule
-- ----------------------------
DROP TABLE IF EXISTS `new_schedule`;
CREATE TABLE `new_schedule` (
  `project_id` int(32) unsigned NOT NULL,
  `course_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`project_id`,`course_id`),
  KEY `new_schedule_project_cid` (`course_id`),
  CONSTRAINT `new_schedule_project_cid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `new_schedule_project_pid` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_schedule
-- ----------------------------
INSERT INTO `new_schedule` VALUES ('1', '1');
INSERT INTO `new_schedule` VALUES ('2', '1');
INSERT INTO `new_schedule` VALUES ('1', '2');
INSERT INTO `new_schedule` VALUES ('2', '2');
INSERT INTO `new_schedule` VALUES ('3', '2');
INSERT INTO `new_schedule` VALUES ('4', '2');
INSERT INTO `new_schedule` VALUES ('5', '2');
INSERT INTO `new_schedule` VALUES ('6', '2');
INSERT INTO `new_schedule` VALUES ('1', '3');
INSERT INTO `new_schedule` VALUES ('4', '3');
INSERT INTO `new_schedule` VALUES ('5', '3');
INSERT INTO `new_schedule` VALUES ('6', '3');
INSERT INTO `new_schedule` VALUES ('1', '4');
INSERT INTO `new_schedule` VALUES ('5', '4');
INSERT INTO `new_schedule` VALUES ('6', '4');
INSERT INTO `new_schedule` VALUES ('2', '5');

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `balance` int(32) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '001', '123', '30000');
INSERT INTO `organization` VALUES ('2', '002', '123', '50000');

-- ----------------------------
-- Table structure for post_modify_schedule
-- ----------------------------
DROP TABLE IF EXISTS `post_modify_schedule`;
CREATE TABLE `post_modify_schedule` (
  `project_id` int(32) unsigned NOT NULL,
  `course_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`project_id`,`course_id`),
  KEY `new_schedule_project_cid` (`course_id`),
  CONSTRAINT `post_modify_schedule_cid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `post_modify_schedule_pid` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_modify_schedule
-- ----------------------------
INSERT INTO `post_modify_schedule` VALUES ('1', '1');
INSERT INTO `post_modify_schedule` VALUES ('2', '1');
INSERT INTO `post_modify_schedule` VALUES ('4', '1');
INSERT INTO `post_modify_schedule` VALUES ('1', '2');
INSERT INTO `post_modify_schedule` VALUES ('2', '2');
INSERT INTO `post_modify_schedule` VALUES ('5', '2');
INSERT INTO `post_modify_schedule` VALUES ('1', '3');
INSERT INTO `post_modify_schedule` VALUES ('2', '3');
INSERT INTO `post_modify_schedule` VALUES ('5', '3');
INSERT INTO `post_modify_schedule` VALUES ('2', '4');
INSERT INTO `post_modify_schedule` VALUES ('1', '5');
INSERT INTO `post_modify_schedule` VALUES ('4', '5');

-- ----------------------------
-- Table structure for post_project
-- ----------------------------
DROP TABLE IF EXISTS `post_project`;
CREATE TABLE `post_project` (
  `pid` int(32) unsigned NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `total_price` int(32) unsigned NOT NULL,
  PRIMARY KEY (`pid`),
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_project
-- ----------------------------
INSERT INTO `post_project` VALUES ('1', '软件工程1班', '2017-03-02', '2017-07-02', '8000');

-- ----------------------------
-- Table structure for pre_modify_schedule
-- ----------------------------
DROP TABLE IF EXISTS `pre_modify_schedule`;
CREATE TABLE `pre_modify_schedule` (
  `project_id` int(32) unsigned NOT NULL,
  `course_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`project_id`,`course_id`),
  KEY `new_schedule_project_cid` (`course_id`),
  CONSTRAINT `pre_modify_schedule_cid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `pre_modify_schedule_pid` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pre_modify_schedule
-- ----------------------------
INSERT INTO `pre_modify_schedule` VALUES ('1', '1');
INSERT INTO `pre_modify_schedule` VALUES ('2', '1');
INSERT INTO `pre_modify_schedule` VALUES ('1', '2');
INSERT INTO `pre_modify_schedule` VALUES ('2', '2');
INSERT INTO `pre_modify_schedule` VALUES ('4', '2');
INSERT INTO `pre_modify_schedule` VALUES ('5', '2');
INSERT INTO `pre_modify_schedule` VALUES ('6', '2');
INSERT INTO `pre_modify_schedule` VALUES ('1', '3');
INSERT INTO `pre_modify_schedule` VALUES ('2', '3');
INSERT INTO `pre_modify_schedule` VALUES ('4', '3');
INSERT INTO `pre_modify_schedule` VALUES ('5', '3');
INSERT INTO `pre_modify_schedule` VALUES ('6', '3');
INSERT INTO `pre_modify_schedule` VALUES ('2', '4');
INSERT INTO `pre_modify_schedule` VALUES ('6', '4');
INSERT INTO `pre_modify_schedule` VALUES ('1', '5');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `organization_id` int(32) unsigned NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `max_std_cnt` int(32) unsigned NOT NULL,
  `cur_std_cnt` int(32) NOT NULL DEFAULT '0',
  `price` int(32) unsigned NOT NULL DEFAULT '0',
  `add_status` enum('pending','approved','rejected') NOT NULL DEFAULT 'pending',
  `modify_status` enum('pending','approved','rejected') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_og_id` (`organization_id`),
  CONSTRAINT `project_og_id` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '1', '软件工程1班', '2017-03-02', '2017-07-02', '200', '1', '8000', 'approved', 'approved');
INSERT INTO `project` VALUES ('2', '1', '软件工程2班', '2017-04-01', '2017-06-24', '200', '0', '800', 'approved', 'approved');
INSERT INTO `project` VALUES ('3', '1', '数学基地班', '2017-09-01', '2018-07-01', '50', '0', '600', 'rejected', null);
INSERT INTO `project` VALUES ('4', '1', '计算机科学1班', '2017-03-01', '2017-10-01', '100', '1', '500', 'approved', 'rejected');
INSERT INTO `project` VALUES ('5', '2', '计算机科学2班', '2017-03-01', '2017-10-01', '100', '0', '400', 'approved', 'approved');
INSERT INTO `project` VALUES ('6', '1', '数学基地班', '2017-03-01', '2017-10-01', '50', '0', '500', 'approved', null);

-- ----------------------------
-- Table structure for project_student
-- ----------------------------
DROP TABLE IF EXISTS `project_student`;
CREATE TABLE `project_student` (
  `pid` int(32) unsigned NOT NULL,
  `sid` int(32) unsigned NOT NULL,
  PRIMARY KEY (`pid`,`sid`),
  KEY `project_std_sid` (`sid`),
  CONSTRAINT `project_std_pid` FOREIGN KEY (`pid`) REFERENCES `project` (`id`) ON DELETE CASCADE,
  CONSTRAINT `project_std_sid` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_student
-- ----------------------------
INSERT INTO `project_student` VALUES ('1', '1');
INSERT INTO `project_student` VALUES ('4', '1');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(32) unsigned NOT NULL,
  `student_id` int(32) unsigned NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment` int(32) unsigned NOT NULL,
  `is_canceled` bit(1) NOT NULL DEFAULT b'0',
  `is_reserve_checked` bit(1) NOT NULL DEFAULT b'0',
  `is_cancel_checked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `reserve_pid` (`project_id`),
  KEY `reserve_sid` (`student_id`),
  CONSTRAINT `reserve_pid` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `reserve_sid` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES ('23', '1', '1', '2017-03-17 09:45:05', '1800', '', '\0', '\0');
INSERT INTO `reservation` VALUES ('24', '1', '1', '2017-03-17 09:45:42', '1800', '\0', '\0', '\0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `card_id` int(32) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `std_card_id` (`card_id`),
  CONSTRAINT `std_card_id` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'ss14', '123', '小明', 'male', '675342517@qq.com', '18362925532', '1');

-- ----------------------------
-- Table structure for student_bank_card
-- ----------------------------
DROP TABLE IF EXISTS `student_bank_card`;
CREATE TABLE `student_bank_card` (
  `bank_card_id` int(32) unsigned NOT NULL,
  `student_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`bank_card_id`,`student_id`),
  KEY `bankcard_std_sid` (`student_id`),
  CONSTRAINT `bankcard_std_bcid` FOREIGN KEY (`bank_card_id`) REFERENCES `bank_card` (`id`) ON DELETE CASCADE,
  CONSTRAINT `bankcard_std_sid` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_bank_card
-- ----------------------------
INSERT INTO `student_bank_card` VALUES ('1', '1');
INSERT INTO `student_bank_card` VALUES ('2', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '刘钦', null);
INSERT INTO `teacher` VALUES ('2', '邵栋', null);
INSERT INTO `teacher` VALUES ('3', '陆宏', null);
INSERT INTO `teacher` VALUES ('4', '杨东', null);
INSERT INTO `teacher` VALUES ('5', '陈道蓄', null);
INSERT INTO `teacher` VALUES ('6', '丁二玉', null);
