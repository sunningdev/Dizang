package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fo_topic")
public class Topic extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String icon;
    private String description;
    private Integer sortOrder;
}