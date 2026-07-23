package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoKnowledge;
import com.ruoyi.system.mapper.dizang.FoKnowledgeMapper;
import com.ruoyi.system.service.dizang.IFoKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoKnowledgeServiceImpl implements IFoKnowledgeService {

    @Autowired
    private FoKnowledgeMapper mapper;

    @Override
    public FoKnowledge selectFoKnowledgeById(Long id) { return mapper.selectFoKnowledgeById(id); }

    @Override
    public List<FoKnowledge> selectFoKnowledgeList(FoKnowledge entity) { return mapper.selectFoKnowledgeList(entity); }

    @Override
    public int insertFoKnowledge(FoKnowledge entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoKnowledge(entity);
    }

    @Override
    public int updateFoKnowledge(FoKnowledge entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoKnowledge(entity);
    }

    @Override
    public int deleteFoKnowledgeById(Long id) { return mapper.deleteFoKnowledgeById(id); }

    @Override
    public int deleteFoKnowledgeByIds(Long[] ids) { return mapper.deleteFoKnowledgeByIds(ids); }
}