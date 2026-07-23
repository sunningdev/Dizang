package com.ruoyi.system.service.dizang.impl;

import com.ruoyi.system.domain.dizang.FoMaster;
import com.ruoyi.system.mapper.dizang.FoMasterMapper;
import com.ruoyi.system.service.dizang.IFoMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoMasterServiceImpl implements IFoMasterService {

    @Autowired
    private FoMasterMapper mapper;

    @Override
    public FoMaster selectFoMasterById(Long id) { return mapper.selectFoMasterById(id); }

    @Override
    public List<FoMaster> selectFoMasterList(FoMaster entity) { return mapper.selectFoMasterList(entity); }

    @Override
    public int insertFoMaster(FoMaster entity) {
        entity.setCreateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.insertFoMaster(entity);
    }

    @Override
    public int updateFoMaster(FoMaster entity) {
        entity.setUpdateBy(com.ruoyi.common.utils.SecurityUtils.getUsername());
        return mapper.updateFoMaster(entity);
    }

    @Override
    public int deleteFoMasterById(Long id) { return mapper.deleteFoMasterById(id); }

    @Override
    public int deleteFoMasterByIds(Long[] ids) { return mapper.deleteFoMasterByIds(ids); }
}