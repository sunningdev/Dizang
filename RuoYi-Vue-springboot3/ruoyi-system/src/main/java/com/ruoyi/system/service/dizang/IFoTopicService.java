package com.ruoyi.system.service.dizang;

import com.ruoyi.system.domain.dizang.FoTopic;
import java.util.List;

public interface IFoTopicService {
    public FoTopic selectFoTopicById(Long id);
    public List<FoTopic> selectFoTopicList(FoTopic entity);
    public int insertFoTopic(FoTopic entity);
    public int updateFoTopic(FoTopic entity);
    public int deleteFoTopicById(Long id);
    public int deleteFoTopicByIds(Long[] ids);
}