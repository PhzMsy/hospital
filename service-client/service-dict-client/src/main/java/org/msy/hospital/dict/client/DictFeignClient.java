package org.msy.hospital.dict.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 11612
 * @date 2023/3/28
 */
@Service
@FeignClient("service-dict")
public interface DictFeignClient {
    @GetMapping("/admin/dict/dictionary/getName/{dictCode}/{value}")
    public String getName(@PathVariable("dictCode") String dictCode,@PathVariable("value") String value);
    @GetMapping("/admin/dict/dictionary/getName/{value}")
    public String getName(@PathVariable("value") String value);
}
