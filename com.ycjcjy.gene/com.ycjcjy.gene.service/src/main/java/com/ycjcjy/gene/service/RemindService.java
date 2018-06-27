package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.Remind;

import java.util.List;

public interface RemindService extends IBaseBiz<Remind> {

    List<Remind> findReminds(Remind remind);

    int findCounts(Remind remind);
}