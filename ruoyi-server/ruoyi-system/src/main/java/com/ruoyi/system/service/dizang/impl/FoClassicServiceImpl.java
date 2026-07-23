package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoClassic;
import com.ruoyi.system.mapper.dizang.FoClassicMapper;
import com.ruoyi.system.service.dizang.IFoClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoClassicServiceImpl implements IFoClassicService {

    @Autowired
    private FoClassicMapper mapper;

    @Override
    public FoClassic selectFoClassicById(Long id) { return mapper.selectFoClassicById(id); }

    @Override
    public List<FoClassic> selectFoClassicList(FoClassic entity) { return mapper.selectFoClassicList(entity); }

    @Override
    public int insertFoClassic(FoClassic entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoClassic(entity);
    }

    @Override
    public int updateFoClassic(FoClassic entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoClassic(entity);
    }

    @Override
    public int deleteFoClassicById(Long id) { return mapper.deleteFoClassicById(id); }

    @Override
    public int deleteFoClassicByIds(Long[] ids) { return mapper.deleteFoClassicByIds(ids); }
}