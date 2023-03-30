package org.msy.hospital.dict.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.msy.hospital.dict.listener.DictListener;
import org.msy.hospital.dict.mapper.DictMapper;
import org.msy.hospital.dict.service.DictService;
import org.msy.hospital.model.dict.Dict;
import org.msy.hospital.vo.dict.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11612
 * @date 2023/3/23
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> findChildrenData(Long id) {
        // 根据父id查询子数据
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dicts = baseMapper.selectList(wrapper);
        // 为hasChildren属性赋值,前端需要的
        for (Dict dict : dicts) {
            Long id1 = dict.getId();
            boolean isChild = this.isChildren(id1);
            dict.setHasChildren(isChild);
        }
        return dicts;
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
// 设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
// 设置头信息：以下载方式打开
            response.setHeader("Content-disposition", "attachment;filename=" +
                    fileName + ".xlsx");
            List<Dict> dicts = baseMapper.selectList(null);
            /*System.out.println(dicts);*/
            List<DictEeVo> exportList = new ArrayList<>();
            for (Dict dict : dicts) {
                DictEeVo edv = new DictEeVo();
                BeanUtils.copyProperties(dict, edv);
                exportList.add(edv);
            }
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(exportList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DictEeVo.class, new
                            DictListener(baseMapper))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isChildren(Long id1) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id1);
        Long aLong = baseMapper.selectCount(wrapper);
        return aLong > 0;
    }

    @Override
    public String getDictName(String dictCode, String value) {
        if (StringUtils.hasLength(dictCode)) {
            /*Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>
                    ().eq("dict_code", dictCode));*/
            Dict dict = this.getDictByDictCode(dictCode);
            Long parentId = dict.getId();
            Dict resultDict = baseMapper.selectOne(new QueryWrapper<Dict>()
                    .eq("parent_id", parentId)
                    .eq("value", value));
            return resultDict.getName();
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            wrapper.eq("value", value);
            Dict dict = baseMapper.selectOne(wrapper);
            return dict.getName();
        }
    }

    private Dict getDictByDictCode(String dictCode) {
        Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>
                ().eq("dict_code", dictCode));
        return dict;
    }
    // 省份查询
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        Dict dict = getDictByDictCode(dictCode);
        return findChildrenData(dict.getId());
    }


}
