package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoArticle;
import com.ruoyi.system.mapper.dizang.FoArticleMapper;
import com.ruoyi.system.service.dizang.IFoArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoArticleServiceImpl implements IFoArticleService {

    @Autowired
    private FoArticleMapper mapper;

    @Override
    public FoArticle selectFoArticleById(Long id) { return mapper.selectFoArticleById(id); }

    @Override
    public List<FoArticle> selectFoArticleList(FoArticle entity) { return mapper.selectFoArticleList(entity); }

    @Override
    public int insertFoArticle(FoArticle entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoArticle(entity);
    }

    @Override
    public int updateFoArticle(FoArticle entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoArticle(entity);
    }

    @Override
    public int deleteFoArticleById(Long id) { return mapper.deleteFoArticleById(id); }

    @Override
    public int deleteFoArticleByIds(Long[] ids) { return mapper.deleteFoArticleByIds(ids); }
}