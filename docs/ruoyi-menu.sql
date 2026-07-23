-- =============================================
-- RuoYi 学佛内容管理菜单
-- 在现有 RuoYi 数据库 dizang 库中执行
-- =============================================

USE dizang;

-- 一级菜单: 学佛内容管理
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2000, '学佛内容', 0, 5, 'dizang', NULL, NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'guide', 'admin', sysdate(), '', NULL, '学佛网站内容管理'),
(2001, '首页轮播', 2000, 1, 'banner', 'dizang/banner/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:banner:list', 'image', 'admin', sysdate(), '', NULL, ''),
(2002, '佛学经典', 2000, 2, 'classic', 'dizang/classic/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:classic:list', 'documentation', 'admin', sysdate(), '', NULL, ''),
(2003, '经典章节', 2000, 3, 'chapter', 'dizang/chapter/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:chapter:list', 'list', 'admin', sysdate(), '', NULL, ''),
(2004, '大德管理', 2000, 4, 'master', 'dizang/master/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:master:list', 'peoples', 'admin', sysdate(), '', NULL, ''),
(2005, '大德开示', 2000, 5, 'teaching', 'dizang/teaching/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:teaching:list', 'edit', 'admin', sysdate(), '', NULL, ''),
(2006, '专题管理', 2000, 6, 'topic', 'dizang/topic/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:topic:list', 'tab', 'admin', sysdate(), '', NULL, ''),
(2007, '专题文章', 2000, 7, 'article', 'dizang/article/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:article:list', 'example', 'admin', sysdate(), '', NULL, ''),
(2008, '知识分类', 2000, 8, 'knowledgeCategory', 'dizang/knowledgeCategory/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:knowledgeCategory:list', 'tree-table', 'admin', sysdate(), '', NULL, ''),
(2009, '佛教知识', 2000, 9, 'knowledge', 'dizang/knowledge/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:knowledge:list', 'education', 'admin', sysdate(), '', NULL, ''),
(2010, '梵音分类', 2000, 10, 'audioCategory', 'dizang/audioCategory/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:audioCategory:list', 'nested', 'admin', sysdate(), '', NULL, ''),
(2011, '梵音管理', 2000, 11, 'audio', 'dizang/audio/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:audio:list', 'radio', 'admin', sysdate(), '', NULL, ''),
(2012, '祈福墙审核', 2000, 12, 'blessing', 'dizang/blessing/index', NULL, NULL, 1, 0, 'C', '0', '0', 'dizang:blessing:list', 'chat', 'admin', sysdate(), '', NULL, '');

-- 按钮权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time) VALUES
(2013, '轮播查询', 2001, 1, 'F', 'dizang:banner:query', 'admin', sysdate()),
(2014, '轮播新增', 2001, 2, 'F', 'dizang:banner:add', 'admin', sysdate()),
(2015, '轮播修改', 2001, 3, 'F', 'dizang:banner:edit', 'admin', sysdate()),
(2016, '轮播删除', 2001, 4, 'F', 'dizang:banner:remove', 'admin', sysdate()),
(2017, '经典查询', 2002, 1, 'F', 'dizang:classic:query', 'admin', sysdate()),
(2018, '经典新增', 2002, 2, 'F', 'dizang:classic:add', 'admin', sysdate()),
(2019, '经典修改', 2002, 3, 'F', 'dizang:classic:edit', 'admin', sysdate()),
(2020, '经典删除', 2002, 4, 'F', 'dizang:classic:remove', 'admin', sysdate()),
(2021, '章节查询', 2003, 1, 'F', 'dizang:chapter:query', 'admin', sysdate()),
(2022, '章节新增', 2003, 2, 'F', 'dizang:chapter:add', 'admin', sysdate()),
(2023, '章节修改', 2003, 3, 'F', 'dizang:chapter:edit', 'admin', sysdate()),
(2024, '章节删除', 2003, 4, 'F', 'dizang:chapter:remove', 'admin', sysdate()),
(2025, '大德查询', 2004, 1, 'F', 'dizang:master:query', 'admin', sysdate()),
(2026, '大德新增', 2004, 2, 'F', 'dizang:master:add', 'admin', sysdate()),
(2027, '大德修改', 2004, 3, 'F', 'dizang:master:edit', 'admin', sysdate()),
(2028, '大德删除', 2004, 4, 'F', 'dizang:master:remove', 'admin', sysdate()),
(2029, '开示查询', 2005, 1, 'F', 'dizang:teaching:query', 'admin', sysdate()),
(2030, '开示新增', 2005, 2, 'F', 'dizang:teaching:add', 'admin', sysdate()),
(2031, '开示修改', 2005, 3, 'F', 'dizang:teaching:edit', 'admin', sysdate()),
(2032, '开示删除', 2005, 4, 'F', 'dizang:teaching:remove', 'admin', sysdate()),
(2033, '专题查询', 2006, 1, 'F', 'dizang:topic:query', 'admin', sysdate()),
(2034, '专题新增', 2006, 2, 'F', 'dizang:topic:add', 'admin', sysdate()),
(2035, '专题修改', 2006, 3, 'F', 'dizang:topic:edit', 'admin', sysdate()),
(2036, '专题删除', 2006, 4, 'F', 'dizang:topic:remove', 'admin', sysdate()),
(2037, '文章查询', 2007, 1, 'F', 'dizang:article:query', 'admin', sysdate()),
(2038, '文章新增', 2007, 2, 'F', 'dizang:article:add', 'admin', sysdate()),
(2039, '文章修改', 2007, 3, 'F', 'dizang:article:edit', 'admin', sysdate()),
(2040, '文章删除', 2007, 4, 'F', 'dizang:article:remove', 'admin', sysdate()),
(2041, '知识分类查询', 2008, 1, 'F', 'dizang:knowledgeCategory:query', 'admin', sysdate()),
(2042, '知识分类新增', 2008, 2, 'F', 'dizang:knowledgeCategory:add', 'admin', sysdate()),
(2043, '知识分类修改', 2008, 3, 'F', 'dizang:knowledgeCategory:edit', 'admin', sysdate()),
(2044, '知识分类删除', 2008, 4, 'F', 'dizang:knowledgeCategory:remove', 'admin', sysdate()),
(2045, '佛教知识查询', 2009, 1, 'F', 'dizang:knowledge:query', 'admin', sysdate()),
(2046, '佛教知识新增', 2009, 2, 'F', 'dizang:knowledge:add', 'admin', sysdate()),
(2047, '佛教知识修改', 2009, 3, 'F', 'dizang:knowledge:edit', 'admin', sysdate()),
(2048, '佛教知识删除', 2009, 4, 'F', 'dizang:knowledge:remove', 'admin', sysdate()),
(2049, '梵音分类查询', 2010, 1, 'F', 'dizang:audioCategory:query', 'admin', sysdate()),
(2050, '梵音分类新增', 2010, 2, 'F', 'dizang:audioCategory:add', 'admin', sysdate()),
(2051, '梵音分类修改', 2010, 3, 'F', 'dizang:audioCategory:edit', 'admin', sysdate()),
(2052, '梵音分类删除', 2010, 4, 'F', 'dizang:audioCategory:remove', 'admin', sysdate()),
(2053, '梵音查询', 2011, 1, 'F', 'dizang:audio:query', 'admin', sysdate()),
(2054, '梵音新增', 2011, 2, 'F', 'dizang:audio:add', 'admin', sysdate()),
(2055, '梵音修改', 2011, 3, 'F', 'dizang:audio:edit', 'admin', sysdate()),
(2056, '梵音删除', 2011, 4, 'F', 'dizang:audio:remove', 'admin', sysdate()),
(2057, '祈福查询', 2012, 1, 'F', 'dizang:blessing:query', 'admin', sysdate()),
(2058, '祈福删除', 2012, 2, 'F', 'dizang:blessing:remove', 'admin', sysdate()),
(2059, '祈福审核', 2012, 3, 'F', 'dizang:blessing:audit', 'admin', sysdate());

-- 给超级管理员角色分配新菜单权限
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu WHERE menu_id BETWEEN 2000 AND 2059;