package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoBlessing;
import com.ruoyi.system.mapper.dizang.FoBlessingMapper;
import com.ruoyi.system.service.dizang.IFoBlessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoBlessingServiceImpl implements IFoBlessingService {

    @Autowired
    private FoBlessingMapper mapper;

    @Override
    public FoBlessing selectFoBlessingById(Long id) { return mapper.selectFoBlessingById(id); }

    @Override
    public List<FoBlessing> selectFoBlessingList(FoBlessing entity) { return mapper.selectFoBlessingList(entity); }

    @Override
    public int insertFoBlessing(FoBlessing entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoBlessing(entity);
    }

    @Override
    public int updateFoBlessing(FoBlessing entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoBlessing(entity);
    }

    @Override
    public int deleteFoBlessingById(Long id) { return mapper.deleteFoBlessingById(id); }

    @Override
    public int deleteFoBlessingByIds(Long[] ids) { return mapper.deleteFoBlessingByIds(ids); }
}