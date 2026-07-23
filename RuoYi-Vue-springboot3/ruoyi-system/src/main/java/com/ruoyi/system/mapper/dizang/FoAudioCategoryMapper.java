package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoAudioCategory;
import java.util.List;

public interface FoAudioCategoryMapper {
    public FoAudioCategory selectFoAudioCategoryById(Long id);
    public List<FoAudioCategory> selectFoAudioCategoryList(FoAudioCategory entity);
    public int insertFoAudioCategory(FoAudioCategory entity);
    public int updateFoAudioCategory(FoAudioCategory entity);
    public int deleteFoAudioCategoryById(Long id);
    public int deleteFoAudioCategoryByIds(Long[] ids);
}