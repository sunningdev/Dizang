package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoAudio;
import java.util.List;

public interface FoAudioMapper {
    public FoAudio selectFoAudioById(Long id);
    public List<FoAudio> selectFoAudioList(FoAudio entity);
    public int insertFoAudio(FoAudio entity);
    public int updateFoAudio(FoAudio entity);
    public int deleteFoAudioById(Long id);
    public int deleteFoAudioByIds(Long[] ids);
}