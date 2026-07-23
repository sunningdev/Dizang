-- =============================================
-- 学佛网站数据库建表脚本 (dizang)
-- Ruoyi 兼容：含 create_by/update_by/status/del_flag
-- =============================================

CREATE DATABASE IF NOT EXISTS dizang DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE dizang;

-- 首页轮播
CREATE TABLE IF NOT EXISTS fo_banner (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  title         VARCHAR(100)  NOT NULL COMMENT '标题',
  image_url     VARCHAR(500)  NOT NULL COMMENT '图片地址',
  link_url      VARCHAR(500)           COMMENT '跳转链接',
  sort_order    INT           DEFAULT 0 COMMENT '排序',
  status        CHAR(1)       DEFAULT '0' COMMENT '0正常 1停用',
  del_flag      CHAR(1)       DEFAULT '0' COMMENT '0存在 2删除',
  create_by     VARCHAR(64)   DEFAULT '' COMMENT '创建者',
  create_time   DATETIME                 COMMENT '创建时间',
  update_by     VARCHAR(64)   DEFAULT '' COMMENT '更新者',
  update_time   DATETIME                 COMMENT '更新时间',
  remark        VARCHAR(500)  DEFAULT '' COMMENT '备注'
) COMMENT='首页轮播';

-- 佛学经典
CREATE TABLE IF NOT EXISTS fo_classic (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  title         VARCHAR(200)  NOT NULL COMMENT '经典名称',
  description   TEXT                   COMMENT '简介',
  cover_url     VARCHAR(500)           COMMENT '封面图',
  category      VARCHAR(20)   DEFAULT '经' COMMENT '分类: 经/律/论/其他',
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='佛学经典';

-- 经典章节
CREATE TABLE IF NOT EXISTS fo_classic_chapter (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  classic_id    BIGINT        NOT NULL COMMENT '所属经典',
  parent_id     BIGINT        DEFAULT 0 COMMENT '父节点，0为顶级',
  title         VARCHAR(200)  NOT NULL COMMENT '章节标题',
  content       LONGTEXT               COMMENT '正文（HTML）',
  sort_order    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='经典章节';

-- 大德（大师）
CREATE TABLE IF NOT EXISTS fo_master (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(50)   NOT NULL COMMENT '法名',
  avatar        VARCHAR(500)           COMMENT '头像',
  bio           TEXT                   COMMENT '简介',
  sort_order    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='大德';

-- 大德开示
CREATE TABLE IF NOT EXISTS fo_teaching (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  master_id     BIGINT        NOT NULL COMMENT '大德ID',
  title         VARCHAR(300)  NOT NULL,
  summary       VARCHAR(500)           COMMENT '摘要',
  content       LONGTEXT               COMMENT '正文（HTML）',
  cover_url     VARCHAR(500),
  publish_time  DATETIME               COMMENT '发布时间',
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='大德开示';

-- 九大专题
CREATE TABLE IF NOT EXISTS fo_topic (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(50)   NOT NULL COMMENT '专题名称',
  icon          VARCHAR(10)   DEFAULT '☸' COMMENT 'Emoji图标',
  description   TEXT,
  sort_order    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='九大专题';

-- 专题文章
CREATE TABLE IF NOT EXISTS fo_article (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  topic_id      BIGINT                 COMMENT '所属专题',
  title         VARCHAR(300)  NOT NULL,
  summary       VARCHAR(500),
  content       LONGTEXT,
  cover_url     VARCHAR(500),
  type          TINYINT       DEFAULT 1 COMMENT '1专题文章',
  publish_time  DATETIME,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='专题文章';

-- 佛教知识分类
CREATE TABLE IF NOT EXISTS fo_knowledge_category (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(50)   NOT NULL,
  sort_order    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='佛教知识分类';

-- 佛教知识
CREATE TABLE IF NOT EXISTS fo_knowledge (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  category_id   BIGINT        NOT NULL,
  title         VARCHAR(300)  NOT NULL,
  summary       VARCHAR(500),
  content       LONGTEXT,
  cover_url     VARCHAR(500),
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='佛教知识';

-- 梵音分类
CREATE TABLE IF NOT EXISTS fo_audio_category (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(50)   NOT NULL,
  sort_order    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='梵音分类';

-- 梵音
CREATE TABLE IF NOT EXISTS fo_audio (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  category_id   BIGINT        NOT NULL,
  title         VARCHAR(200)  NOT NULL,
  description   VARCHAR(500),
  cover_url     VARCHAR(500),
  file_url      VARCHAR(500)  NOT NULL COMMENT '音频文件地址',
  duration      INT           DEFAULT 0 COMMENT '时长（秒）',
  play_count    INT           DEFAULT 0,
  status        CHAR(1)       DEFAULT '0',
  del_flag      CHAR(1)       DEFAULT '0',
  create_by     VARCHAR(64)   DEFAULT '',
  create_time   DATETIME,
  update_by     VARCHAR(64)   DEFAULT '',
  update_time   DATETIME,
  remark        VARCHAR(500)  DEFAULT ''
) COMMENT='梵音音频';

-- 祈福墙（审核字段为核心）
CREATE TABLE IF NOT EXISTS fo_blessing (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  nickname      VARCHAR(50)   DEFAULT '匿名善信' COMMENT '昵称',
  content       VARCHAR(200)  NOT NULL COMMENT '祈福内容',
  like_count    INT           DEFAULT 0,
  audit_status  TINYINT       DEFAULT 0 COMMENT '0待审 1已通过 2已拒绝',
  audit_by      VARCHAR(64)             COMMENT '审核人（Ruoyi用户名）',
  audit_time    DATETIME                COMMENT '审核时间',
  reject_reason VARCHAR(200)            COMMENT '拒绝原因',
  ip_hash       VARCHAR(64)             COMMENT 'IP哈希，防刷',
  status        CHAR(1)       DEFAULT '0' COMMENT '0正常 1停用',
  del_flag      CHAR(1)       DEFAULT '0' COMMENT '0存在 2删除',
  create_by     VARCHAR(64)   DEFAULT '' COMMENT '创建者',
  create_time   DATETIME               COMMENT '提交时间',
  update_by     VARCHAR(64)   DEFAULT '' COMMENT '更新者',
  update_time   DATETIME                COMMENT '更新时间',
  remark        VARCHAR(500)  DEFAULT '' COMMENT '备注'
) COMMENT='祈福墙';

-- =============================================
-- 种子数据
-- =============================================

INSERT IGNORE INTO fo_topic (id, name, icon, sort_order, status, del_flag, create_time) VALUES
(1, '精进念佛', '📿', 1, '0', '0', NOW()),
(2, '深信因果', '⚖️', 2, '0', '0', NOW()),
(3, '戒杀放生', '🕊️', 3, '0', '0', NOW()),
(4, '消除业障', '🌟', 4, '0', '0', NOW()),
(5, '禅定', '🧘', 5, '0', '0', NOW()),
(6, '贪嗔痴慢疑', '💡', 6, '0', '0', NOW()),
(7, '素食', '🥗', 7, '0', '0', NOW()),
(8, '往生西方', '🌅', 8, '0', '0', NOW()),
(9, '积德改命', '🌸', 9, '0', '0', NOW());

INSERT IGNORE INTO fo_knowledge_category (id, name, sort_order, status, del_flag, create_time) VALUES
(1, '入门基础', 1, '0', '0', NOW()),
(2, '戒律修行', 2, '0', '0', NOW()),
(3, '佛教历史', 3, '0', '0', NOW()),
(4, '常见问答', 4, '0', '0', NOW());

INSERT IGNORE INTO fo_audio_category (id, name, sort_order, status, del_flag, create_time) VALUES
(1, '诵经', 1, '0', '0', NOW()),
(2, '持咒', 2, '0', '0', NOW()),
(3, '禅乐', 3, '0', '0', NOW());