package com.ycjcjy.gene.service;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.SubCourseManager;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SubCourseManagerService extends IBaseBiz<SubCourseManager> {

    public void batchSave(List<SubCourseManager> courseManagers);

    public Map<String,Object> findBySubId(Integer id);

    public void deleteBySubId(Integer id);

    public void updateBySub(SubCourseManager subCourseManager);

    public List<SubCourseManager> getAllSubCourses(Date date,Long id);

    public int getSubCounts(Integer id);

    public int getNowCounts(Date nowDate,Integer id);

    public Long saveSubCouseManager(SubCourseManager subCourseManager);

    public void deleteByPid(Integer pid);

    List<SubCourseManager> getSubCourseById(Integer id);

    List<SubCourseManager> delayList(SubCourseManager subCourseManager);

    Integer delayListCount(SubCourseManager subCourseManager);

    Map<String,Object> findCountByDay(String caseid);

    Integer findCountByMonth(String case_id);

    List<Map<String,Object>> findAllNum(Integer dateType,String caseId);
}