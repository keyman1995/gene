package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.common.CDateUtil;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.SubCourseManager;
import com.ycjcjy.gene.service.SubCourseManagerService;
import com.ycjcjy.gene.dao.SubCourseManagerDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SubCourseManagerServiceImpl extends BaseBiz<SubCourseManager, SubCourseManagerDao> implements SubCourseManagerService{

    @Override
    public void batchSave(List<SubCourseManager> courseManagers) {

    }

    @Override
    public Map<String, Object> findBySubId(Integer id) {
        return baseDao.findSubCourse(id);
    }

    @Override
    public void deleteBySubId(Integer id) {
        baseDao.deleteByCourseId(id);
    }

    @Override
    public void updateBySub(SubCourseManager subCourseManager) {
        baseDao.updateBySubId(subCourseManager);
    }

    @Override
    public List<SubCourseManager> getAllSubCourses(Date date,Long id) {
        return baseDao.findSubCourses(date,id);
    }

    @Override
    public int getSubCounts(Integer id) {
        return baseDao.findCountByCircle(id);
    }

    @Override
    public int getNowCounts(Date nowDate, Integer id) {
        return baseDao.getCountFromNow(nowDate,id);
    }

    @Override
    public Long saveSubCouseManager(SubCourseManager subCourseManager) {
        return null;
    }

    @Override
    public void deleteByPid(Integer pid) {
        baseDao.deleteByPid(pid);
    }

    @Override
    public List<SubCourseManager> getSubCourseById(Integer id){
        return baseDao.getSubCourseById(id);
    }

    @Override
    public List<SubCourseManager> delayList(SubCourseManager subCourseManager){return baseDao.delayList(subCourseManager);}

    @Override
    public Integer delayListCount(SubCourseManager subCourseManager){return baseDao.delayListCount(subCourseManager);}

    @Override
    public Map<String, Object> findCountByDay(String caseid) {
        return baseDao.selectSubCountByDay(caseid);
    }

    @Override
    public Integer findCountByMonth(String case_id){
        return baseDao.findCountByMonth(case_id);
    }

    @Override
    public List<Map<String,Object>> findAllNum(Integer dateType,String caseId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SubCourseManager subCourseManager = new SubCourseManager();
        subCourseManager.setCaseId(caseId);
        if(dateType==0){
            subCourseManager.setStart_time(sdf.format(CDateUtil.getBeginDayOfYesterday()));
            subCourseManager.setEnd_time(sdf.format(CDateUtil.getEndDayOfYesterDay()));
        }else if(dateType==1){
            subCourseManager.setStart_time(sdf.format(CDateUtil.getBeginDayOfWeek()));
            subCourseManager.setEnd_time(sdf.format(CDateUtil.getEndDayOfWeek()));
        }else{
            subCourseManager.setStart_time(sdf.format(CDateUtil.getBeginDayOfMonth()));
            subCourseManager.setEnd_time(sdf.format(CDateUtil.getEndDayOfMonth()));
        }
        return baseDao.findAllNum(subCourseManager);
    }
}