package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoMaster;
import com.ruoyi.system.service.dizang.IFoMasterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "master")
public class FoMasterController extends BaseController {

    @Autowired
    private IFoMasterService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoMaster entity) {
        startPage();
        return getDataTable(service.selectFoMasterList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoMasterById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoMaster entity) {
        return toAjax(service.insertFoMaster(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoMaster entity) {
        return toAjax(service.updateFoMaster(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "大德", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoMasterByIds(ids));
    }
}