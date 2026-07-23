package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoKnowledgeCategory;
import com.ruoyi.system.service.dizang.IFoKnowledgeCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "knowledgeCategory")
public class FoKnowledgeCategoryController extends BaseController {

    @Autowired
    private IFoKnowledgeCategoryService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoKnowledgeCategory entity) {
        startPage();
        return getDataTable(service.selectFoKnowledgeCategoryList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoKnowledgeCategoryById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoKnowledgeCategory entity) {
        return toAjax(service.insertFoKnowledgeCategory(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoKnowledgeCategory entity) {
        return toAjax(service.updateFoKnowledgeCategory(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "佛教知识分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoKnowledgeCategoryByIds(ids));
    }
}