package com.dizang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.common.RateLimitHelper;
import com.dizang.entity.Blessing;
import com.dizang.mapper.BlessingMapper;
import com.dizang.service.BlessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlessingServiceImpl implements BlessingService {

    private final BlessingMapper blessingMapper;
    private final RateLimitHelper rateLimitHelper;

    @Override
    public Page<Blessing> getApprovedList(int pageNum, int pageSize) {
        return blessingMapper.selectPage(
            new Page<>(pageNum, pageSize),
            new LambdaQueryWrapper<Blessing>()
                .eq(Blessing::getAuditStatus, 1)
                .eq(Blessing::getDelFlag, "0")
                .orderByDesc(Blessing::getCreateTime)
        );
    }

    @Override
    public void submit(String nickname, String content, String ipHash) {
        String limitKey = "blessing:limit:" + ipHash;
        long count = rateLimitHelper.increment(limitKey, 3600);
        if (count > 5) {
            throw new IllegalArgumentException("提交过于频繁，请1小时后再试");
        }

        Blessing blessing = new Blessing();
        blessing.setNickname(nickname == null || nickname.isBlank() ? "匿名善信" : nickname);
        blessing.setContent(content);
        blessing.setLikeCount(0);
        blessing.setAuditStatus(0);
        blessing.setIpHash(ipHash);
        blessing.setCreateTime(LocalDateTime.now());
        blessing.setDelFlag("0");
        blessingMapper.insert(blessing);
    }

    @Override
    public void like(Long id, String ipHash) {
        String likeKey = "blessing:like:" + id + ":" + ipHash;
        if (rateLimitHelper.exists(likeKey)) {
            throw new IllegalArgumentException("您已经点过赞了");
        }
        Blessing blessing = blessingMapper.selectById(id);
        if (blessing == null || blessing.getAuditStatus() != 1) {
            throw new IllegalArgumentException("祈福不存在");
        }
        blessing.setLikeCount(blessing.getLikeCount() + 1);
        blessingMapper.updateById(blessing);
        rateLimitHelper.set(likeKey, "1", 30 * 24 * 3600);
    }
}