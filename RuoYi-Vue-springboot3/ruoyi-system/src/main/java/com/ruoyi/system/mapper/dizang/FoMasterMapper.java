package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoMaster;
import java.util.List;

public interface FoMasterMapper {
    public FoMaster selectFoMasterById(Long id);
    public List<FoMaster> selectFoMasterList(FoMaster entity);
    public int insertFoMaster(FoMaster entity);
    public int updateFoMaster(FoMaster entity);
    public int deleteFoMasterById(Long id);
    public int deleteFoMasterByIds(Long[] ids);
}