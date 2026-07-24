package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.entity.Classic;
import com.dizang.entity.ClassicChapter;
import com.dizang.mapper.ClassicChapterMapper;
import com.dizang.mapper.ClassicMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "佛学经典")
@RestController
@RequestMapping("/api/v1/classics")
@RequiredArgsConstructor
public class ClassicController {

    private final ClassicMapper classicMapper;
    private final ClassicChapterMapper chapterMapper;

    @Operation(summary = "经典列表")
    @GetMapping
    public R<PageResult<Classic>> list(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Classic> wrapper = new LambdaQueryWrapper<Classic>()
                .eq(Classic::getStatus, "0")
                .eq(Classic::getDelFlag, "0")
                .eq(StringUtils.hasText(category), Classic::getCategory, category)
                .orderByAsc(Classic::getId);
        Page<Classic> result = classicMapper.selectPage(new Page<>(page, size), wrapper);
        return R.ok(PageResult.of(result));
    }

    @Operation(summary = "经典详情+目录")
    @GetMapping("/{id}")
    public R<?> detail(@PathVariable Long id) {
        Classic classic = classicMapper.selectById(id);
        if (classic == null) return R.fail(404, "经典不存在");
        List<ClassicChapter> chapters = chapterMapper.selectList(
                new LambdaQueryWrapper<ClassicChapter>()
                        .eq(ClassicChapter::getClassicId, id)
                        .eq(ClassicChapter::getDelFlag, "0")
                        .orderByAsc(ClassicChapter::getSortOrder)
        );
        Map<String, Object> data = new HashMap<>();
        data.put("classic", classic);
        data.put("chapters", chapters);
        return R.ok(data);
    }

    @Operation(summary = "章节内容")
    @GetMapping("/{classicId}/chapters/{chapterId}")
    public R<?> chapter(@PathVariable Long classicId, @PathVariable Long chapterId) {
        ClassicChapter chapter = chapterMapper.selectById(chapterId);
        if (chapter == null) return R.fail(404, "章节不存在");
        List<ClassicChapter> all = chapterMapper.selectList(
                new LambdaQueryWrapper<ClassicChapter>()
                        .eq(ClassicChapter::getClassicId, classicId)
                        .eq(ClassicChapter::getDelFlag, "0")
                        .orderByAsc(ClassicChapter::getSortOrder)
                        .select(ClassicChapter::getId)
        );
        Map<String, Object> data = new HashMap<>();
        data.put("chapter", chapter);
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId().equals(chapterId)) {
                if (i > 0) data.put("prevId", all.get(i - 1).getId());
                if (i < all.size() - 1) data.put("nextId", all.get(i + 1).getId());
                break;
            }
        }
        return R.ok(data);
    }
}