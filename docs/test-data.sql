USE dizang;

-- 测试 Banner
INSERT IGNORE INTO fo_banner (id, title, image_url, link_url, sort_order, status, del_flag, create_time) VALUES
(1, '阿弥陀佛', 'https://picsum.photos/750/300?random=1', '/topics/1', 1, '0', '0', NOW()),
(2, '般若波罗蜜多心经', 'https://picsum.photos/750/300?random=2', '/classics', 2, '0', '0', NOW()),
(3, '学佛，从心开始', 'https://picsum.photos/750/300?random=3', '/teachings', 3, '0', '0', NOW());

-- 测试大德
INSERT IGNORE INTO fo_master (id, name, bio, sort_order, status, del_flag, create_time) VALUES
(1, '净空法师', '净空法师（1927-2022），当代著名佛教法师，致力推广佛陀教育。', 1, '0', '0', NOW()),
(2, '圣严法师', '圣严法师（1931-2009），台湾著名禅师，法鼓山创办人。', 2, '0', '0', NOW()),
(3, '弘一法师', '弘一法师（1880-1942），近代著名高僧，艺术家出家后成为律宗第十一代祖师。', 3, '0', '0', NOW());

-- 测试开示
INSERT IGNORE INTO fo_teaching (id, master_id, title, summary, content, publish_time, status, del_flag, create_time) VALUES
(1, 1, '念佛是最稳当的修行方法', '净空法师开示：念阿弥陀佛是末法众生最稳当、最简便的修行方法', '<p>净空法师开示：<strong>念佛是最稳当的修行方法</strong></p><p>在这个末法时代，众生业障深重，修行不易。念阿弥陀佛，是释迦牟尼佛特别为末法众生开设的一条修行捷径。</p><p>只要一心念佛，临命终时，阿弥陀佛必来接引，往生西方极乐世界，永脱轮回之苦。</p>', NOW(), '0', '0', NOW()),
(2, 2, '禅的智慧——放下与承担', '圣严法师：禅修不是逃避现实，而是更清醒地面对人生', '<p>圣严法师开示：</p><p>很多人认为禅修是要逃离现实，其实恰恰相反。真正的禅修，是让我们更清醒地活在当下，更有智慧地面对生活中的一切。</p><p><strong>放下</strong>是放下执着，而不是放下责任；<strong>承担</strong>是承担因果，而不是抱怨逃避。</p>', NOW(), '0', '0', NOW()),
(3, 3, '念佛不忘救国，救国不忘念佛', '弘一法师对修行与现世的开示', '<p>弘一法师教导：</p><p>学佛之人，当念念不忘救度众生。修行与世间，并非对立。真正的修行人，于世间苦难，当生大悲心，行菩萨道。</p><p>以慈悲心待人，以智慧处世，这便是人间的修行。</p>', NOW(), '0', '0', NOW());

-- 测试佛学经典
INSERT IGNORE INTO fo_classic (id, title, description, category, status, del_flag, create_time) VALUES
(1, '心经', '《般若波罗蜜多心经》，简称《心经》，是佛教中影响最深远的经典之一，仅260字，却蕴含了般若思想的精髓。', '经', '0', '0', NOW()),
(2, '金刚经', '《金刚般若波罗蜜经》，大乘佛教重要经典，阐明无相、无我、无住的般若智慧。', '经', '0', '0', NOW()),
(3, '阿弥陀经', '《佛说阿弥陀经》，净土宗根本经典之一，介绍西方极乐世界的庄严，鼓励众生念佛往生。', '经', '0', '0', NOW());

-- 测试心经章节
INSERT IGNORE INTO fo_classic_chapter (id, classic_id, parent_id, title, content, sort_order, status, del_flag, create_time) VALUES
(1, 1, 0, '正文', '<p style="text-indent:2em;line-height:2">观自在菩萨，行深般若波罗蜜多时，照见五蕴皆空，度一切苦厄。</p><p style="text-indent:2em;line-height:2">舍利子，色不异空，空不异色，色即是空，空即是色，受想行识，亦复如是。</p><p style="text-indent:2em;line-height:2">舍利子，是诸法空相，不生不灭，不垢不净，不增不减。是故空中无色，无受想行识，无眼耳鼻舌身意，无色声香味触法，无眼界，乃至无意识界。</p><p style="text-indent:2em;line-height:2">无无明，亦无无明尽，乃至无老死，亦无老死尽。无苦集灭道，无智亦无得。</p><p style="text-indent:2em;line-height:2">以无所得故，菩提萨埵，依般若波罗蜜多故，心无挂碍，无挂碍故，无有恐怖，远离颠倒梦想，究竟涅槃。三世诸佛，依般若波罗蜜多故，得阿耨多罗三藐三菩提。</p><p style="text-indent:2em;line-height:2">故知般若波罗蜜多，是大神咒，是大明咒，是无上咒，是无等等咒，能除一切苦，真实不虚。</p><p style="text-indent:2em;line-height:2">故说般若波罗蜜多咒，即说咒曰：揭谛揭谛，波罗揭谛，波罗僧揭谛，菩提萨婆诃。</p>', 1, '0', '0', NOW()),
(2, 1, 0, '注解', '<p>心经全名《般若波罗蜜多心经》，唐代高僧玄奘法师译本流传最广。</p><p><strong>五蕴</strong>：色、受、想、行、识，是构成人的五种要素。</p><p><strong>照见五蕴皆空</strong>：以般若智慧观照，发现五蕴的本质是空的，没有固定不变的实体。</p><p><strong>色即是空，空即是色</strong>：现象与本质的辩证统一，物质现象（色）与空性是同一的。</p>', 2, '0', '0', NOW());

-- 测试专题文章
INSERT IGNORE INTO fo_article (id, topic_id, title, summary, content, publish_time, type, status, del_flag, create_time) VALUES
(1, 1, '如何正确念佛——净空法师开示', '念佛的方法、注意事项及功德利益', '<h3>念佛的正确方法</h3><p>净空法师开示：念佛要以<strong>清净心</strong>念，不要夹杂其他念头。</p><p>最简单的方法：口念心听，听清楚每一个字，心就定了，杂念就少了。</p><h3>念佛的功德</h3><p>《无量寿经》说：一向专念，无量寿佛。一心念佛，与阿弥陀佛心心相印，临命终时决定往生。</p>', NOW(), 1, '0', '0', NOW()),
(2, 2, '深信因果，改变命运', '因果是宇宙的根本法则，深信因果才能断恶修善', '<h3>什么是因果</h3><p>佛陀说：<em>欲知前世因，今生受者是；欲知来世果，今生作者是。</em></p><p>因果不虚，善恶必报，只是时间早晚的问题。</p><h3>如何深信因果</h3><p>1. 学习佛陀的教导，明白因果道理<br>2. 观察生活中的因果现象<br>3. 发愿断恶修善，从当下做起</p>', NOW(), 1, '0', '0', NOW()),
(3, 3, '放生的功德与意义', '戒杀放生是慈悲心的体现，具有无量功德', '<h3>为何要放生</h3><p>一切众生皆有佛性，皆有情感，都贪生怕死。放生，是对生命最大的尊重。</p><h3>放生的功德</h3><p>《杂宝藏经》说，放生能获得长寿、健康、减少疾病、福德增长等功德。</p><p>更重要的是，放生能培养我们的慈悲心，是修行菩萨道的具体行动。</p>', NOW(), 1, '0', '0', NOW());

-- 测试佛教知识
INSERT IGNORE INTO fo_knowledge (id, category_id, title, summary, content, status, del_flag, create_time) VALUES
(1, 1, '什么是三宝？', '佛法僧三宝是佛教的核心，皈依三宝是入门佛法的第一步', '<h3>佛宝</h3><p>佛，梵文Buddha，意为<strong>觉者</strong>，即彻底觉悟宇宙人生真相的人。释迦牟尼佛是本师，也是我们这个世界的教主。</p><h3>法宝</h3><p>法，即佛陀所说的教法，包括经律论三藏。法宝是指导我们修行的方法和道理。</p><h3>僧宝</h3><p>僧，即依照佛法修行的出家人所组成的僧团。僧宝是传授佛法、护持佛法的重要力量。</p><p>皈依三宝，是正式成为佛教徒的仪式，也是修行的起点。</p>', '0', '0', NOW()),
(2, 1, '什么是四圣谛？', '苦集灭道四谛，是佛陀最初说法的核心内容', '<h3>苦谛</h3><p>人生有苦：生老病死苦、爱别离苦、怨憎会苦、求不得苦、五蕴炽盛苦。</p><h3>集谛</h3><p>苦的根源是贪、嗔、痴三毒，由此造业，导致轮回受苦。</p><h3>灭谛</h3><p>苦可以熄灭，涅槃是苦的究竟灭除，是修行的目标。</p><h3>道谛</h3><p>通过八正道（正见、正思惟、正语、正业、正命、正精进、正念、正定）可以达到涅槃。</p>', '0', '0', NOW()),
(3, 4, '念佛能往生西方吗？', '净土法门的基本原理与往生条件', '<p><strong>问</strong>：念阿弥陀佛真的能往生西方极乐世界吗？</p><p><strong>答</strong>：可以。这是阿弥陀佛四十八大愿中明确承诺的。</p><p>往生的条件：<strong>信、愿、行</strong>三资粮缺一不可。</p><p>信：深信西方极乐世界的存在，深信阿弥陀佛的慈悲愿力。</p><p>愿：真诚发愿往生西方，厌离娑婆世界的苦。</p><p>行：老实念佛，持之以恒，临命终时至心念佛。</p>', '0', '0', NOW());

-- 测试梵音
INSERT IGNORE INTO fo_audio (id, category_id, title, description, file_url, duration, play_count, status, del_flag, create_time) VALUES
(1, 1, '心经（普通话）', '玄奘法师译本《般若波罗蜜多心经》，普通话诵读', 'https://www.w3schools.com/html/horse.mp3', 180, 0, '0', '0', NOW()),
(2, 2, '大悲咒', '《千手千眼观世音菩萨广大圆满无碍大悲心陀罗尼》', 'https://www.w3schools.com/html/horse.mp3', 480, 0, '0', '0', NOW()),
(3, 3, '禅意古琴 · 空山', '空灵的禅乐，助您静心冥想', 'https://www.w3schools.com/html/horse.mp3', 360, 0, '0', '0', NOW());

-- 测试祈福（已审核通过）
INSERT IGNORE INTO fo_blessing (id, nickname, content, like_count, audit_status, create_time, del_flag) VALUES
(1, '善信阿华', '愿家人平安健康，阿弥陀佛🙏', 8, 1, NOW(), '0'),
(2, '匿名善信', '南无阿弥陀佛，愿天下众生离苦得乐', 15, 1, NOW(), '0'),
(3, '虔诚弟子', '祈愿父母长寿，六亲眷属离苦得乐，阿弥陀佛', 5, 1, NOW(), '0');