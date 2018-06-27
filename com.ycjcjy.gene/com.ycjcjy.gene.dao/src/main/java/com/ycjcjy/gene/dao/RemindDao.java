package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.Remind;

import java.util.List;

public interface RemindDao extends BaseDao<Remind> {
    List<Remind> findReminds(Remind remind);

    int findCounts(Remind remind);
}