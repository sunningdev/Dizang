package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.entity.Article;
import com.dizang.entity.Topic;
import com.dizang.mapper.ArticleMapper;
import com.dizang.mapper.TopicMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "专题")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TopicController {

    private final TopicMapper topicMapper;
    private final ArticleMapper articleMapper;

    @Operation(summary = "专题列表")
    @GetMapping("/topics")
    public R<?> list() {
        return R.ok(topicMapper.selectList(
                new LambdaQueryWrapper<Topic>()
                        .eq(Topic::getStatus, "0")
                        .eq(Topic::getDelFlag, "0")
                        .orderByAsc(Topic::getSortOrder)));
    }

    @Operation(summary = "专题详情")
    @GetMapping("/topics/{id}")
    public R<Topic> detail(@PathVariable Long id) {
        return R.ok(topicMapper.selectById(id));
    }

    @Operation(summary = "专题文章列表")
    @GetMapping("/topics/{id}/articles")
    public R<PageResult<Article>> articles(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return R.ok(PageResult.of(articleMapper.selectPage(
                new Page<>(page, size),
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getTopicId, id)
                        .eq(Article::getStatus, "0")
                        .eq(Article::getDelFlag, "0")
                        .orderByDesc(Article::getPublishTime))));
    }

    @Operation(summary = "文章详情")
    @GetMapping("/articles/{id}")
    public R<Article> articleDetail(@PathVariable Long id) {
        return R.ok(articleMapper.selectById(id));
    }
}