package com.ruoyi.system.mapper.dizang;

import com.ruoyi.system.domain.dizang.FoBanner;
import java.util.List;

public interface FoBannerMapper {
    public FoBanner selectFoBannerById(Long id);
    public List<FoBanner> selectFoBannerList(FoBanner entity);
    public int insertFoBanner(FoBanner entity);
    public int updateFoBanner(FoBanner entity);
    public int deleteFoBannerById(Long id);
    public int deleteFoBannerByIds(Long[] ids);
}