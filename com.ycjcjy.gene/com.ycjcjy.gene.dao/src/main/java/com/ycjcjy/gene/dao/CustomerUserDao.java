package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.CustomerUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerUserDao extends BaseDao<CustomerUser> {
    public void updateGiftBalanceById(@Param("gift_balance") Double gift_balance, @Param("id") Long id);

    public void updateBalanceById(@Param("gift_balance") Double gift_balance, @Param("actual_balance") Double actual_balance, @Param("id") Long id);

    public CustomerUser findByTel(@Param("mobile") String mobile);
    public CustomerUser getUserByOpenid(@Param("openId") String openId);

    public Integer getAllCount(CustomerUser customerUser);

    public List<CustomerUser> findCustomerUsers(CustomerUser customerUser);

    int addPinCode(Map<String, Object> par);

    Map<String,Object> getPinCode(Map<String, Object> map);

    Double findGift_balanceById(Long user_id);

    Double findActual_balanceById(Long user_id);

    void updateTelByOpenId(@Param("tel")String tel,@Param("openId") String openId);

    Map<String,Object>findTotalAndNow();
}