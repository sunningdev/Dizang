package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("fo_article")
public class Article extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private String title;
    private String summary;
    private String content;
    private String coverUrl;
    private Integer type;
    private LocalDateTime publishTime;
}