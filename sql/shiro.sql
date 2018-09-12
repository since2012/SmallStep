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

 Date: 12/09/2018 20:04:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cmd
-- ----------------------------
DROP TABLE IF EXISTS `cmd`;
CREATE TABLE `cmd`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '命令ID',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '命令',
  `detail` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cmd
-- ----------------------------
INSERT INTO `cmd` VALUES (1, '段子', '精彩段子');
INSERT INTO `cmd` VALUES (16, '查看', '查看详情');
INSERT INTO `cmd` VALUES (22, '测试', '命令提示');
INSERT INTO `cmd` VALUES (24, '订购', '订购服务');

-- ----------------------------
-- Table structure for cmd_content
-- ----------------------------
DROP TABLE IF EXISTS `cmd_content`;
CREATE TABLE `cmd_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容ID',
  `cmdid` int(11) DEFAULT NULL COMMENT '命令',
  `content` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `COMMAND_ID`(`cmdid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cmd_content
-- ----------------------------
INSERT INTO `cmd_content` VALUES (26, NULL, 'AACC');
INSERT INTO `cmd_content` VALUES (27, NULL, 'BB');
INSERT INTO `cmd_content` VALUES (28, NULL, 'AA');
INSERT INTO `cmd_content` VALUES (29, NULL, 'BB');
INSERT INTO `cmd_content` VALUES (30, NULL, 'AA');
INSERT INTO `cmd_content` VALUES (31, NULL, 'BB');
INSERT INTO `cmd_content` VALUES (32, NULL, 'AA');
INSERT INTO `cmd_content` VALUES (33, NULL, 'CC');
INSERT INTO `cmd_content` VALUES (39, 16, '查看1');
INSERT INTO `cmd_content` VALUES (40, 16, '查看2');
INSERT INTO `cmd_content` VALUES (41, 16, '查看3');
INSERT INTO `cmd_content` VALUES (42, 16, '查看4');
INSERT INTO `cmd_content` VALUES (43, 1, '段子1');
INSERT INTO `cmd_content` VALUES (44, 1, '段子2');
INSERT INTO `cmd_content` VALUES (45, 1, '段子3');
INSERT INTO `cmd_content` VALUES (46, 22, '测试1');
INSERT INTO `cmd_content` VALUES (47, 22, '测试2');
INSERT INTO `cmd_content` VALUES (50, 24, '套餐1');
INSERT INTO `cmd_content` VALUES (51, 24, '套餐2');
INSERT INTO `cmd_content` VALUES (52, 24, '套餐3');

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill`  (
  `stockid` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `userid` int(11) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效； 0:成功 ；1:已付款； 2:已发货',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`stockid`, `userid`) USING BTREE,
  INDEX `idx_create_time`(`createtime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `primeprice` decimal(20, 2) DEFAULT NULL COMMENT '原价',
  `saleprice` decimal(20, 2) DEFAULT NULL COMMENT '秒杀价',
  `total` int(11) NOT NULL COMMENT '库存数量',
  `begintime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `endtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_start_time`(`begintime`) USING BTREE,
  INDEX `idx_end_time`(`endtime`) USING BTREE,
  INDEX `idx_create_time`(`createtime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (1000, 'iphone6', 10000.00, 1000.00, 100, '2016-01-01 00:00:00', '2020-11-26 00:00:00', '2020-11-26 00:00:00');
INSERT INTO `stock` VALUES (1001, 'ipad', 10000.00, 800.00, 100, '2016-01-01 00:00:00', '2020-11-26 00:00:00', '2020-11-26 00:00:00');
INSERT INTO `stock` VALUES (1002, 'mac book pro', 10000.00, 6600.00, 100, '2020-11-26 00:00:00', '2020-12-04 00:00:00', '2020-11-26 00:00:00');
INSERT INTO `stock` VALUES (1003, 'iMac', 10000.00, 7000.00, 100, '2018-07-06 00:00:00', '2018-08-31 00:00:00', '2020-11-26 00:00:00');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级链',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 1, 0, '[0],', '总公司', '总公司', '基地', NULL);
INSERT INTO `sys_dept` VALUES (25, 2, 24, '[0],[24],', '开发部', '开发部', '负责软件开发', NULL);
INSERT INTO `sys_dept` VALUES (26, 3, 24, '[0],[24],', '运营部', '运营部', '负责业务运营', NULL);
INSERT INTO `sys_dept` VALUES (27, 4, 24, '[0],[24],', '战略部', '战略部', '规划企业战略', NULL);
INSERT INTO `sys_dept` VALUES (28, 5, 24, '[0],[24],', '人力部', '人力资源部', '招聘等工作', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父级',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (50, 0, 0, '性别', NULL, 'sys_sex');
INSERT INTO `sys_dict` VALUES (51, 1, 50, '男', NULL, '1');
INSERT INTO `sys_dict` VALUES (52, 2, 50, '女', NULL, '2');
INSERT INTO `sys_dict` VALUES (53, 0, 0, '状态', NULL, 'sys_state');
INSERT INTO `sys_dict` VALUES (54, 1, 53, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (55, 2, 53, '禁用', NULL, '2');
INSERT INTO `sys_dict` VALUES (56, 0, 0, '账号状态', NULL, 'account_state');
INSERT INTO `sys_dict` VALUES (57, 1, 56, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (58, 2, 56, '冻结', NULL, '2');
INSERT INTO `sys_dict` VALUES (59, 3, 56, '已删除', NULL, '3');
INSERT INTO `sys_dict` VALUES (85, 0, 0, '等级', '测试专用', 'dict_test');
INSERT INTO `sys_dict` VALUES (86, 1, 85, '初级', NULL, 'pri');
INSERT INTO `sys_dict` VALUES (87, 2, 85, '中级', NULL, 'mid');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (154, '退出日志', 1, '2018-09-12 10:54:59', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (155, '登录日志', 1, '2018-09-12 19:50:32', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (156, '退出日志', 1, '2018-09-12 19:52:19', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (157, '登录日志', 1, '2018-09-12 19:53:12', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (158, '退出日志', 1, '2018-09-12 19:55:07', '成功', NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (159, '登录日志', 1, '2018-09-12 19:55:39', '成功', '登录成功', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父菜单编号链',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url地址',
  `levels` int(65) DEFAULT NULL COMMENT '层级',
  `num` int(65) DEFAULT NULL COMMENT '序号',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'power', '权限管理', '0', '[0],', 'fa-user', '#', 1, 2, 1, 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (106, 'mgr', '用户管理', 'power', '[0],[power],', '', '/mgr', 2, 1, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', '添加用户', 'mgr', '[0],[power],[mgr],', '', '/mgr/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', '修改用户', 'mgr', '[0],[power],[mgr],', '', '/mgr/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', '删除用户', 'mgr', '[0],[power],[mgr],', '', '/mgr/delete', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', '重置密码', 'mgr', '[0],[power],[mgr],', '', '/mgr/reset', 3, 7, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', '冻结用户', 'mgr', '[0],[power],[mgr],', '', '/mgr/freeze', 3, 8, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', '解除冻结用户', 'mgr', '[0],[power],[mgr],', '', '/mgr/unfreeze', 3, 9, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', '分配角色', 'mgr', '[0],[power],[mgr],', '', '/mgr/setRole', 3, 11, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (114, 'role', '角色管理', 'power', '[0],[power],', NULL, '/role', 2, 2, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (115, 'role_add', '添加角色', 'role', '[0],[power],[role],', '', '/role/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', '修改角色', 'role', '[0],[power],[role],', '', '/role/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', '删除角色', 'role', '[0],[power],[role],', '', '/role/remove', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', '角色配置权限', 'role', '[0],[power],[role],', '', '/role/setAuthority', 3, 8, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (119, 'menu', '菜单管理', 'power', '[0],[power],', NULL, '/menu', 2, 4, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', '添加菜单', 'menu', '[0],[power],[menu],', '', '/menu/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', '修改菜单', 'menu', '[0],[power],[menu],', '', '/menu/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (122, 'menu_delete', '删除菜单', 'menu', '[0],[power],[menu],', '', '/menu/delete', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'druid监控', 'mon', '[0],[druid],', '', '/druid', 2, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (131, 'dept', '部门管理', 'system', '[0],[system],', '', '/dept', 2, 3, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', '字典管理', 'system', '[0],[system],', '', '/dict', 2, 4, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (133, 'login_log', '登录日志', 'mon', '[0],[login_log],', '', '/login_log', 2, 6, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', '添加部门', 'dept', '[0],[dept],[dept_add],', '', '/dept/add', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', '修改部门', 'dept', '[0],[power],[dept],', NULL, '/dept/update', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', '删除部门', 'dept', '[0],[power],[dept],', NULL, '/dept/delete', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', '添加字典', 'dict', '[0],[power],[dict],', NULL, '/dict/add', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', '修改字典', 'dict', '[0],[power],[dict],', NULL, '/dict/update', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', '删除字典', 'dict', '[0],[power],[dict],', NULL, '/dict/delete', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', '编辑菜单页', 'menu', '[0],[power],[menu],', '', '/menu/menu_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', '菜单列表', 'menu', '[0],[power],[menu],', '', '/menu/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', '修改部门跳转', 'dept', '[0],[power],[dept],', '', '/dept/dept_update', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', '部门列表', 'dept', '[0],[power],[dept],', '', '/dept/list', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', '部门详情', 'dept', '[0],[power],[dept],', '', '/dept/detail', 3, 6, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', '修改菜单跳转', 'dict', '[0],[power],[dict],', '', '/dict/dict_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', '字典列表', 'dict', '[0],[power],[dict],', '', '/dict/list', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', '字典详情', 'dict', '[0],[power],[dict],', '', '/dict/detail', 3, 6, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (160, 'login_log_clear', '清空登录日志', 'login_log', '[0],[login_log],[login_log_clear],', '', '/login_log/clear', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', '登录日志列表', 'login_log', '[0],[login_log],[login_log_list],', '', '/login_log/list', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', '修改角色跳转', 'role', '[0],[power],[role],', '', '/role/role_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', '角色分配权限', 'role', '[0],[power],[role],', '', '/role/role_assign', 3, 7, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', '角色列表', 'role', '[0],[power],[role],', '', '/role/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', '分配角色页面', 'mgr', '[0],[power],[mgr],', '', '/mgr/role_assign', 3, 10, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', '修改用户页面', 'mgr', '[0],[power],[mgr],', '', '/mgr/user_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', '用户列表', 'mgr', '[0],[power],[mgr],', '', '/mgr/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (168, 'mon', '监控管理', '0', '[0],', 'fa-laptop', '#', 1, 5, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (186, 'index', '首页', '0', '[0],', '', '/', 1, 0, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (187, 'to_user_add', '新增用户页面', 'mgr', '[0],[power],[mgr],', '', '/mgr/user_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (188, 'to_menu_add', '新增菜单页', 'menu', '[0],[power],[menu],', '', '/menu/menu_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (189, 'menu_switch', '菜单状态切换', 'menu', '[0],[power],[menu],', '', '/menu/switch', 3, 7, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (190, 'menu_tree', '菜单树', 'menu', '[0],[power],[menu],', '', '/menu/tree', 3, 20, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (191, 'menuTreeByRoleId', '角色配置权限', 'menu', '[0],[power],[menu],', '', '/menu/treeByRoleId/**', 3, 21, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (192, 'mgr_img', '用户获取头像', 'mgr', '[0],[power],[mgr],', '', '/mgr/img/**', 3, 20, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (193, 'mgr_upload', '用户上传头像', 'mgr', '[0],[power],[mgr],', '', '/mgr/upload', 3, 21, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (194, 'system', '系统管理', '0', '[0],', 'fa-dashboard', '#', 1, 3, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (195, 'biz', '业务管理', '0', '[0],', 'fa-bank', '#', 1, 12, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (196, 'cmd', '指令管理', 'biz', '[0],[biz],', '', '/cmd', 2, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (197, 'robot', '自动回复', 'biz', '[0],[biz],', '', '/robot', 2, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (198, 'stock', '库存管理', 'biz', '[0],[biz],', '', '/stock', 2, 14, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (199, 'stock_show', '秒杀列表', 'biz', '[0],[biz],', '', '/stock/show', 2, 13, 1, NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4374 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (3377, 105, 5);
INSERT INTO `sys_relation` VALUES (3378, 106, 5);
INSERT INTO `sys_relation` VALUES (3379, 107, 5);
INSERT INTO `sys_relation` VALUES (3380, 108, 5);
INSERT INTO `sys_relation` VALUES (3381, 109, 5);
INSERT INTO `sys_relation` VALUES (3382, 110, 5);
INSERT INTO `sys_relation` VALUES (3383, 111, 5);
INSERT INTO `sys_relation` VALUES (3384, 112, 5);
INSERT INTO `sys_relation` VALUES (3385, 113, 5);
INSERT INTO `sys_relation` VALUES (3386, 114, 5);
INSERT INTO `sys_relation` VALUES (3387, 115, 5);
INSERT INTO `sys_relation` VALUES (3388, 116, 5);
INSERT INTO `sys_relation` VALUES (3389, 117, 5);
INSERT INTO `sys_relation` VALUES (3390, 118, 5);
INSERT INTO `sys_relation` VALUES (3391, 119, 5);
INSERT INTO `sys_relation` VALUES (3392, 120, 5);
INSERT INTO `sys_relation` VALUES (3393, 121, 5);
INSERT INTO `sys_relation` VALUES (3394, 122, 5);
INSERT INTO `sys_relation` VALUES (3395, 150, 5);
INSERT INTO `sys_relation` VALUES (3396, 151, 5);
INSERT INTO `sys_relation` VALUES (4293, 105, 1);
INSERT INTO `sys_relation` VALUES (4294, 106, 1);
INSERT INTO `sys_relation` VALUES (4295, 107, 1);
INSERT INTO `sys_relation` VALUES (4296, 108, 1);
INSERT INTO `sys_relation` VALUES (4297, 109, 1);
INSERT INTO `sys_relation` VALUES (4298, 110, 1);
INSERT INTO `sys_relation` VALUES (4299, 111, 1);
INSERT INTO `sys_relation` VALUES (4300, 112, 1);
INSERT INTO `sys_relation` VALUES (4301, 113, 1);
INSERT INTO `sys_relation` VALUES (4302, 114, 1);
INSERT INTO `sys_relation` VALUES (4303, 115, 1);
INSERT INTO `sys_relation` VALUES (4304, 116, 1);
INSERT INTO `sys_relation` VALUES (4305, 117, 1);
INSERT INTO `sys_relation` VALUES (4306, 118, 1);
INSERT INTO `sys_relation` VALUES (4307, 119, 1);
INSERT INTO `sys_relation` VALUES (4308, 120, 1);
INSERT INTO `sys_relation` VALUES (4309, 121, 1);
INSERT INTO `sys_relation` VALUES (4310, 122, 1);
INSERT INTO `sys_relation` VALUES (4312, 130, 1);
INSERT INTO `sys_relation` VALUES (4313, 131, 1);
INSERT INTO `sys_relation` VALUES (4314, 132, 1);
INSERT INTO `sys_relation` VALUES (4315, 133, 1);
INSERT INTO `sys_relation` VALUES (4317, 135, 1);
INSERT INTO `sys_relation` VALUES (4318, 136, 1);
INSERT INTO `sys_relation` VALUES (4319, 137, 1);
INSERT INTO `sys_relation` VALUES (4320, 138, 1);
INSERT INTO `sys_relation` VALUES (4321, 139, 1);
INSERT INTO `sys_relation` VALUES (4322, 140, 1);
INSERT INTO `sys_relation` VALUES (4330, 150, 1);
INSERT INTO `sys_relation` VALUES (4331, 151, 1);
INSERT INTO `sys_relation` VALUES (4332, 152, 1);
INSERT INTO `sys_relation` VALUES (4333, 153, 1);
INSERT INTO `sys_relation` VALUES (4334, 154, 1);
INSERT INTO `sys_relation` VALUES (4335, 155, 1);
INSERT INTO `sys_relation` VALUES (4336, 156, 1);
INSERT INTO `sys_relation` VALUES (4337, 157, 1);
INSERT INTO `sys_relation` VALUES (4340, 160, 1);
INSERT INTO `sys_relation` VALUES (4341, 161, 1);
INSERT INTO `sys_relation` VALUES (4342, 162, 1);
INSERT INTO `sys_relation` VALUES (4343, 163, 1);
INSERT INTO `sys_relation` VALUES (4344, 164, 1);
INSERT INTO `sys_relation` VALUES (4345, 165, 1);
INSERT INTO `sys_relation` VALUES (4346, 166, 1);
INSERT INTO `sys_relation` VALUES (4347, 167, 1);
INSERT INTO `sys_relation` VALUES (4365, 186, 1);
INSERT INTO `sys_relation` VALUES (4366, 187, 1);
INSERT INTO `sys_relation` VALUES (4367, 188, 1);
INSERT INTO `sys_relation` VALUES (4368, 189, 1);
INSERT INTO `sys_relation` VALUES (4369, 0, 1);
INSERT INTO `sys_relation` VALUES (4370, 168, 6);
INSERT INTO `sys_relation` VALUES (4371, 105, 6);
INSERT INTO `sys_relation` VALUES (4372, 138, 6);
INSERT INTO `sys_relation` VALUES (4373, 139, 6);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '中文名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英文名称',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, 0, '超级管理员', 24, 'administrator', 1);
INSERT INTO `sys_role` VALUES (5, 6, 1, '临时工', 26, 'temp', NULL);
INSERT INTO `sys_role` VALUES (6, 4, 1, '开发工程师', 25, 'dev', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'f8d104e2-6719-4fe0-80a7-5d83c86be27c.jpg', 'admin', '6cd3423f077da4d9465408654b7366ef', '8pgby', 'TC', '2017-05-05 00:00:00', 1, '123@foxmail.com', '18200000000', '1', 24, 1, '2016-01-29 08:49:53', 25);
INSERT INTO `sys_user` VALUES (2, NULL, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'aa', '2018-09-05 00:00:00', 2, 'abc@123.com', '123', '6', 27, 3, '2017-05-16 20:33:37', NULL);
INSERT INTO `sys_user` VALUES (3, NULL, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', 1, '', '', '1', 24, 1, '2017-12-04 22:24:02', NULL);
INSERT INTO `sys_user` VALUES (4, NULL, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', 1, '', '', '1', 28, 1, '2017-12-04 22:24:24', NULL);
INSERT INTO `sys_user` VALUES (5, '', 'tan', '4772c53d942ff8a27e5a88676d7b7cb1', 'nfukw', 'TC', '2018-08-01 00:00:00', 1, 'aa@bb.com', '123', '8', 29, 1, '2018-07-22 17:23:52', NULL);
INSERT INTO `sys_user` VALUES (6, '', 'aa', '864dfe5557f409cd2fc4981cb66d6a0f', 'esy09', 'aa', '2018-09-05 00:00:00', 1, 'aa@bb.com', '123', NULL, 24, 1, '2018-09-01 17:11:32', NULL);

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(in v_seckill_id bigint,in v_phone bigint,
    in v_kill_time timestamp,out r_result int)
BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION;
    insert ignore into seckill
      (stockid,userid,createtime)
      values (v_seckill_id,v_phone,v_kill_time);
    select row_count() into insert_count;
    IF (insert_count = 0) THEN
      ROLLBACK;
      set r_result = -1;
    ELSEIF(insert_count < 0) THEN
      ROLLBACK;
      SET R_RESULT = -2;
    ELSE
      update stock
      set total = total-1
      where id = v_seckill_id
        and endtime > v_kill_time
        and begintime < v_kill_time
        and total > 0;
      select row_count() into insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK;
        set r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK;
        set r_result = -2;
      ELSE
        COMMIT;
        set r_result = 1;
      END IF;
    END IF;
  END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
