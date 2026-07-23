package com.ruoyi.system.service.dizang;

import com.ruoyi.system.domain.dizang.FoArticle;
import java.util.List;

public interface IFoArticleService {
    public FoArticle selectFoArticleById(Long id);
    public List<FoArticle> selectFoArticleList(FoArticle entity);
    public int insertFoArticle(FoArticle entity);
    public int updateFoArticle(FoArticle entity);
    public int deleteFoArticleById(Long id);
    public int deleteFoArticleByIds(Long[] ids);
}