package org.msy.hospital.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.msy.hospital.common.result.ResultCodeEnum;

/**
 * @author 11612
 * @date 2023/3/20
 */
@Data
@ApiModel(value = "异常状态码")
public class YyghException extends RuntimeException{
    @ApiModelProperty("异常状态码")
    private Integer code;
    /**
     * 通过状态码和错误消息创建异常对象
     * @param message 错误信息
     * @param code 错误码
     */
    public YyghException(String message,Integer code){
        super(message);
        this.code = code;
    }
    /**
     * 接受枚举类型对象
     * @param resultCodeEnum
     */
    public YyghException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
