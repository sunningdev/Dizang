package com.ruoyi.web.controller.dizang;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.dizang.FoArticle;
import com.ruoyi.system.service.dizang.IFoArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dizang/" + "article")
public class FoArticleController extends BaseController {

    @Autowired
    private IFoArticleService service;

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/list")
    public TableDataInfo list(FoArticle entity) {
        startPage();
        return getDataTable(service.selectFoArticleList(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(service.selectFoArticleById(id));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody FoArticle entity) {
        return toAjax(service.insertFoArticle(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody FoArticle entity) {
        return toAjax(service.updateFoArticle(entity));
    }

    @PreAuthorize("@ss.hasPermi('')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(service.deleteFoArticleByIds(ids));
    }
}