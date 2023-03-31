package org.msy.hospital.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 11612
 * @date 2023/3/31
 */
@Data
@ApiModel(description="登录对象")
public class LoginVo {
    @ApiModelProperty(value = "openid")
    private String openid;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String code;
    @ApiModelProperty(value = "IP")
    private String ip;

}
