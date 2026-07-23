package com.dizang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dizang.common.R;
import com.dizang.entity.Classic;
import com.dizang.entity.Knowledge;
import com.dizang.entity.Teaching;
import com.dizang.mapper.ClassicMapper;
import com.dizang.mapper.KnowledgeMapper;
import com.dizang.mapper.TeachingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "全站搜索")
@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final ClassicMapper classicMapper;
    private final TeachingMapper teachingMapper;
    private final KnowledgeMapper knowledgeMapper;

    @Operation(summary = "全站搜索")
    @GetMapping
    public R<?> search(@RequestParam String q) {
        if (!StringUtils.hasText(q) || q.length() < 2) {
            return R.fail(400, "请输入至少2个字符");
        }
        String kw = "%" + q + "%";
        Map<String, Object> result = new HashMap<>();

        List<Classic> classics = classicMapper.selectList(
                new LambdaQueryWrapper<Classic>()
                        .eq(Classic::getStatus, "0").eq(Classic::getDelFlag, "0")
                        .like(Classic::getTitle, kw).last("LIMIT 5"));

        List<Teaching> teachings = teachingMapper.selectList(
                new LambdaQueryWrapper<Teaching>()
                        .eq(Teaching::getStatus, "0").eq(Teaching::getDelFlag, "0")
                        .like(Teaching::getTitle, kw).last("LIMIT 5"));

        List<Knowledge> knowledge = knowledgeMapper.selectList(
                new LambdaQueryWrapper<Knowledge>()
                        .eq(Knowledge::getStatus, "0").eq(Knowledge::getDelFlag, "0")
                        .like(Knowledge::getTitle, kw).last("LIMIT 5"));

        result.put("classics", classics);
        result.put("teachings", teachings);
        result.put("knowledge", knowledge);
        return R.ok(result);
    }
}