package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoAudio;
import com.ruoyi.system.service.dizang.IFoAudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "audio")
public class FoAudioController extends BaseController {

    @Autowired
    private IFoAudioService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoAudio entity) {
        startPage();
        return getDataTable(service.selectFoAudioList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoAudioById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoAudio entity) {
        return toAjax(service.insertFoAudio(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoAudio entity) {
        return toAjax(service.updateFoAudio(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "梵音", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoAudioByIds(ids));
    }
}