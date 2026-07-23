package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoBanner;
import com.ruoyi.system.mapper.dizang.FoBannerMapper;
import com.ruoyi.system.service.dizang.IFoBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoBannerServiceImpl implements IFoBannerService {

    @Autowired
    private FoBannerMapper mapper;

    @Override
    public FoBanner selectFoBannerById(Long id) { return mapper.selectFoBannerById(id); }

    @Override
    public List<FoBanner> selectFoBannerList(FoBanner entity) { return mapper.selectFoBannerList(entity); }

    @Override
    public int insertFoBanner(FoBanner entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoBanner(entity);
    }

    @Override
    public int updateFoBanner(FoBanner entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoBanner(entity);
    }

    @Override
    public int deleteFoBannerById(Long id) { return mapper.deleteFoBannerById(id); }

    @Override
    public int deleteFoBannerByIds(Long[] ids) { return mapper.deleteFoBannerByIds(ids); }
}