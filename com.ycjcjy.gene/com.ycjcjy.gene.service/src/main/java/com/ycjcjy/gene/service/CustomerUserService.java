package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.CustomerUser;

import java.util.List;
import java.util.Map;

public interface CustomerUserService extends IBaseBiz<CustomerUser> {


    public void updateGiftBalanceById(Double gift_balance, Long id);

    public CustomerUser findByTel(String mobile);
    public CustomerUser getUserByOpenid(String openId);

    public Integer getAllCount(CustomerUser customerUser);

    public List<CustomerUser> findCustomerUsers(CustomerUser customerUser);

    int addPinCode(Map<String, Object> par);

    Map<String,Object> getPinCode(Map<String, Object> map);


    /**
     * 付钱，-1 钱不够
     * @param balance
     * @param id
     * @return
     */
    public int cutMoney(Double balance, Long id);

    Double findActual_balanceById(Long user_id);

    Double findGift_balanceById(Long user_id);

    void updateTelByOpenId(String tel, String openId);

    Map<String,Object> getCountByDay();
}


