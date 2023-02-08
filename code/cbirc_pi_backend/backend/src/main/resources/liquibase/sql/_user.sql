/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 39.97.124.144:3306
 Source Schema         : cbirc

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 30/10/2021 19:08:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for _user
-- ----------------------------
DROP TABLE IF EXISTS `_user`;
CREATE TABLE `_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of _user
-- ----------------------------
INSERT INTO `_user` VALUES (0, 'cbirc', '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO `_user` VALUES (1, '办公厅', '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO `_user` VALUES (2, '123', 'gnzLDuqKcGxMNKFokfhOew==');
INSERT INTO `_user` VALUES (3, '真布别天世', 'c+CVvT/eZvdjwlucAacdwA==');
INSERT INTO `_user` VALUES (4, 'fhnb', '4QrcOUm6Wau+VuBX8g+IPg==');
INSERT INTO `_user` VALUES (5, 'cyzcyz3', 'yIN7I/+Kqoot3pFUc84JkQ==');
INSERT INTO `_user` VALUES (6, 'cyznbnb', 'yIN7I/+Kqoot3pFUc84JkQ==');
INSERT INTO `_user` VALUES (7, 'cccaaa', 'yIN7I/+Kqoot3pFUc84JkQ==');
INSERT INTO `_user` VALUES (8, 'test123', 'yIN7I/+Kqoot3pFUc84JkQ==');
INSERT INTO `_user` VALUES (12, 'testuser', '4QrcOUm6Wau+VuBX8g+IPg==');

SET FOREIGN_KEY_CHECKS = 1;
