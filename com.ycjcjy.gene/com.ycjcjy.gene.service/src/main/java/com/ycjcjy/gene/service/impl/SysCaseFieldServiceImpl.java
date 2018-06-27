package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.dao.SysCaseFieldDao;
import com.ycjcjy.gene.model.SysCaseField;
import com.ycjcjy.gene.service.SysCaseFieldService;
import net.onebean.core.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCaseFieldServiceImpl extends BaseBiz<SysCaseField, SysCaseFieldDao> implements SysCaseFieldService{
    @Autowired
    private SysCaseFieldDao sysCaseFieldDao;

    @Override
    public SysCaseField findByCaseFieldName(String casefieldname) {
        return sysCaseFieldDao.findByCaseFieldName(casefieldname);
    }

    @Override
    public void deleteById(int id) {
        sysCaseFieldDao.deleteById(id);
    }

    @Override
    public int updateAvailById(Double avail,Long id) {
       return sysCaseFieldDao.updateAvailById(avail,id);
    }

    @Override
    public SysCaseField findByFileldId(String s) {
        return sysCaseFieldDao.findByFileldId(s);
    }

    @Override
    public int cutMoney(Double avail, Long id) {
        SysCaseField caseField =  sysCaseFieldDao.findByFileldId(String.valueOf(id));
        double allMoney = caseField.getAvail();
        if(allMoney<avail){
            return -1;
        }
        return sysCaseFieldDao.cutMoney(avail,id);
    }

    @Override
    public Integer getAllCount(SysCaseField sysCaseField) {
        return baseDao.getAllCount(sysCaseField);
    }

    @Override
    public List<SysCaseField> findSysCaseFields(SysCaseField sysCaseField) {
        return baseDao.findSysCaseFields(sysCaseField);
    }

    @Override
    public List<SysCaseField> findByCaseids(String[] id) {
        return baseDao.findByCaseIds(id);
    }
}