package org.msy.hospital.vo.hosp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 11612
 * @date 2023/3/28
 */
@Data
@ApiModel(description = "Schedule")
public class ScheduleQueryVo {
    @ApiModelProperty(value = "医院编号")
    private String hoscode;

    @ApiModelProperty(value = "科室编号")
    private String depcode;

    @ApiModelProperty(value = "医生编号")
    private String doccode;

    @ApiModelProperty(value = "安排日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    @ApiModelProperty(value = "安排时间（0：上午 1：下午）")
    private Integer workTime;
}
