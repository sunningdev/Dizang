package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.common.TeachingEnricher;
import com.dizang.entity.Master;
import com.dizang.entity.Teaching;
import com.dizang.mapper.MasterMapper;
import com.dizang.mapper.TeachingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "大德开示")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TeachingController {

    private final TeachingMapper teachingMapper;
    private final MasterMapper masterMapper;
    private final TeachingEnricher teachingEnricher;

    @Operation(summary = "大德列表")
    @GetMapping("/masters")
    public R<?> masters() {
        return R.ok(masterMapper.selectList(
                new LambdaQueryWrapper<Master>()
                        .eq(Master::getStatus, "0")
                        .eq(Master::getDelFlag, "0")
                        .orderByAsc(Master::getSortOrder)));
    }

    @Operation(summary = "开示列表")
    @GetMapping("/teachings")
    public R<PageResult<Teaching>> list(
            @RequestParam(required = false) Long masterId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Teaching> wrapper = new LambdaQueryWrapper<Teaching>()
                .eq(Teaching::getStatus, "0")
                .eq(Teaching::getDelFlag, "0")
                .eq(masterId != null, Teaching::getMasterId, masterId)
                .orderByDesc(Teaching::getPublishTime);
        Page<Teaching> result = teachingMapper.selectPage(new Page<>(page, size), wrapper);
        teachingEnricher.fillMasterName(result.getRecords());
        return R.ok(PageResult.of(result));
    }

    @Operation(summary = "开示详情")
    @GetMapping("/teachings/{id}")
    public R<Teaching> detail(@PathVariable Long id) {
        Teaching t = teachingMapper.selectById(id);
        if (t == null) return R.fail(404, "开示不存在");
        teachingEnricher.fillMasterName(t);
        return R.ok(t);
    }
}