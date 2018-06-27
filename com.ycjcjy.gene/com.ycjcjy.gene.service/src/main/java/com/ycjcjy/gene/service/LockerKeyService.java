package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.SysUser;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.LockerKey;

import java.util.List;

public interface LockerKeyService extends IBaseBiz<LockerKey> {
    public List<Integer> findByCaseFieldId(Integer case_field_id);
    public boolean containsNum(Integer num, List<Integer> list);
    public boolean containsId(Long num, List<Long> list);
    Integer findUserId(Long case_field_id,Long userId);
    public Integer keyBindCard(String id,String userId);
    public String keyBindCoach(String id, String userId, SysUser sysUser);
}