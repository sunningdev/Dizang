package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoKnowledgeCategory;
import java.util.List;

public interface FoKnowledgeCategoryMapper {
    public FoKnowledgeCategory selectFoKnowledgeCategoryById(Long id);
    public List<FoKnowledgeCategory> selectFoKnowledgeCategoryList(FoKnowledgeCategory entity);
    public int insertFoKnowledgeCategory(FoKnowledgeCategory entity);
    public int updateFoKnowledgeCategory(FoKnowledgeCategory entity);
    public int deleteFoKnowledgeCategoryById(Long id);
    public int deleteFoKnowledgeCategoryByIds(Long[] ids);
}