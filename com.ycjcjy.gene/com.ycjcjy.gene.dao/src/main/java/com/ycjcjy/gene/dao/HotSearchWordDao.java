package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.HotSearchWord;

import java.util.List;

public interface HotSearchWordDao extends BaseDao<HotSearchWord> {
    public Integer getAllCount(HotSearchWord hotSearchWord);

    public List<HotSearchWord> findHotSearchWords(HotSearchWord hotSearchWord);

    List<HotSearchWord> findAllHotSearchWords();
}