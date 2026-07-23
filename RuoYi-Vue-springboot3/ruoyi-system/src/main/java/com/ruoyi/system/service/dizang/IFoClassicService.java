package com.ruoyi.system.service.dizang;

import com.ruoyi.system.domain.dizang.FoClassic;
import java.util.List;

public interface IFoClassicService {
    public FoClassic selectFoClassicById(Long id);
    public List<FoClassic> selectFoClassicList(FoClassic entity);
    public int insertFoClassic(FoClassic entity);
    public int updateFoClassic(FoClassic entity);
    public int deleteFoClassicById(Long id);
    public int deleteFoClassicByIds(Long[] ids);
}