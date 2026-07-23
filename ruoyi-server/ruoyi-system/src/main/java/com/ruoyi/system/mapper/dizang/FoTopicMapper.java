package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoTopic;
import java.util.List;

public interface FoTopicMapper {
    public FoTopic selectFoTopicById(Long id);
    public List<FoTopic> selectFoTopicList(FoTopic entity);
    public int insertFoTopic(FoTopic entity);
    public int updateFoTopic(FoTopic entity);
    public int deleteFoTopicById(Long id);
    public int deleteFoTopicByIds(Long[] ids);
}