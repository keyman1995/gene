package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CardCourdeBasic;

import java.util.List;

public interface CardCourdeBasicService extends IBaseBiz<CardCourdeBasic> {
    public List<CardCourdeBasic> findCourse(CardCourdeBasic cardCourdeBasic);
    public Integer pageCount(CardCourdeBasic cardCourdeBasic);
    public Integer isOutDate(Integer courseId);
    Integer hasTicket (Integer target_id);

    CardCourdeBasic getById(Long id);
}