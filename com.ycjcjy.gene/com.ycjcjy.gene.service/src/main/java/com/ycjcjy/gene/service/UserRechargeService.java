package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.UserRecharge;
import net.onebean.core.PageResult;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface UserRechargeService extends IBaseBiz<UserRecharge> {
    public void add(UserRecharge entity);



    public void updateStatusById(String status, Long id);

    public UserRecharge findByUserRechargeId(Integer id);

    public UserRecharge findById(Integer id);

    public void updateIdentifyingcodeById(String identifyingcode, Long id,Timestamp update_time);

    public List<UserRecharge> findByType(String type);

    public Integer getAllCount(UserRecharge userRecharge);

    public List<UserRecharge> findUserRecharges(UserRecharge userRecharge);

    PageResult<UserRecharge> savecasefieldrecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request);

    PageResult<UserRecharge> resend(Integer id,PageResult<UserRecharge> result);

    Map<String,Object> cancel(Integer id);

    PageResult<UserRecharge> savebackstagerecharge(Model model, UserRecharge entity, PageResult<UserRecharge> result, HttpServletRequest request);

    Map<String,Object> resend1(Integer id);

    UserRecharge insertCustomeruserRecharge(Integer user_id,Double price,Double gift_price);

    void updateStatusByOrderId(UserRecharge userRecharge);
}