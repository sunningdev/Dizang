package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fo_master")
public class Master extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String avatar;
    private String bio;
    private Integer sortOrder;
}