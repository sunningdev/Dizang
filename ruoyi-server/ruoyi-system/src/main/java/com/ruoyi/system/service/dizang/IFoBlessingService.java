package com.ruoyi.system.service.dizang;

import com.ruoyi.system.domain.dizang.FoBlessing;
import java.util.List;

public interface IFoBlessingService {
    public FoBlessing selectFoBlessingById(Long id);
    public List<FoBlessing> selectFoBlessingList(FoBlessing entity);
    public int insertFoBlessing(FoBlessing entity);
    public int updateFoBlessing(FoBlessing entity);
    public int deleteFoBlessingById(Long id);
    public int deleteFoBlessingByIds(Long[] ids);
}