package com.ycjcjy.gene.service;

import com.ycjcjy.gene.VO.ServerManagerVo;
import net.onebean.core.IBaseBiz;
import com.ycjcjy.gene.model.ServerManager;

import java.util.List;


/**
* @author chenjie
* @description 5A服务 service
* @date 2018-05-28 09:26:17
*/
public interface ServerManagerService extends IBaseBiz<ServerManager> {


    List<ServerManagerVo> getServerForWeb(Long id);

    void updateStatus(Long id,String status);

    List<ServerManager> getAllManager();

}