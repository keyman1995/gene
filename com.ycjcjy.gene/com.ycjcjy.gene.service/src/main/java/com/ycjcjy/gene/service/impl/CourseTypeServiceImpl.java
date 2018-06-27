package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.VO.CourseTypeTree;
import net.onebean.core.Condition;
import net.onebean.core.Pagination;
import net.onebean.core.form.Parse;
import net.onebean.util.CollectionUtil;
import net.onebean.util.StringUtils;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.CourseType;
import com.ycjcjy.gene.service.CourseTypeService;
import com.ycjcjy.gene.dao.CourseTypeDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseTypeServiceImpl extends BaseBiz<CourseType, CourseTypeDao> implements CourseTypeService{

    @Override
    public List<CourseType> findChildSync(Long id) {
        List<CourseType> list = new ArrayList<CourseType>();
        if(id==null){
            Condition condition = Condition.parseCondition("is_root@string@eq$");
            condition.setValue("1");
            CourseType courseType = this.find(new Pagination(),condition).get(0);
            courseType.setChildList(baseDao.findChildSync(courseType.getId()));
            list.add(courseType);
        }else {
            list = baseDao.findChildSync(id);
        }
        return list;
    }

    @Override
    public List<CourseType> findPicPlace(){
        return baseDao.findPicPlace();
    }


    @Override
    public List<CourseTypeTree> courseToCourseTypeTree(List<CourseType> before, Long self_id) {
        List<CourseTypeTree> treeList = new ArrayList<>();
        CourseTypeTree temp;
        if(CollectionUtil.isNotEmpty(before)){
         for(CourseType courseType : before){
             if(null == self_id ||(null !=self_id && Parse.toInt(courseType.getId())!=self_id) || self_id==1){
                 temp = new CourseTypeTree();
                 temp.setTitle(courseType.getName());
                 temp.setId(courseType.getId());
                 temp.setSort(courseType.getSort());
                 temp.setType_img(courseType.getType_img());
                 if(CollectionUtil.isNotEmpty(courseType.getChildList())){
                     temp.setType(CourseTypeTree.TYPE_FOLDER);
                     temp.setChildList(courseToCourseTypeTree(courseType.getChildList(),self_id));
                 }else{
                     temp.setType(CourseTypeTree.TYPE_ITEM);
                 }
                 treeList.add(temp);
                }
            }
        }
        return treeList;
    }


    @Override
    public void save(CourseType entity) {
        super.save(entity);
        entity.setParentids(getParentOrgIdsNotEmpty(entity.getId()));
        super.save(entity);
    }

    @Override
    public void deleteSelfAndChildById(Long id) {
        baseDao.deleteSelfAndChildById(id);
    }

    protected String getParentOrgIdsNotEmpty(Long id){
        String res = baseDao.getParentCtype(id);
        if (StringUtils.isEmpty(res)){
            return  null;
        }
        return  res;
    }

    @Override
    public Integer findChildOrderNextNum(Long parentId) {
        return baseDao.getChildByPId(parentId);
    }

    @Override
    public List<CourseType> findByParentId(int i) {
        return baseDao.findByParentId(i);
    }

    @Override
    public List<String> getAllUnicode(Long id) {
        return baseDao.findUnicode(id);
    }
}