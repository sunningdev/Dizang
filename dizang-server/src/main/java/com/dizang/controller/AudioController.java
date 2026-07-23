package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.entity.Audio;
import com.dizang.entity.AudioCategory;
import com.dizang.mapper.AudioCategoryMapper;
import com.dizang.mapper.AudioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "梵音")
@RestController
@RequestMapping("/api/v1/audios")
@RequiredArgsConstructor
public class AudioController {

    private final AudioMapper audioMapper;
    private final AudioCategoryMapper categoryMapper;

    @Operation(summary = "梵音分类")
    @GetMapping("/categories")
    public R<?> categories() {
        return R.ok(categoryMapper.selectList(
                new LambdaQueryWrapper<AudioCategory>()
                        .eq(AudioCategory::getStatus, "0")
                        .eq(AudioCategory::getDelFlag, "0")
                        .orderByAsc(AudioCategory::getSortOrder)));
    }

    @Operation(summary = "梵音列表")
    @GetMapping
    public R<PageResult<Audio>> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return R.ok(PageResult.of(audioMapper.selectPage(
                new Page<>(page, size),
                new LambdaQueryWrapper<Audio>()
                        .eq(Audio::getStatus, "0")
                        .eq(Audio::getDelFlag, "0")
                        .eq(categoryId != null, Audio::getCategoryId, categoryId)
                        .orderByDesc(Audio::getId))));
    }

    @Operation(summary = "梵音详情")
    @GetMapping("/{id}")
    public R<Audio> detail(@PathVariable Long id) {
        return R.ok(audioMapper.selectById(id));
    }

    @Operation(summary = "播放计数")
    @PostMapping("/{id}/play")
    public R<?> play(@PathVariable Long id) {
        Audio audio = audioMapper.selectById(id);
        if (audio != null) {
            audio.setPlayCount(audio.getPlayCount() == null ? 1 : audio.getPlayCount() + 1);
            audioMapper.updateById(audio);
        }
        return R.ok();
    }
}