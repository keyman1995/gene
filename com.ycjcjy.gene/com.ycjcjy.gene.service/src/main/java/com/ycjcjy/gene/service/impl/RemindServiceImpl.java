package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.dao.RemindDao;
import com.ycjcjy.gene.model.Remind;
import com.ycjcjy.gene.service.RemindService;
import net.onebean.core.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindServiceImpl extends BaseBiz<Remind, RemindDao> implements RemindService{

    @Override
    public List<Remind> findReminds(Remind remind) {
        return baseDao.findReminds(remind);
    }

    @Override
    public int findCounts(Remind remind) {
        return baseDao.findCounts(remind);
    }
}