package com.ruoyi.system.service.dizang;

import com.ruoyi.system.domain.dizang.FoKnowledge;
import java.util.List;

public interface IFoKnowledgeService {
    public FoKnowledge selectFoKnowledgeById(Long id);
    public List<FoKnowledge> selectFoKnowledgeList(FoKnowledge entity);
    public int insertFoKnowledge(FoKnowledge entity);
    public int updateFoKnowledge(FoKnowledge entity);
    public int deleteFoKnowledgeById(Long id);
    public int deleteFoKnowledgeByIds(Long[] ids);
}