package org.msy.hospital.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Data
public class HospitalSetQueryVo {
    @ApiModelProperty("医院名称")
    private String hosname;
    @ApiModelProperty("医院编号")
    private String hoscode;
}
