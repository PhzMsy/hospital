package org.msy.hospital.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Data
@ApiModel(description = "Department")
public class DepartmentVo {
    @ApiModelProperty(value = "医院编号")
    private String hoscode;
    @ApiModelProperty(value = "科室编号")
    private String depcode;
    @ApiModelProperty(value = "科室名称")
    private String depname;
    @ApiModelProperty(value = "大科室编号")
    private String bigcode;
    @ApiModelProperty(value = "大科室名称")
    private String bigname;
}
