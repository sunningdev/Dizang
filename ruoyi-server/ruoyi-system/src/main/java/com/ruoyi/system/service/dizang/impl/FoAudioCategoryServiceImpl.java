package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoAudioCategory;
import com.ruoyi.system.mapper.dizang.FoAudioCategoryMapper;
import com.ruoyi.system.service.dizang.IFoAudioCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoAudioCategoryServiceImpl implements IFoAudioCategoryService {

    @Autowired
    private FoAudioCategoryMapper mapper;

    @Override
    public FoAudioCategory selectFoAudioCategoryById(Long id) { return mapper.selectFoAudioCategoryById(id); }

    @Override
    public List<FoAudioCategory> selectFoAudioCategoryList(FoAudioCategory entity) { return mapper.selectFoAudioCategoryList(entity); }

    @Override
    public int insertFoAudioCategory(FoAudioCategory entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoAudioCategory(entity);
    }

    @Override
    public int updateFoAudioCategory(FoAudioCategory entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoAudioCategory(entity);
    }

    @Override
    public int deleteFoAudioCategoryById(Long id) { return mapper.deleteFoAudioCategoryById(id); }

    @Override
    public int deleteFoAudioCategoryByIds(Long[] ids) { return mapper.deleteFoAudioCategoryByIds(ids); }
}