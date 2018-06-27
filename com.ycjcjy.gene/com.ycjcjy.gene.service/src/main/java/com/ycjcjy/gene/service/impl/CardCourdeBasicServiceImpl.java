package com.ycjcjy.gene.service.impl;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CardCourdeBasic;
import com.ycjcjy.gene.service.CardCourdeBasicService;
import com.ycjcjy.gene.dao.CardCourdeBasicDao;

import java.util.List;

@Service
public class CardCourdeBasicServiceImpl extends BaseBiz<CardCourdeBasic, CardCourdeBasicDao> implements CardCourdeBasicService{

    public List<CardCourdeBasic> findCourse(CardCourdeBasic cardCourdeBasic){return baseDao.findCourse(cardCourdeBasic);}
    public Integer pageCount(CardCourdeBasic cardCourdeBasic){return baseDao.pageCount(cardCourdeBasic);}

    /**
     *
     *  绑定课程的卡是否失效
     * @author chenjie
     * @date 2018/5/18 10:03
     * @param [courseId]
     * @return java.lang.Integer
     */
    @Override
    public Integer isOutDate(Integer courseId) {
        return baseDao.checkIsOutDate(courseId);
    }
    public Integer hasTicket (Integer target_id){
        return baseDao.hasTicket(target_id);
    }

    @Override
    public CardCourdeBasic getById(Long id) {
        return baseDao.findCardCourse(id);
    }
}