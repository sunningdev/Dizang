package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoTeaching;
import com.ruoyi.system.mapper.dizang.FoTeachingMapper;
import com.ruoyi.system.service.dizang.IFoTeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoTeachingServiceImpl implements IFoTeachingService {

    @Autowired
    private FoTeachingMapper mapper;

    @Override
    public FoTeaching selectFoTeachingById(Long id) { return mapper.selectFoTeachingById(id); }

    @Override
    public List<FoTeaching> selectFoTeachingList(FoTeaching entity) { return mapper.selectFoTeachingList(entity); }

    @Override
    public int insertFoTeaching(FoTeaching entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoTeaching(entity);
    }

    @Override
    public int updateFoTeaching(FoTeaching entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoTeaching(entity);
    }

    @Override
    public int deleteFoTeachingById(Long id) { return mapper.deleteFoTeachingById(id); }

    @Override
    public int deleteFoTeachingByIds(Long[] ids) { return mapper.deleteFoTeachingByIds(ids); }
}