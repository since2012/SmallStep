/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 23/06/2018 12:05:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT '优惠券ID',
  `shop_id` tinyint(4) NOT NULL COMMENT '商家ID',
  `begin_day` date DEFAULT NULL COMMENT '起始时间',
  `end_day` date DEFAULT NULL COMMENT '结束时间',
  `recept_day` date DEFAULT NULL COMMENT '领取时间',
  `actived` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否被领取',
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领取验证码',
  `user_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领取人的电话号码',
  `used` bit(1) DEFAULT NULL COMMENT '1:已使用，0：未使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'shop information table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家名称',
  `enable` tinyint(4) NOT NULL COMMENT '1 启用,0 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'shop information table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '资源 ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称,一般是中文名称(给人看的)',
  `permission` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)',
  `url` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '系统管理', 'admin:*', '/admin/**');
INSERT INTO `sys_resource` VALUES (2, '用户管理', 'user:*', '/admin/user/**');
INSERT INTO `sys_resource` VALUES (3, '用户添加', 'user:tip', '/admin/user/tip');
INSERT INTO `sys_resource` VALUES (4, '用户删除', 'user:tip', '/admin/user/tip');
INSERT INTO `sys_resource` VALUES (5, '用户修改', 'user:tip', '/admin/user/tip');
INSERT INTO `sys_resource` VALUES (6, '用户查询', 'user:list', '/admin/user/list');
INSERT INTO `sys_resource` VALUES (7, '用户资源查询', 'user:resources:*', '/admin/user/resources/*');
INSERT INTO `sys_resource` VALUES (8, '角色管理', 'role:*', '/admin/role/**');
INSERT INTO `sys_resource` VALUES (9, '角色添加', 'role:tip', '/admin/role/tip');
INSERT INTO `sys_resource` VALUES (10, '角色删除', 'role:tip', '/admin/role/tip');
INSERT INTO `sys_resource` VALUES (11, '角色修改', 'role:tip', '/admin/role/tip');
INSERT INTO `sys_resource` VALUES (12, '角色查询', 'role:list', '/admin/role/list');
INSERT INTO `sys_resource` VALUES (13, '角色资源查询', 'role:resources:*', '/admin/role/resources/*');
INSERT INTO `sys_resource` VALUES (14, '资源管理', 'resource:*', '/admin/resource/**');
INSERT INTO `sys_resource` VALUES (15, '资源增加', 'resource:tip', '/admin/resource/tip');
INSERT INTO `sys_resource` VALUES (16, '资源删除', 'resource:tip', '/admin/resource/tip');
INSERT INTO `sys_resource` VALUES (17, '资源修改', 'resource:tip', '/admin/resource/tip');
INSERT INTO `sys_resource` VALUES (18, '资源查询', 'resource:list', '/admin/resource/list');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '角色表 ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色字符串',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'admin');
INSERT INTO `t_role` VALUES (2, '开发工程师', 'dev');
INSERT INTO `t_role` VALUES (3, '测试工程师', 'test');
INSERT INTO `t_role` VALUES (4, '文档工程师', 'doc');
INSERT INTO `t_role` VALUES (5, '设计师', 'design');

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '角色资源关联 ID',
  `role_id` tinyint(4) NOT NULL COMMENT '角色 id',
  `resource_id` tinyint(4) NOT NULL COMMENT '资源 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES (1, 1, 1);
INSERT INTO `t_role_resource` VALUES (2, 2, 3);
INSERT INTO `t_role_resource` VALUES (3, 2, 5);
INSERT INTO `t_role_resource` VALUES (4, 2, 6);
INSERT INTO `t_role_resource` VALUES (5, 2, 7);
INSERT INTO `t_role_resource` VALUES (6, 2, 9);
INSERT INTO `t_role_resource` VALUES (7, 2, 11);
INSERT INTO `t_role_resource` VALUES (8, 2, 12);
INSERT INTO `t_role_resource` VALUES (9, 2, 13);
INSERT INTO `t_role_resource` VALUES (10, 2, 15);
INSERT INTO `t_role_resource` VALUES (11, 2, 17);
INSERT INTO `t_role_resource` VALUES (12, 2, 18);
INSERT INTO `t_role_resource` VALUES (13, 3, 6);
INSERT INTO `t_role_resource` VALUES (14, 3, 7);
INSERT INTO `t_role_resource` VALUES (15, 3, 8);
INSERT INTO `t_role_resource` VALUES (16, 3, 14);
INSERT INTO `t_role_resource` VALUES (17, 4, 6);
INSERT INTO `t_role_resource` VALUES (18, 4, 7);
INSERT INTO `t_role_resource` VALUES (19, 4, 12);
INSERT INTO `t_role_resource` VALUES (20, 4, 18);
INSERT INTO `t_role_resource` VALUES (25, 5, 6);
INSERT INTO `t_role_resource` VALUES (26, 5, 12);
INSERT INTO `t_role_resource` VALUES (27, 5, 18);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `status` tinyint(4) NOT NULL COMMENT '状态:1 启用,2 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '928bfd2577490322a6e19b793691467e', '超级管理员', 1);
INSERT INTO `t_user` VALUES (2, 'dev', 'f51703256a38e6bab3d9410a070c32ea', '开发工程师', 1);
INSERT INTO `t_user` VALUES (3, 'test', 'f51703256a38e6bab3d9410a070c32ea', '测试工程师', 1);
INSERT INTO `t_user` VALUES (4, 'doc', 'f51703256a38e6bab3d9410a070c32ea', '文档工程师', 1);
INSERT INTO `t_user` VALUES (6, 'tan', 'f51703256a38e6bab3d9410a070c32ea', 'frank', 1);
INSERT INTO `t_user` VALUES (8, 'a', '8c4fb7bf681156b52fea93442c7dffc9', 'a', 1);
INSERT INTO `t_user` VALUES (9, '张三', '1232', 'wdw', 1);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表 ID',
  `user_id` tinyint(4) NOT NULL,
  `role_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 2, 2);
INSERT INTO `t_user_role` VALUES (3, 3, 3);
INSERT INTO `t_user_role` VALUES (4, 4, 4);
INSERT INTO `t_user_role` VALUES (13, 6, 5);
INSERT INTO `t_user_role` VALUES (15, 8, 5);

SET FOREIGN_KEY_CHECKS = 1;
