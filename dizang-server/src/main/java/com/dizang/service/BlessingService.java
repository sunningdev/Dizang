package com.dizang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizang.entity.Blessing;
import java.util.Map;

public interface BlessingService {
    Page<Blessing> getApprovedList(int pageNum, int pageSize);
    void submit(String nickname, String content, String ipHash);
    void like(Long id, String ipHash);
}