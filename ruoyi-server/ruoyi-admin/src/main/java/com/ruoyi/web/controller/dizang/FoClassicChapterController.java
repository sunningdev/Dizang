package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoClassicChapter;
import com.ruoyi.system.service.dizang.IFoClassicChapterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "classicChapter")
public class FoClassicChapterController extends BaseController {

    @Autowired
    private IFoClassicChapterService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoClassicChapter entity) {
        startPage();
        return getDataTable(service.selectFoClassicChapterList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoClassicChapterById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "经典章节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoClassicChapter entity) {
        return toAjax(service.insertFoClassicChapter(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "经典章节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoClassicChapter entity) {
        return toAjax(service.updateFoClassicChapter(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "经典章节", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoClassicChapterByIds(ids));
    }
}