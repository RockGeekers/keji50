SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 账户信息表
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
	`id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
	`phone` varchar(11) COMMENT '手机号',
	`email` varchar(100) COMMENT '邮箱',
	`password` varchar(32) NOT NULL COMMENT 'MD5 32位密码',
	`nickname` varchar(50) COMMENT '昵称',
	`realname` varchar(20) COMMENT '真实姓名',
	`image` varchar(100) COMMENT '头像',
	`qq`    varchar(20)  COMMENT 'QQ号码',
	`weibo` varchar(50)  COMMENT '微博账号',
	`wechat` varchar(50) COMMENT '微信账号',
	`status` varchar(1)  NOT NULL DEFAULT 's' COMMENT '用户状态   s:激活， d:删除',
	`create_by` varchar(64) DEFAULT 'system' COMMENT '创建人',
  	`create_time` datetime    COMMENT '创建时间',
  	`update_by` varchar(64) DEFAULT 'system' COMMENT '修改人',
  	`update_time` datetime    COMMENT '修改时间',
	PRIMARY KEY (`id`),
    KEY `account_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户信息表';

-- ----------------------------
-- 手机邮箱认证信息表
-- ----------------------------
DROP TABLE IF EXISTS `account_validate`;
CREATE TABLE `account_validate` (
	`id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  	`type` varchar(2) DEFAULT '0' COMMENT '0: 手机号绑定 ， 1: 邮箱绑定',
  	`validate_username` varchar(100) NOT NULL COMMENT '验证用户名',
  	`validate_code` varchar(32) NOT NULL COMMENT '验证码',
  	`validate_expire` datetime NOT NULL COMMENT '验证内容有效时间',
  	`ip` varchar(32) COMMENT '客户ip地址',
  	`create_by` varchar(64) DEFAULT 'system' COMMENT '创建人',
  	`create_time` datetime    COMMENT '创建时间',
  	`update_by` varchar(64) DEFAULT 'system' COMMENT '修改人',
  	`update_time` datetime    COMMENT '修改时间',
  	PRIMARY KEY (`id`),
  	KEY `account_validate_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客户短信邮箱认证表';
-- ----------------------------
-- 资讯类别信息表
-- ----------------------------
DROP TABLE IF EXISTS `info_category`;
CREATE TABLE `info_category` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '资讯类别代码',
  `name` varchar(64) NOT NULL COMMENT '资讯类别名称',
  `color_code` varchar(7) DEFAULT '#66B3FF' COMMENT '类别颜色代码',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  `parent` int(11) DEFAULT 0 COMMENT '父类别id',
  PRIMARY KEY (`id`),
  KEY `info_category_id` (`id`),
  UNIQUE KEY `info_category_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯类别信息表';

insert into info_category(code, name, color_code, state) values ('BigData', '大数据', '#FFC125', 's');

-- ----------------------------
-- 资讯信息表
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `info_category_id` int(11) NOT NULL COMMENT '资讯类别ID',
  `author_id` int(11) NOT NULL COMMENT '作者ID',
  `title` varchar(200) NOT NULL COMMENT '资讯标题',
  `image` varchar(200) NOT NULL COMMENT '展示图片',
  `short_desc` varchar(200) COMMENT '资讯简述',
  `content` text NOT NULL COMMENT '资讯内容',
  `tags` varchar(64) COMMENT '标签集， 格式 标签1|标签2|标签3',
  `suggest` varchar(1) DEFAULT '0' COMMENT '首页推荐  1是   0不是',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime COMMENT '更新时间',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  PRIMARY KEY (`id`),
  KEY `info_id` (`id`),
  KEY `info_info_category_id` (`info_category_id`),
  KEY `info_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯信息表';

-- ----------------------------
-- 资讯评论表
-- ----------------------------
DROP TABLE IF EXISTS `info_comment`;
CREATE TABLE `info_comment` (
  `id` int(11) unsigned auto_increment NOT NULL COMMENT '主键ID',
  `author_id` int(11) NOT NULL COMMENT '评论人ID',
  `to_author` varchar(60)  COMMENT '被评论人昵称',
  `info_id` int(11) NOT NULL COMMENT '资讯ID',
  `content` varchar(400) NOT NULL COMMENT '评论内容',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(1) COLLATE utf8_bin DEFAULT 'c' COMMENT '数据状态   c:草稿 s;已审核 d:删除',
  PRIMARY KEY (`id`),
  KEY `info_comment_id` (`id`),
  KEY `info_comment_info_id` (`info_id`),
  KEY `info_comment_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资讯评论表';

-- ----------------------------
-- 活动报名表
-- ----------------------------
DROP TABLE IF EXISTS `activity_app`;
CREATE TABLE `activity_app` (
  `activity_id` varchar(32) NOT NULL,
  `user_id` int(11) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `app_dt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活动报名表';

-- ----------------------------
-- 创业活动表
-- ----------------------------
DROP TABLE IF EXISTS `es_activity`;
CREATE TABLE `es_activity` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `overview` text NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `begintime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `organizer` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `address` varchar(200) NOT NULL,
  `image_id` varchar(32) NOT NULL,
  `text` text NOT NULL,
  `app_begin_dt` date DEFAULT NULL,
  `app_end_dt` date DEFAULT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='创业活动表';
