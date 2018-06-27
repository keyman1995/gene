package com.ycjcjy.gene.dao;

import com.ycjcjy.gene.VO.ServerManagerVo;
import net.onebean.core.BaseDao;
import com.ycjcjy.gene.model.ServerManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author chenjie
* @description 5A服务 Dao
* @date 2018-05-28 09:26:17
*/
public interface ServerManagerDao extends BaseDao<ServerManager> {

    List<ServerManagerVo> findForWeb(@Param(value = "id")Long id);

    void updateStatus(@Param(value = "id") Long id,@Param(value = "status") String status);

    List<ServerManager> findAllForWeb();

}