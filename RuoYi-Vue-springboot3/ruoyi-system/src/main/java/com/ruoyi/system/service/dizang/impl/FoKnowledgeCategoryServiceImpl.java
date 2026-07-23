package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoKnowledgeCategory;
import com.ruoyi.system.mapper.dizang.FoKnowledgeCategoryMapper;
import com.ruoyi.system.service.dizang.IFoKnowledgeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoKnowledgeCategoryServiceImpl implements IFoKnowledgeCategoryService {

    @Autowired
    private FoKnowledgeCategoryMapper mapper;

    @Override
    public FoKnowledgeCategory selectFoKnowledgeCategoryById(Long id) { return mapper.selectFoKnowledgeCategoryById(id); }

    @Override
    public List<FoKnowledgeCategory> selectFoKnowledgeCategoryList(FoKnowledgeCategory entity) { return mapper.selectFoKnowledgeCategoryList(entity); }

    @Override
    public int insertFoKnowledgeCategory(FoKnowledgeCategory entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoKnowledgeCategory(entity);
    }

    @Override
    public int updateFoKnowledgeCategory(FoKnowledgeCategory entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoKnowledgeCategory(entity);
    }

    @Override
    public int deleteFoKnowledgeCategoryById(Long id) { return mapper.deleteFoKnowledgeCategoryById(id); }

    @Override
    public int deleteFoKnowledgeCategoryByIds(Long[] ids) { return mapper.deleteFoKnowledgeCategoryByIds(ids); }
}