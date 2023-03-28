package org.msy.hospital.controller;

import org.msy.hospital.common.exception.YyghException;
import org.msy.hospital.common.result.ResultCodeEnum;
import org.msy.hospital.common.utils.HttpRequestHelper;
import org.msy.hospital.common.utils.MD5;
import org.msy.hospital.service.HospService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 11612
 * @date 2023/3/27
 */
public class BaseController {
    /**
     * 获取请求参数
     */
    public Map<String,Object> getParamMap(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);
        return stringObjectMap;
    }
    /**
     * 签名校验
     */
    public void checkSignKey(Map<String,Object> paramMap, HospService hospService){
        String hoscode = (String)paramMap.get("hoscode");
        if(!StringUtils.hasLength(hoscode)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        String signKey = (String)paramMap.get("sign");
        String utfSignKey = hospService.getSignKey(hoscode);
        try {
            byte[] bytes = utfSignKey.getBytes("UTF-8");
            utfSignKey = new String(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String signKeyMD5 = MD5.encrypt(utfSignKey);
        if(!signKey.equals(signKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }

}

