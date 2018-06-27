package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.HotSearchWord;
import com.ycjcjy.gene.service.HotSearchWordService;
import com.ycjcjy.gene.dao.HotSearchWordDao;

import java.util.List;

@Service
public class HotSearchWordServiceImpl extends BaseBiz<HotSearchWord, HotSearchWordDao> implements HotSearchWordService{
    @Override
    public Integer getAllCount(HotSearchWord hotSearchWord) {
        return baseDao.getAllCount(hotSearchWord);
    }

    @Override
    public List<HotSearchWord> findHotSearchWords(HotSearchWord hotSearchWord) {
        return baseDao.findHotSearchWords(hotSearchWord);
    }

    @Override
    public List<HotSearchWord> findAllHotSearchWords() {
        return baseDao.findAllHotSearchWords();
    }
}