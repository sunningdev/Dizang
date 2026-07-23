package com.dizang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("fo_blessing")
public class Blessing {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String nickname;
    private String content;
    private Integer likeCount;
    private Integer auditStatus;
    private String auditBy;
    private LocalDateTime auditTime;
    private String rejectReason;
    private String ipHash;
    private LocalDateTime createTime;
    private String delFlag;
}