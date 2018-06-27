package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CardCourdeBasic;
import net.onebean.core.Condition;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardCourdeBasicDao extends BaseDao<CardCourdeBasic> {
    List<CardCourdeBasic> findCourse(CardCourdeBasic cardCourdeBasic);
    Integer pageCount(CardCourdeBasic cardCourdeBasic);

    Integer checkIsOutDate(@Param(value = "course_id")Integer course_id);
    Integer hasTicket (@Param("target_id") Integer target_id);

    CardCourdeBasic findCardCourse(Long id);
}