package com.ycjcjy.gene.service.impl;

import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.dao.DicDictionaryDao;
import com.ycjcjy.gene.model.DicDictionary;
import com.ycjcjy.gene.service.DicDictionaryService;
import org.springframework.stereotype.Service;

@Service
public class DicDictionaryServiceImpl extends BaseBiz<DicDictionary, DicDictionaryDao> implements DicDictionaryService {

    @Override
    public Integer findGroupOrderNextNum(String code) {
        return baseDao.findGroupOrderNextNum(code);
    }

    @Override
    public String selectVal(String code) {
        return baseDao.selectValByCode(code);
    }
}