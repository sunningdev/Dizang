package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoTeaching;
import java.util.List;

public interface FoTeachingMapper {
    public FoTeaching selectFoTeachingById(Long id);
    public List<FoTeaching> selectFoTeachingList(FoTeaching entity);
    public int insertFoTeaching(FoTeaching entity);
    public int updateFoTeaching(FoTeaching entity);
    public int deleteFoTeachingById(Long id);
    public int deleteFoTeachingByIds(Long[] ids);
}