/*
 Navicat Premium Data Transfer

 Source Server         : CarrotHu
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : ExamOL

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 24/12/2022 20:00:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `examId` int NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `examName` varchar(20) DEFAULT NULL COMMENT '考试名称',
  `paperId` int DEFAULT NULL COMMENT '考试试卷编号',
  `userId` int DEFAULT NULL COMMENT '考试用户编号',
  `choiceScore` int DEFAULT '2',
  `fillingScore` int DEFAULT '2',
  PRIMARY KEY (`examId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of exam
-- ----------------------------
BEGIN;
INSERT INTO `exam` (`examId`, `examName`, `paperId`, `userId`, `choiceScore`, `fillingScore`) VALUES (1, '历史考试', 1, 10001, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paperId` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `paperName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '试卷名',
  `choiceAmount` int DEFAULT '0' COMMENT '选择题数目',
  `fillingAmount` int DEFAULT '0' COMMENT '填空题数目',
  PRIMARY KEY (`paperId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of paper
-- ----------------------------
BEGIN;
INSERT INTO `paper` (`paperId`, `paperName`, `choiceAmount`, `fillingAmount`) VALUES (1, '历史考试', 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `questionId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` int NOT NULL COMMENT '类别（0为选择1位填空）',
  `paperId` int NOT NULL COMMENT '属于哪张卷子',
  `content` varchar(255) DEFAULT NULL COMMENT '试题内容\n',
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '答案\n',
  `optionA` varchar(255) DEFAULT NULL COMMENT '选项A',
  `optionB` varchar(255) DEFAULT NULL COMMENT '选项B',
  `optionC` varchar(255) DEFAULT NULL COMMENT '选项C',
  `optionD` varchar(255) DEFAULT NULL COMMENT '选项D',
  PRIMARY KEY (`questionId`) USING BTREE,
  KEY `paperId_FK` (`paperId`),
  CONSTRAINT `paperId_FK` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (1, 0, 1, '中华人民共和国建国于（）年？', '1949', '1948', '1949', '1950', '1951');
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (2, 0, 1, '改革开放开始于（）年？', '1978', '1978', '1979', '1980', '1981');
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (3, 1, 1, '中国历史上第一位皇帝是（）？', '秦始皇', NULL, NULL, NULL, NULL);
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (4, 0, 1, '唐太宗时期，先后进谏200多次的名臣是（）？', '魏征', '房玄龄 ', '魏征', '杜如晦', '长孙无忌 ');
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (5, 1, 1, '世界第八大奇迹是指（）？', '秦始皇陵兵马俑', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `examId` int NOT NULL,
  `userId` int DEFAULT NULL,
  `examName` varchar(20) DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` (`examId`, `userId`, `examName`, `score`) VALUES (1, 10001, '历史考试', 8);
COMMIT;

-- ----------------------------
-- Table structure for Submission
-- ----------------------------
DROP TABLE IF EXISTS `Submission`;
CREATE TABLE `Submission` (
  `submissionId` int unsigned NOT NULL AUTO_INCREMENT,
  `examId` int DEFAULT NULL,
  `questionId` int DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `isGraded` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`submissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of Submission
-- ----------------------------
BEGIN;
INSERT INTO `Submission` (`submissionId`, `examId`, `questionId`, `answer`, `isGraded`, `type`) VALUES (1, 1, 2, '1978', 0, 0);
INSERT INTO `Submission` (`submissionId`, `examId`, `questionId`, `answer`, `isGraded`, `type`) VALUES (2, 1, 5, '秦始皇陵兵马俑', 0, 1);
INSERT INTO `Submission` (`submissionId`, `examId`, `questionId`, `answer`, `isGraded`, `type`) VALUES (3, 1, 1, '1948', 0, 0);
INSERT INTO `Submission` (`submissionId`, `examId`, `questionId`, `answer`, `isGraded`, `type`) VALUES (4, 1, 3, '秦始皇', 0, 1);
INSERT INTO `Submission` (`submissionId`, `examId`, `questionId`, `answer`, `isGraded`, `type`) VALUES (5, 1, 4, '魏征', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '账号',
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`userId`, `username`, `password`) VALUES (10001, '张三', '123');
INSERT INTO `user` (`userId`, `username`, `password`) VALUES (10002, 'user02', '123');
INSERT INTO `user` (`userId`, `username`, `password`) VALUES (10003, 'user03', '123');
INSERT INTO `user` (`userId`, `username`, `password`) VALUES (10004, 'user04', '123');
COMMIT;

-- ----------------------------
-- Triggers structure for table question
-- ----------------------------
DROP TRIGGER IF EXISTS `addQuestionTrigger`;
delimiter ;;
CREATE TRIGGER `addQuestionTrigger` AFTER INSERT ON `question` FOR EACH ROW BEGIN
IF new.type=0  THEN
	UPDATE paper SET choiceAmount=choiceAmount+1 WHERE paper.paperId=new.paperId;
ELSE
	UPDATE paper SET fillingAmount=fillingAmount+1 WHERE paper.paperId=new.paperId;
END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table question
-- ----------------------------
DROP TRIGGER IF EXISTS `deleteQuestionTrigger`;
delimiter ;;
CREATE TRIGGER `deleteQuestionTrigger` AFTER DELETE ON `question` FOR EACH ROW BEGIN
IF old.type=0  THEN
	UPDATE paper SET choiceAmount=choiceAmount-1 WHERE paper.paperId=old.paperId;
ELSE
	UPDATE paper SET fillingAmount=fillingAmount-1 WHERE paper.paperId=old.paperId;
END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
