package com.ycjcjy.gene.dao;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.SysCaseField;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCaseFieldDao extends BaseDao<SysCaseField> {
    public SysCaseField findByCaseFieldName(@Param("casefieldname")String casefieldname);

    public void deleteById(@Param("id")int id);

    public int updateAvailById(@Param("avail")Double avail,@Param("id")Long id);

    public int cutMoney(@Param("avail")Double avail,@Param("id")Long id);
    SysCaseField findByFileldId(String s);

    Integer getAllCount(SysCaseField sysCaseField);

    List<SysCaseField> findSysCaseFields(SysCaseField sysCaseField);

    List<SysCaseField> findByCaseIds(@Param(value = "caseids")String... caseids);
}