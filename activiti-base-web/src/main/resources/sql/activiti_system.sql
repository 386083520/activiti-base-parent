# Host: localhost  (Version 5.7.16)
# Date: 2020-12-01 08:01:25
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "sys_dept"
#

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(56) COLLATE utf8mb4_bin NOT NULL COMMENT '部门id',
  `pid` varchar(56) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上级部门id',
  `parent_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上级部门名称',
  `name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `dept_code` varchar(36) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门编码',
  `dept_phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话',
  `dept_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `like_id` varchar(255) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '上级部门id及自己的id',
  `order_num` int(11) DEFAULT NULL COMMENT '序号',
  `manager` varchar(56) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门经理',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

#
# Data for table "sys_dept"
#

INSERT INTO `sys_dept` VALUES ('1000001162817166','1000001251633881','销售部门','销售2','','','','0',0,NULL),('1000001251633881','1000001776185099','秘咖科技有限公司','销售部门',NULL,NULL,NULL,'0,10000017761850991000001251633881',NULL,NULL),('1000001341234088','1000001776185099','秘咖网络科技有限公司','人才管理部1','RCGL','','','0,1000001776185099',0,NULL),('1000001620535597','1000001776185099','秘咖网络科技有限公司','软件研发部',NULL,NULL,NULL,'0,10000017761850991000001620535597',NULL,NULL),('1000001776185099','0','顶级部门','秘咖网络科技有限公司',NULL,NULL,NULL,'0,1000001776185099',NULL,NULL);

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父权限 ID (0为顶级菜单)',
  `label` varchar(64) NOT NULL COMMENT '权限名称',
  `code` varchar(64) DEFAULT '' COMMENT '授权标识符',
  `path` varchar(255) DEFAULT '' COMMENT '路由地址',
  `name` varchar(255) DEFAULT '' COMMENT '路由名称',
  `url` varchar(255) DEFAULT '' COMMENT '授权路径',
  `order_num` int(11) DEFAULT '0' COMMENT '序号',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '类型(0 目录 1菜单，2按钮)',
  `icon` varchar(200) DEFAULT '' COMMENT '图标',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_home` varchar(5) DEFAULT '0' COMMENT '是否是首页(0不是 1是)',
  `parent_name` varchar(36) DEFAULT '' COMMENT '父级菜单名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission` VALUES (17,0,'系统管理','sys:manage','/system',NULL,NULL,1,0,'el-icon-document',NULL,'2023-08-08 11:11:11','2023-08-09 15:26:28','0','顶级菜单'),(18,17,'用户管理','sys:user','/userList','userList','/system/user/UserList',3,1,'el-icon-s-custom',NULL,'2023-08-08 11:11:11','2023-08-09 15:26:28','0','系统管理'),(20,18,'新增','sys:user:add',NULL,NULL,'',NULL,2,'','新增用户','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(21,18,'修改','sys:user:edit',NULL,NULL,'',NULL,2,'','修改用户','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(22,18,'删除','sys:user:delete',NULL,NULL,'',NULL,2,'','删除用户','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(23,17,'角色管理','sys:role','/roleList','roleList','/system/role/RoleList',4,1,'el-icon-rank',NULL,'2023-08-08 11:11:11','2023-08-09 15:26:28','0','系统管理'),(25,23,'新增','sys:role:add',NULL,NULL,'',NULL,2,'','新增角色','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(26,23,'修改','sys:role:edit',NULL,NULL,'',NULL,2,'','修改角色','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(27,23,'删除','sys:role:delete',NULL,NULL,'',NULL,2,'','删除角色','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(28,17,'权限管理','sys:menu','/menuList','menuList','/system/menu/MenuList',5,1,'el-icon-menu',NULL,'2023-08-08 11:11:11','2023-08-09 15:26:28','0','系统管理'),(30,28,'新增','sys:menu:add',NULL,NULL,'',NULL,2,NULL,'新增权限','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(31,28,'修改','sys:menu:edit',NULL,NULL,'',NULL,2,NULL,'修改权限','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(32,28,'删除','sys:menu:delete',NULL,NULL,'',NULL,2,'','删除权限','2023-08-08 11:11:11','2023-08-09 15:26:28','0',NULL),(33,17,'机构管理','sys:dept','/departmentList','departmentList','/system/department/departmentList',2,1,'el-icon-copy-document','机构管理','2020-04-12 22:58:29','2020-04-08 17:12:19','0','系统管理'),(34,0,'商品管理','sys:goods','/goods','',NULL,2,0,'el-icon-picture',NULL,'2020-04-12 22:49:47','2020-04-12 17:22:03','0','顶级菜单'),(36,34,'分类管理','sys:goodsCategory','/goodCategory','goodCategory','/goods/goodsCategory/goodsCategoryList',1,1,'el-icon-s-data',NULL,'2020-04-12 22:54:32','2020-04-12 17:26:30','0','商品管理'),(37,34,'品牌管理','sys:goodsBrand','/goodsBrand','goodsBrand','/goods/goodsBrand/goodsBrandList',2,1,'el-icon-tickets',NULL,'2020-04-12 17:32:04','2020-04-12 17:32:04','0',NULL),(38,36,'新增','sys:addGoodsCategory','','',NULL,0,2,'',NULL,'2020-04-12 17:33:58','2020-04-12 17:33:58','0',NULL),(39,36,'编辑','sys:editGoodsCategory','','',NULL,1,2,'',NULL,'2020-04-12 17:35:30','2020-04-12 17:35:30','0',NULL),(40,37,'新增','sys:addGoodsBrand','','',NULL,0,2,'',NULL,'2020-04-12 17:36:14','2020-04-12 17:36:14','0',NULL),(41,37,'编辑','sys:editGoodsBrand','','',NULL,1,2,'',NULL,'2020-04-12 17:36:46','2020-04-12 17:36:46','0',NULL),(42,0,'系统工具','sys:systenConfig','/systenConfig','',NULL,3,0,'el-icon-receiving',NULL,'2020-04-12 22:50:03','2020-04-12 17:40:41','0','顶级菜单'),(43,42,'代码生成','sys:systemCode','/systemCode','systemCode','/system/config/code',0,1,'el-icon-files',NULL,'2020-04-16 12:44:42','2020-04-12 17:44:06','0','系统工具'),(46,33,'新增','sys:addDepartment','','',NULL,0,2,'',NULL,'2020-04-12 19:58:48','2020-04-12 19:58:48','0',NULL),(76,33,'编辑','sys:editDept','','',NULL,1,2,'',NULL,'2020-04-12 20:42:20','2020-04-12 20:42:20','0',NULL),(77,42,'接口文档','sys:document','/document','document','/system/config/systemDocument',0,1,'el-icon-s-operation',NULL,'2020-04-13 11:31:45','2020-04-13 11:31:45','0','系统工具'),(78,33,'删除','sys:deleteDept','','','',3,2,'',NULL,'2020-04-18 10:25:55','2020-04-18 10:25:55','0','机构管理'),(79,23,'分配权限','sys:role:assign','','','',0,2,'',NULL,'2020-04-18 10:31:05','2020-04-18 10:31:05','0','角色管理'),(80,18,'分配角色','sys:user:assign','','','',0,2,'',NULL,'2020-04-18 10:50:14','2020-04-18 10:50:14','0','用户管理'),(81,NULL,'新增','add:brandAdd','','','',0,2,'',NULL,'2020-05-11 09:04:49','2020-05-11 09:04:49','0','品牌管理'),(82,NULL,'新增','sys:addCode','','','',0,2,'',NULL,'2020-05-11 09:05:58','2020-05-11 09:05:58','0','代码生成');

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '角色说明',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (9,'超级管理员','超级管理员','2020-05-20 07:51:28','2020-05-20 07:51:28'),(10,'普通管理员','拥有查看权限','2023-08-08 11:11:11','2023-08-08 11:11:11'),(13,'销售管理员','管理销售人员','2020-04-14 12:22:53','2020-04-14 12:22:53'),(14,'财务管理员','管理公司财务','2020-04-14 12:23:10','2020-04-14 12:23:10'),(15,'人才管理员','人才管理员','2020-04-18 09:58:05','2020-04-18 09:58:05'),(19,'超级管理员5555','超级管理员55555','2020-05-20 07:57:04','2020-05-20 07:57:04'),(22,'超级管理员22222','超级管理员22222','2023-08-08 11:11:11','2023-08-08 11:11:11');

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=557 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

#
# Data for table "sys_role_permission"
#

INSERT INTO `sys_role_permission` VALUES (27,14,17),(28,14,18),(29,14,20),(30,14,21),(31,14,22),(32,14,23),(33,14,25),(34,14,26),(35,14,27),(36,14,28),(37,14,30),(38,14,31),(39,14,32),(40,14,33),(41,14,46),(42,14,76),(43,14,34),(44,14,36),(45,14,38),(46,14,39),(47,14,37),(48,14,40),(49,14,41),(50,14,42),(51,14,43),(52,14,77),(144,15,17),(145,15,33),(146,15,76),(147,15,18),(148,15,21),(149,15,22),(150,15,20),(151,15,23),(152,15,26),(153,15,27),(154,15,25),(155,15,28),(156,15,32),(157,15,30),(158,15,31),(217,13,17),(218,13,18),(219,13,20),(220,13,21),(221,13,22),(222,13,80),(223,13,23),(224,13,25),(225,13,26),(226,13,27),(227,13,79),(228,13,28),(229,13,30),(230,13,31),(231,13,32),(232,13,33),(233,13,46),(234,13,76),(235,13,78),(236,13,34),(237,13,36),(238,13,38),(239,13,39),(240,13,37),(241,13,40),(242,13,41),(243,13,42),(244,13,43),(245,13,77),(362,16,17),(363,16,18),(364,16,20),(365,9,17),(366,9,18),(367,9,20),(368,9,21),(369,9,22),(370,9,80),(371,9,23),(372,9,25),(373,9,26),(374,9,27),(375,9,79),(376,9,28),(377,9,30),(378,9,31),(379,9,32),(380,9,33),(381,9,46),(382,9,76),(383,9,78),(384,9,34),(385,9,36),(386,9,38),(387,9,39),(388,9,37),(389,9,40),(390,9,41),(391,9,42),(392,9,43),(393,9,77),(528,10,17),(529,10,18),(530,10,20),(531,10,21),(532,10,22),(533,10,80),(534,10,23),(535,10,25),(536,10,26),(537,10,27),(538,10,79),(539,10,28),(540,10,30),(541,10,31),(542,10,32),(543,10,33),(544,10,46),(545,10,76),(546,10,78),(547,10,34),(548,10,36),(549,10,38),(550,10,39),(551,10,37),(552,10,40),(553,10,41),(554,10,42),(555,10,43),(556,10,77);

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(50) NOT NULL COMMENT '系统登录名',
  `password` varchar(64) NOT NULL COMMENT '密码，加密存储, admin/1234',
  `is_account_non_expired` int(2) DEFAULT '1' COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(2) DEFAULT '1' COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(2) DEFAULT '1' COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(2) DEFAULT '1' COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `dept_id` varchar(56) DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `mobile` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `login_name` varchar(36) DEFAULT NULL COMMENT '姓名',
  `is_admin` varchar(5) DEFAULT '0' COMMENT '1:管理员',
  `sex` varchar(5) DEFAULT NULL COMMENT '0:男 1:女',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (9,'admin','e0fa3b4ce826fa6a250ff8ba8ee26980',1,1,1,1,'张三丰',NULL,NULL,'18888886666','admin@163.com','2023-08-08 11:11:11','2019-12-16 10:25:53',NULL,NULL,NULL,'feb60bc9648dcc69063dc0f7eefc46ee'),(10,'test','$2a$10$rDkPvvAFV8kqwvKJzwlRv.i.q.wz1w1pz0SFsHn/55jNeZFQv/eCm',1,1,1,1,'测试',NULL,NULL,'16888886666','test11@qq.com','2023-08-08 11:11:11','2023-08-08 11:11:11',NULL,'1',NULL,NULL),(43,'lcy123','$2a$10$XDh8KvOfnbL8jnRgP7D.9ulTtZwnfmoD3DlaFvCBwNtAQY5dEYqR6',1,1,1,1,NULL,'1000001251633881','销售部门','888888666',NULL,'2020-05-15 08:15:17','2020-05-15 08:15:17','lcy','0','0',NULL),(44,'1868751','$2a$10$iGA8dvSLpJxffto7qdT49Oq0KJreP8k3SATm0LfYjT0KExjZ8jqpy',1,1,1,1,NULL,'1000001341234088','人才管理部1','18687116223',NULL,'2020-05-16 12:00:35','2020-05-16 12:00:35','测试','0','0',NULL),(46,'cs1','$2a$10$ysUm6SDWk4ugXi7nwclscehkJb.L3o9BqjZ80Z6ALzEOUu7dmezBC',1,1,1,1,NULL,'1000001776185099','秘咖网络科技有限公司','18314358245',NULL,'2020-05-16 13:43:14','2020-05-16 13:43:14','测试1','0','0',NULL),(47,'12377','$2a$10$NJJ3mvCF6L8cgJ6oaZKu0uL1SyYkQu0U.L6kckKLqbjAdKd1qvBie',1,1,1,1,NULL,'1000001162817166','销售2','12377',NULL,'2020-05-21 20:51:02','2020-05-21 20:51:02','test1','0','0',NULL);

#
# Structure for table "sys_user_role"
#

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

#
# Data for table "sys_user_role"
#

INSERT INTO `sys_user_role` VALUES (1,9,9),(2,10,10),(8,36,9),(9,38,15),(10,43,15),(11,45,10),(17,46,14);
