package org.msy.hospital.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.msy.hospital.model.dict.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 11612
 * @date 2023/3/23
 */
public interface DictService extends IService<Dict> {

    List<Dict> findChildrenData(Long id);

    void exportExcel(HttpServletResponse response);


    void importData(MultipartFile file);
}
