package org.msy.hospital.model.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.msy.hospital.model.base.BaseMongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 11612
 * @date 2023/3/27
 */
@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseMongoEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "医院编号")
    @Indexed //普通索引
    private String hoscode;
    @ApiModelProperty(value = "科室编号")
    @Indexed(unique = true) //唯一索引
    private String depcode;
    @ApiModelProperty(value = "科室名称")
    private String depname;
    @ApiModelProperty(value = "科室描述")
    private String intro;
    @ApiModelProperty(value = "大科室编号")
    private String bigcode;
    @ApiModelProperty(value = "大科室名称")
    private String bigname;

}
