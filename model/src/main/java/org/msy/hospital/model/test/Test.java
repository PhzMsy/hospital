package org.msy.hospital.model.test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 11612
 * @date 2023/3/25
 */
@Data
@ApiModel(description = "测试数据")
@TableName("test")
public class Test{
    @ApiModelProperty("id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;
}
