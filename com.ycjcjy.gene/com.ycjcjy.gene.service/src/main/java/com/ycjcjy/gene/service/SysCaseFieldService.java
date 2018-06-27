package com.ycjcjy.gene.service;
import com.ycjcjy.gene.model.SysCaseField;
import net.onebean.core.IBaseBiz;

import java.util.List;

public interface SysCaseFieldService extends IBaseBiz<SysCaseField> {
    public SysCaseField findByCaseFieldName(String casefieldname);

    public void deleteById(int id);

    public int updateAvailById(Double avail,Long id);

    SysCaseField findByFileldId(String s);

    int cutMoney(Double avail,Long id);

    Integer getAllCount(SysCaseField sysCaseField);

    List<SysCaseField> findSysCaseFields(SysCaseField sysCaseField);

    List<SysCaseField> findByCaseids(String[] id);
}