/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5
 Source Server Type    : MySQL
 Source Server Version : 50541
 Source Host           : localhost:3306
 Source Schema         : tiktok

 Target Server Type    : MySQL
 Target Server Version : 50541
 File Encoding         : 65001

 Date: 28/02/2020 11:52:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audio
-- ----------------------------
DROP TABLE IF EXISTS `audio`;
CREATE TABLE `audio`  (
  `audio_id` int(9) NOT NULL AUTO_INCREMENT,
  `audio_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audio_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audio_date` datetime NULL DEFAULT NULL,
  `business_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`audio_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `business_id` int(9) NOT NULL AUTO_INCREMENT,
  `business_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_isfreeze` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`business_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, 'wanda', 0);
INSERT INTO `business` VALUES (7, 'test', 0);
INSERT INTO `business` VALUES (8, 'w', 0);

-- ----------------------------
-- Table structure for businessinfo
-- ----------------------------
DROP TABLE IF EXISTS `businessinfo`;
CREATE TABLE `businessinfo`  (
  `business_info_id` int(9) NOT NULL AUTO_INCREMENT,
  `business_info_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_info_legal_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_info_legal_person_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`business_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of businessinfo
-- ----------------------------
INSERT INTO `businessinfo` VALUES (1, '万达', '王健林', '123456789', 1);
INSERT INTO `businessinfo` VALUES (2, NULL, NULL, NULL, 6);
INSERT INTO `businessinfo` VALUES (3, NULL, NULL, NULL, 7);
INSERT INTO `businessinfo` VALUES (4, NULL, NULL, NULL, 8);

-- ----------------------------
-- Table structure for businesspassword
-- ----------------------------
DROP TABLE IF EXISTS `businesspassword`;
CREATE TABLE `businesspassword`  (
  `business_password_id` int(9) NOT NULL AUTO_INCREMENT,
  `business_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_id` int(9) NULL DEFAULT NULL,
  PRIMARY KEY (`business_password_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of businesspassword
-- ----------------------------
INSERT INTO `businesspassword` VALUES (1, '123', 1);
INSERT INTO `businesspassword` VALUES (2, '123', 6);
INSERT INTO `businesspassword` VALUES (3, '123456', 7);
INSERT INTO `businesspassword` VALUES (4, '123', 8);

-- ----------------------------
-- Table structure for deleteaudio
-- ----------------------------
DROP TABLE IF EXISTS `deleteaudio`;
CREATE TABLE `deleteaudio`  (
  `audio_id` int(9) NOT NULL AUTO_INCREMENT,
  `audio_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audio_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audio_date` datetime NULL DEFAULT NULL,
  `business_id` int(9) NULL DEFAULT NULL,
  `delete_audio_date` datetime NULL DEFAULT NULL,
  `delete_type` int(2) NULL DEFAULT NULL,
  `uid` int(9) NULL DEFAULT NULL,
  PRIMARY KEY (`audio_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for deletevideo
-- ----------------------------
DROP TABLE IF EXISTS `deletevideo`;
CREATE TABLE `deletevideo`  (
  `video_id` int(9) NOT NULL,
  `video_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_date` datetime NULL DEFAULT NULL,
  `business_id` int(9) NULL DEFAULT NULL,
  `delete_video_date` datetime NULL DEFAULT NULL,
  `delete_type` int(2) NULL DEFAULT NULL,
  `uid` int(9) NULL DEFAULT NULL,
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of deletevideo
-- ----------------------------
INSERT INTO `deletevideo` VALUES (6, 'gfgdhfgh', '/videos/5992816a8f847483c0f47ce3796fa3a8.mp4', '2020-02-27 18:07:49', 1, '2020-02-27 18:08:18', 0, 1);

-- ----------------------------
-- Table structure for loginpassword
-- ----------------------------
DROP TABLE IF EXISTS `loginpassword`;
CREATE TABLE `loginpassword`  (
  `login_id` int(9) NOT NULL AUTO_INCREMENT,
  `login_user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(9) NOT NULL,
  PRIMARY KEY (`login_id`) USING BTREE,
  INDEX `UID`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of loginpassword
-- ----------------------------
INSERT INTO `loginpassword` VALUES (1, '123', 1);

-- ----------------------------
-- Table structure for systemuser
-- ----------------------------
DROP TABLE IF EXISTS `systemuser`;
CREATE TABLE `systemuser`  (
  `uid` int(9) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of systemuser
-- ----------------------------
INSERT INTO `systemuser` VALUES (1, 'sys');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `user_info_id` int(9) NOT NULL AUTO_INCREMENT,
  `user_info_true_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_info_tel` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_info_sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(9) NULL DEFAULT NULL,
  PRIMARY KEY (`user_info_id`) USING BTREE,
  INDEX `UID`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '张三1', '123456789', '男', 1);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `video_id` int(9) NOT NULL AUTO_INCREMENT,
  `video_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_date` datetime NULL DEFAULT NULL,
  `business_id` int(9) NULL DEFAULT NULL,
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, 'GGGG', '/videos/3e62852815558a84bfeed05abd35a65c.mp4', '2020-02-26 15:06:44', 1);
INSERT INTO `video` VALUES (2, 'AAA', '/videos/4ca53baea33e7f92c7a5b53df2ab54aa.mp4', '2020-02-27 11:48:52', 1);
INSERT INTO `video` VALUES (3, 'GGGG', '/videos/3e62852815558a84bfeed05abd35a65c.mp4', '2020-02-27 11:49:17', 1);
INSERT INTO `video` VALUES (4, '4', '4', '2020-02-27 22:08:25', 1);

-- ----------------------------
-- Procedure structure for moveAudioToRecycleBinProc
-- ----------------------------
DROP PROCEDURE IF EXISTS `moveAudioToRecycleBinProc`;
delimiter ;;
CREATE PROCEDURE `moveAudioToRecycleBinProc`(IN audioId int, IN deleteType int, IN uid INT)
BEGIN
	DECLARE audioTitle VARCHAR(255);
	DECLARE audioSrc VARCHAR(255);
	DECLARE audioDate datetime;
	DECLARE businessId int;
	START TRANSACTION;
	SELECT audio_title, audio_src, audio_date, business_id INTO audioTitle, audioSrc, audioDate, businessId FROM audio WHERE audio_id=audioId;
	INSERT INTO deleteaudio (audio_id, audio_title, audio_src, audio_date, business_id, delete_audio_date, delete_type, uid) VALUES(audioId, audioTitle, audioSrc, audioDate, businessId, SYSDATE(), deleteType, uid);
	DELETE FROM audio WHERE audio_id=audioId;
	COMMIT;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for moveVideoToRecycleBinProc
-- ----------------------------
DROP PROCEDURE IF EXISTS `moveVideoToRecycleBinProc`;
delimiter ;;
CREATE PROCEDURE `moveVideoToRecycleBinProc`(IN videoId int, IN deleteType int, IN uid INT)
BEGIN
	DECLARE videoTitle VARCHAR(255);
	DECLARE videoSrc VARCHAR(255);
	DECLARE videoDate datetime;
	DECLARE businessId int;
	START TRANSACTION;
	SELECT video_title, video_src, video_date, business_id INTO videoTitle, videoSrc, videoDate, businessId FROM video WHERE video_id=videoId;
	INSERT INTO deletevideo (video_id, video_title, video_src, video_date, business_id, delete_video_date, delete_type, uid) VALUES(videoId, videoTitle, videoSrc, videoDate, businessId, SYSDATE(), deleteType, uid);
	DELETE FROM video WHERE video_id=videoId;
	COMMIT;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for recoverAudioFromRecycleBinProc
-- ----------------------------
DROP PROCEDURE IF EXISTS `recoverAudioFromRecycleBinProc`;
delimiter ;;
CREATE PROCEDURE `recoverAudioFromRecycleBinProc`(IN audioId int)
BEGIN
	DECLARE audioTitle VARCHAR(255);
	DECLARE audioSrc VARCHAR(255);
	DECLARE audioDate datetime;
	DECLARE businessId int;
	START TRANSACTION;
	SELECT audio_title, audio_src, audio_date, business_id INTO audioTitle, audioSrc, audioDate, businessId FROM deleteaudio WHERE audio_id=audioId;
	INSERT INTO video (audio_id, audio_title, audio_src, audio_date, business_id) VALUES(audioId, audioTitle, audioSrc, audioDate, businessId);
	DELETE FROM deleteaudio WHERE audio_id=audioId;
	COMMIT;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for recoverVideoFromRecycleBinProc
-- ----------------------------
DROP PROCEDURE IF EXISTS `recoverVideoFromRecycleBinProc`;
delimiter ;;
CREATE PROCEDURE `recoverVideoFromRecycleBinProc`(IN videoId int)
BEGIN
	DECLARE videoTitle VARCHAR(255);
	DECLARE videoSrc VARCHAR(255);
	DECLARE videoDate datetime;
	DECLARE businessId int;
	START TRANSACTION;
	SELECT video_title, video_src, video_date, business_id INTO videoTitle, videoSrc, videoDate, businessId FROM deletevideo WHERE video_id=videoId;
	INSERT INTO video (video_id, video_title, video_src, video_date, business_id) VALUES(videoId, videoTitle, videoSrc, videoDate, businessId);
	DELETE FROM deletevideo WHERE video_id=videoId;
	COMMIT;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
