package com.dizang.controller;

import com.dizang.common.PageResult;
import com.dizang.common.R;
import com.dizang.entity.Blessing;
import com.dizang.service.BlessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Map;

@Tag(name = "祈福墙")
@RestController
@RequestMapping("/api/v1/blessings")
@RequiredArgsConstructor
public class BlessingController {

    private final BlessingService blessingService;

    @Operation(summary = "祈福列表（已审核）")
    @GetMapping
    public R<PageResult<Blessing>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return R.ok(PageResult.of(blessingService.getApprovedList(page, size)));
    }

    @Operation(summary = "提交祈福")
    @PostMapping
    public R<?> submit(@RequestBody @Validated BlessingForm form, HttpServletRequest request) {
        String ipHash = hashIp(getClientIp(request));
        blessingService.submit(form.getNickname(), form.getContent(), ipHash);
        return R.ok(Map.of("message", "祈福已送达，待审核通过后展示，愿您心想事成"));
    }

    @Operation(summary = "点赞")
    @PostMapping("/{id}/like")
    public R<?> like(@PathVariable Long id, HttpServletRequest request) {
        String ipHash = hashIp(getClientIp(request));
        blessingService.like(id, ipHash);
        return R.ok();
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) ip = request.getRemoteAddr();
        return ip.split(",")[0].trim();
    }

    private String hashIp(String ip) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(ip.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash).substring(0, 16);
        } catch (Exception e) {
            return ip;
        }
    }

    @Data
    static class BlessingForm {
        private String nickname;
        @NotBlank(message = "祈福内容不能为空")
        @Size(max = 200, message = "祈福内容不超过200字")
        private String content;
    }
}