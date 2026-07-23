package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoClassic;
import com.ruoyi.system.service.dizang.IFoClassicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "classic")
public class FoClassicController extends BaseController {

    @Autowired
    private IFoClassicService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoClassic entity) {
        startPage();
        return getDataTable(service.selectFoClassicList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoClassicById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛学经典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoClassic entity) {
        return toAjax(service.insertFoClassic(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛学经典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoClassic entity) {
        return toAjax(service.updateFoClassic(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛学经典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoClassicByIds(ids));
    }
}