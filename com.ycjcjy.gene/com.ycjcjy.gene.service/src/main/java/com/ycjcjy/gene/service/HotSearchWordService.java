package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.HotSearchWord;

import java.util.List;

public interface HotSearchWordService extends IBaseBiz<HotSearchWord> {
    public Integer getAllCount(HotSearchWord hotSearchWord);

    public List<HotSearchWord> findHotSearchWords(HotSearchWord hotSearchWord);

    List<HotSearchWord> findAllHotSearchWords();
}