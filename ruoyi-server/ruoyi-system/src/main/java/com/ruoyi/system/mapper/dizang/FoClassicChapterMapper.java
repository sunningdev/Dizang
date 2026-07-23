package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoClassicChapter;
import java.util.List;

public interface FoClassicChapterMapper {
    public FoClassicChapter selectFoClassicChapterById(Long id);
    public List<FoClassicChapter> selectFoClassicChapterList(FoClassicChapter entity);
    public int insertFoClassicChapter(FoClassicChapter entity);
    public int updateFoClassicChapter(FoClassicChapter entity);
    public int deleteFoClassicChapterById(Long id);
    public int deleteFoClassicChapterByIds(Long[] ids);
}