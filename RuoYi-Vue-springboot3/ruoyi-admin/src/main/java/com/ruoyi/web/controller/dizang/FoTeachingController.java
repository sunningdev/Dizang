package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoTeaching;
import com.ruoyi.system.service.dizang.IFoTeachingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "teaching")
public class FoTeachingController extends BaseController {

    @Autowired
    private IFoTeachingService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoTeaching entity) {
        startPage();
        return getDataTable(service.selectFoTeachingList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoTeachingById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德开示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoTeaching entity) {
        return toAjax(service.insertFoTeaching(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德开示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoTeaching entity) {
        return toAjax(service.updateFoTeaching(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德开示", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoTeachingByIds(ids));
    }
}