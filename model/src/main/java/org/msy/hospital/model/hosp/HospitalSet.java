package org.msy.hospital.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.msy.hospital.model.base.BaseEntity;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Data
@ApiModel(description = "医院设置")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "医院名称")
    @TableField("hosname")
    private String hosname;
    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hoscode;
    @ApiModelProperty(value = "api基础路径")
    private String apiUrl;
    @ApiModelProperty(value = "签名密钥")
    private String signKey;
    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;
    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;
}
