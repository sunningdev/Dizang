package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoTopic;
import com.ruoyi.system.service.dizang.IFoTopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "topic")
public class FoTopicController extends BaseController {

    @Autowired
    private IFoTopicService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoTopic entity) {
        startPage();
        return getDataTable(service.selectFoTopicList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoTopicById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "专题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoTopic entity) {
        return toAjax(service.insertFoTopic(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "专题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoTopic entity) {
        return toAjax(service.updateFoTopic(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "专题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoTopicByIds(ids));
    }
}