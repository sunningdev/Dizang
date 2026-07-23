package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoBanner;
import com.ruoyi.system.service.dizang.IFoBannerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "banner")
public class FoBannerController extends BaseController {

    @Autowired
    private IFoBannerService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoBanner entity) {
        startPage();
        return getDataTable(service.selectFoBannerList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoBannerById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "首页轮播", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoBanner entity) {
        return toAjax(service.insertFoBanner(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "首页轮播", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoBanner entity) {
        return toAjax(service.updateFoBanner(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "首页轮播", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoBannerByIds(ids));
    }
}