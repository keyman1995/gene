package com.ycjcjy.gene.dao;

import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.ServerLinkCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author chenjie
* @description 专题课程 Dao
* @date 2018-05-30 19:59:09
*/
public interface ServerLinkCourseDao extends BaseDao<ServerLinkCourse> {


   List<ServerLinkCourse> selectLinkCourseByServerId(@Param(value = "serverId")Long serverId);

}