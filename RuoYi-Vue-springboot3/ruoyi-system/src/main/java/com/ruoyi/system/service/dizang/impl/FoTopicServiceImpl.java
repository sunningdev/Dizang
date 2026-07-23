package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoTopic;
import com.ruoyi.system.mapper.dizang.FoTopicMapper;
import com.ruoyi.system.service.dizang.IFoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoTopicServiceImpl implements IFoTopicService {

    @Autowired
    private FoTopicMapper mapper;

    @Override
    public FoTopic selectFoTopicById(Long id) { return mapper.selectFoTopicById(id); }

    @Override
    public List<FoTopic> selectFoTopicList(FoTopic entity) { return mapper.selectFoTopicList(entity); }

    @Override
    public int insertFoTopic(FoTopic entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoTopic(entity);
    }

    @Override
    public int updateFoTopic(FoTopic entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoTopic(entity);
    }

    @Override
    public int deleteFoTopicById(Long id) { return mapper.deleteFoTopicById(id); }

    @Override
    public int deleteFoTopicByIds(Long[] ids) { return mapper.deleteFoTopicByIds(ids); }
}