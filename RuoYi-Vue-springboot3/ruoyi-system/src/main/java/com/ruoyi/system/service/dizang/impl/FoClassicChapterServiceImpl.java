package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoClassicChapter;
import com.ruoyi.system.mapper.dizang.FoClassicChapterMapper;
import com.ruoyi.system.service.dizang.IFoClassicChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoClassicChapterServiceImpl implements IFoClassicChapterService {

    @Autowired
    private FoClassicChapterMapper mapper;

    @Override
    public FoClassicChapter selectFoClassicChapterById(Long id) { return mapper.selectFoClassicChapterById(id); }

    @Override
    public List<FoClassicChapter> selectFoClassicChapterList(FoClassicChapter entity) { return mapper.selectFoClassicChapterList(entity); }

    @Override
    public int insertFoClassicChapter(FoClassicChapter entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoClassicChapter(entity);
    }

    @Override
    public int updateFoClassicChapter(FoClassicChapter entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoClassicChapter(entity);
    }

    @Override
    public int deleteFoClassicChapterById(Long id) { return mapper.deleteFoClassicChapterById(id); }

    @Override
    public int deleteFoClassicChapterByIds(Long[] ids) { return mapper.deleteFoClassicChapterByIds(ids); }
}