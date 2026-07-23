package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoAudioCategory;
import com.ruoyi.system.service.dizang.IFoAudioCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "audioCategory")
public class FoAudioCategoryController extends BaseController {

    @Autowired
    private IFoAudioCategoryService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoAudioCategory entity) {
        startPage();
        return getDataTable(service.selectFoAudioCategoryList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoAudioCategoryById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoAudioCategory entity) {
        return toAjax(service.insertFoAudioCategory(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoAudioCategory entity) {
        return toAjax(service.updateFoAudioCategory(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoAudioCategoryByIds(ids));
    }
}