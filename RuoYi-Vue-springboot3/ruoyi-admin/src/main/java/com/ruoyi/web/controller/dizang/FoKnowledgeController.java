package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoKnowledge;
import com.ruoyi.system.service.dizang.IFoKnowledgeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "knowledge")
public class FoKnowledgeController extends BaseController {

    @Autowired
    private IFoKnowledgeService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoKnowledge entity) {
        startPage();
        return getDataTable(service.selectFoKnowledgeList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoKnowledgeById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoKnowledge entity) {
        return toAjax(service.insertFoKnowledge(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoKnowledge entity) {
        return toAjax(service.updateFoKnowledge(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoKnowledgeByIds(ids));
    }
}