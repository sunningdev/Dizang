package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fo_classic_chapter")
public class ClassicChapter extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long classicId;
    private Long parentId;
    private String title;
    private String content;
    private Integer sortOrder;
}