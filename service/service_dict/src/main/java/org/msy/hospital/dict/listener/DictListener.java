package org.msy.hospital.dict.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.msy.hospital.dict.mapper.DictMapper;
import org.msy.hospital.model.dict.Dict;
import org.msy.hospital.vo.dict.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author 11612
 * @date 2023/3/23
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;
    public DictListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
