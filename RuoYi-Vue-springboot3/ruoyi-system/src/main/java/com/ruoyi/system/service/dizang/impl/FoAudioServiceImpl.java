package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoAudio;
import com.ruoyi.system.mapper.dizang.FoAudioMapper;
import com.ruoyi.system.service.dizang.IFoAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoAudioServiceImpl implements IFoAudioService {

    @Autowired
    private FoAudioMapper mapper;

    @Override
    public FoAudio selectFoAudioById(Long id) { return mapper.selectFoAudioById(id); }

    @Override
    public List<FoAudio> selectFoAudioList(FoAudio entity) { return mapper.selectFoAudioList(entity); }

    @Override
    public int insertFoAudio(FoAudio entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoAudio(entity);
    }

    @Override
    public int updateFoAudio(FoAudio entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoAudio(entity);
    }

    @Override
    public int deleteFoAudioById(Long id) { return mapper.deleteFoAudioById(id); }

    @Override
    public int deleteFoAudioByIds(Long[] ids) { return mapper.deleteFoAudioByIds(ids); }
}