package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoBlessing;
import com.ruoyi.system.service.dizang.IFoBlessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/dizang/blessing")
public class FoBlessingController extends BaseController {

    @Autowired
    private IFoBlessingService service;

    @PreAuthorize("@ss.hasPermi('dizang:blessing:list')")
    @GetMapping("/list")
    public TableDataInfo list(FoBlessing entity) {
        startPage();
        return getDataTable(service.selectFoBlessingList(entity));
    }

    @PreAuthorize("@ss.hasPermi('dizang:blessing:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoBlessingById(id));
    }

    @PreAuthorize("@ss.hasPermi('dizang:blessing:remove')")
    @Log(title = "祈福墙", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoBlessingByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('dizang:blessing:audit')")
    @Log(title = "祈福审核-通过", businessType = BusinessType.UPDATE)
    @PutMapping("/approve/{id}")
    public AjaxResult approve(@PathVariable Long id) {
        FoBlessing b = new FoBlessing();
        b.setId(id);
        b.setAuditStatus(1);
        b.setAuditBy(getUsername());
        b.setAuditTime(new Date());
        return toAjax(service.updateFoBlessing(b));
    }

    @PreAuthorize("@ss.hasPermi('dizang:blessing:audit')")
    @Log(title = "祈福审核-拒绝", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult reject(@RequestBody FoBlessing entity) {
        entity.setAuditStatus(2);
        entity.setAuditBy(getUsername());
        entity.setAuditTime(new Date());
        return toAjax(service.updateFoBlessing(entity));
    }
}