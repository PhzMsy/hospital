package org.msy.hospital.model.dict;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/23
 */
@Data
@ApiModel("数据字典")
@TableName("dict")
public class Dict {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("逻辑删除1:已删除 0:未删除")
    @TableLogic
    private Integer isDeleted;
    @ApiModelProperty("其他参数")
    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
    @ApiModelProperty("上级id")
    private Long parentId;
    @ApiModelProperty("名称")
    @TableField("name")
    private String name;
    @ApiModelProperty("值")
    @TableField("value")
    private String value;
    @ApiModelProperty("编码")
    private String dictCode;
    @ApiModelProperty("是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;

}
