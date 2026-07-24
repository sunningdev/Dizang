package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fo_teaching")
public class Teaching extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long masterId;
    private String title;
    private String summary;
    private String content;
    private String coverUrl;
    private LocalDateTime publishTime;

    @TableField(exist = false)
    private String masterName;
}