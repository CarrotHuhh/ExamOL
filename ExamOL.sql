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

 Date: 23/12/2022 23:50:37
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
  PRIMARY KEY (`examId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of exam
-- ----------------------------
BEGIN;
INSERT INTO `exam` (`examId`, `examName`, `paperId`, `userId`) VALUES (1, '历史考试', 1, 10001);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of paper
-- ----------------------------
BEGIN;
INSERT INTO `paper` (`paperId`, `paperName`, `choiceAmount`, `fillingAmount`) VALUES (1, '历史考试', 2, 1);
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
  `answer` varchar(255) DEFAULT NULL COMMENT '答案\n',
  `optionA` varchar(255) DEFAULT NULL COMMENT '选项A',
  `optionB` varchar(255) DEFAULT NULL COMMENT '选项B',
  `optionC` varchar(255) DEFAULT NULL COMMENT '选项C',
  `optionD` varchar(255) DEFAULT NULL COMMENT '选项D',
  PRIMARY KEY (`questionId`) USING BTREE,
  KEY `paperId_FK` (`paperId`),
  CONSTRAINT `paperId_FK` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (1, 0, 1, '中华人民共和国建国于（）年？', '1949', '1948', '1949', '1950', '1951');
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (2, 0, 1, '改革开放开始于（）年？', '1978', '1978', '1979', '1980', '1981');
INSERT INTO `question` (`questionId`, `type`, `paperId`, `content`, `answer`, `optionA`, `optionB`, `optionC`, `optionD`) VALUES (3, 1, 1, '中国历史上第一位皇帝是（）？', '秦始皇', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records` (
  `recordId` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `examId` int DEFAULT NULL,
  `examName` varchar(20) DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of records
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '账号',
  `userName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (10001, '张三', '123');
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
