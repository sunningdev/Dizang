package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.entity.Knowledge;
import com.dizang.entity.KnowledgeCategory;
import com.dizang.mapper.KnowledgeCategoryMapper;
import com.dizang.mapper.KnowledgeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "佛教知识")
@RestController
@RequestMapping("/api/v1/knowledge")
@RequiredArgsConstructor
public class KnowledgeController {

    private final KnowledgeCategoryMapper categoryMapper;
    private final KnowledgeMapper knowledgeMapper;

    @Operation(summary = "知识分类")
    @GetMapping("/categories")
    public R<?> categories() {
        return R.ok(categoryMapper.selectList(
                new LambdaQueryWrapper<KnowledgeCategory>()
                        .eq(KnowledgeCategory::getStatus, "0")
                        .eq(KnowledgeCategory::getDelFlag, "0")
                        .orderByAsc(KnowledgeCategory::getSortOrder)));
    }

    @Operation(summary = "知识列表")
    @GetMapping
    public R<PageResult<Knowledge>> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return R.ok(PageResult.of(knowledgeMapper.selectPage(
                new Page<>(page, size),
                new LambdaQueryWrapper<Knowledge>()
                        .eq(Knowledge::getStatus, "0")
                        .eq(Knowledge::getDelFlag, "0")
                        .eq(categoryId != null, Knowledge::getCategoryId, categoryId)
                        .orderByDesc(Knowledge::getId))));
    }

    @Operation(summary = "知识详情")
    @GetMapping("/{id}")
    public R<Knowledge> detail(@PathVariable Long id) {
        return R.ok(knowledgeMapper.selectById(id));
    }
}