package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.UserRecharge;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserRechargeDao extends BaseDao<UserRecharge> {
    public void add(UserRecharge entity);


    public void updateStatusById(@Param("status")String status, @Param("id")Long id);

    public UserRecharge findByUserRechargeId(@Param("id")Integer id);

    public UserRecharge findById(@Param("id")Integer id);

    public void updateIdentifyingcodeById(@Param("identifyingcode")String identifyingcode, @Param("id")Long id,@Param("update_time")Timestamp update_time);

    public List<UserRecharge> findByType(@Param("type")String type);

    public Integer getAllCount(UserRecharge userRecharge);

    public List<UserRecharge> findUserRecharges(UserRecharge userRecharge);

    void insertCustomeruserRecharge(UserRecharge userRecharge);

    void updateStatusByOrderId(UserRecharge userRecharge);
}