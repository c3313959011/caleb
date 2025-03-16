/*
 Navicat Premium Data Transfer

 Source Server         : bbs
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : oauth

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 16/03/2025 15:36:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_audit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_audit`;
CREATE TABLE `tbl_audit`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏ID',
  `pic_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自证图片路径',
  `status` int NULL DEFAULT 0 COMMENT '审核状态',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `captcha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '验证码',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE,
  CONSTRAINT `chk_status` CHECK (`status` in (0,1,2))
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_audit
-- ----------------------------
INSERT INTO `tbl_audit` VALUES (1, '3313959011@qq.com', '1234567890', '1', 1, '测试用', NULL, '12345678901234567890123456789012', NULL, '2025-03-09 16:07:52', '2025-03-08 23:41:58');
INSERT INTO `tbl_audit` VALUES (3, '123456789@qq.com', '1111111111', NULL, 1, NULL, NULL, '11111111112222222222333333333344', NULL, '2025-03-09 20:34:08', '2025-03-08 23:42:36');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES (1, '超级管理员', NULL, '2025-03-09 20:08:05', '2025-03-09 20:07:57');
INSERT INTO `tbl_role` VALUES (2, '普通管理员', NULL, '2025-03-09 20:08:18', '2025-03-09 20:08:18');
INSERT INTO `tbl_role` VALUES (3, '普通用户', NULL, '2025-03-09 20:08:32', '2025-03-09 20:08:32');

-- ----------------------------
-- Table structure for tbl_role_url
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_url`;
CREATE TABLE `tbl_role_url`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'URL',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_role_url
-- ----------------------------
INSERT INTO `tbl_role_url` VALUES (4, '2', '/normal', NULL, '2025-03-11 22:49:31', '2025-03-09 20:39:32');
INSERT INTO `tbl_role_url` VALUES (5, '2', '/admin', NULL, '2025-03-09 20:40:11', '2025-03-09 20:39:49');
INSERT INTO `tbl_role_url` VALUES (6, '3', '/normal', NULL, '2025-03-11 22:49:38', '2025-03-09 20:40:01');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人签名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '游戏ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (4, 'cm', '$2a$10$vZ8k0Slw3lws3yKiLnAJMO89QHEmyVVVr1mSbPTLPb55aISeeiPBa', NULL, '3313959011@qq.com', '1234567890', 3, NULL, '2025-03-09 20:35:35', '2025-03-09 16:07:54');
INSERT INTO `tbl_user` VALUES (5, 'admin', '$2a$10$xAjEKP1r/xXiaGfCxdO9eua6oyM/AEYBLqNqKW/rYkHffg9ZB8Z2G', NULL, '123456789@qq.com', '1111111111', 1, NULL, '2025-03-09 20:35:32', '2025-03-09 20:34:57');

SET FOREIGN_KEY_CHECKS = 1;
