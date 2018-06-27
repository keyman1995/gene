package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.common.util.NumberArithmeticUtils;
import com.ycjcjy.gene.dao.CustomerUserDao;
import com.ycjcjy.gene.model.CustomerUser;
import com.ycjcjy.gene.service.CustomerUserService;
import net.onebean.core.BaseBiz;
import net.onebean.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class CustomerUserServiceImpl extends BaseBiz<CustomerUser, CustomerUserDao> implements CustomerUserService{

    @Autowired
    private CustomerUserDao customerUserDao;

    @Override
    public void updateGiftBalanceById(Double gift_balance, Long id) {
        baseDao.updateGiftBalanceById(gift_balance,id);
    }

    @Override
    public CustomerUser findByTel(String mobile) {
        return baseDao.findByTel(mobile);
    }

    @Override
    public CustomerUser getUserByOpenid(String openId) {
        return baseDao.getUserByOpenid(openId);
    }

    @Override
    public Integer getAllCount(CustomerUser customerUser) {
        return baseDao.getAllCount(customerUser);
    }

    @Override
    public List<CustomerUser> findCustomerUsers(CustomerUser customerUser) {
        return baseDao.findCustomerUsers(customerUser);
    }

    @Override
    public int cutMoney(Double balance, Long id) {
        CustomerUser user =   baseDao.findById(String.valueOf(id));
        double allMoney = NumberArithmeticUtils.add(user.getActual_balance(),user.getGift_balance()).doubleValue();
        if(balance>allMoney){//没钱
            return -1;
        }
        //判断余额够不够
         double leftMoney =  NumberArithmeticUtils.sub(balance,user.getActual_balance()).doubleValue();

        if(leftMoney>=0){
            baseDao.updateBalanceById(leftMoney,0d,id);
        }else{
            baseDao.updateBalanceById(0d,balance,id);
        }



        return 0;
    }

    @Override
    public Double findActual_balanceById(Long user_id) {
        return baseDao.findActual_balanceById(user_id);
    }

    @Override
    public Double findGift_balanceById(Long user_id) {
        return baseDao.findGift_balanceById(user_id);
    }

    @Override
    public void updateTelByOpenId(String tel, String openId) {
        baseDao.updateTelByOpenId(tel,openId);
    }


    @Override
    public int addPinCode(Map<String, Object> par) {
        par.put("nowTime", DateUtils.getNowyyyy_MM_dd_HH_mm_ss());
        return customerUserDao.addPinCode(par);
    }

    @Override
    public Map<String, Object> getPinCode(Map<String, Object> map) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, -5);
        map.put("nowTime", DateUtils.dateToString(nowTime.getTime()));
        return customerUserDao.getPinCode(map);
    }

    @Override
    public Map<String, Object> getCountByDay() {
        return customerUserDao.findTotalAndNow();
    }
}