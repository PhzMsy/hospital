package org.msy.hospital.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Data
public class BaseEntity implements Serializable {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("创建时间")
    @TableField("create_Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;
    @ApiModelProperty(value = "逻辑删除(1:已删除,0:未删除)")
    @TableField("is_deleted")
    private Integer isDelete;
    @ApiModelProperty("其他参数")
    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
}
