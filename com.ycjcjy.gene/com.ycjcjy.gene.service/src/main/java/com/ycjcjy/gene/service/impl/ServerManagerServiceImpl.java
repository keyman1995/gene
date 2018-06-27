package com.ycjcjy.gene.service.impl;
import com.ycjcjy.gene.VO.ServerManagerVo;
import org.springframework.stereotype.Service;
import net.onebean.core.BaseBiz;
import com.ycjcjy.gene.model.ServerManager;
import com.ycjcjy.gene.service.ServerManagerService;
import com.ycjcjy.gene.dao.ServerManagerDao;

import java.util.List;

/**
* @author chenjie
* @description 5A服务 serviceImpl
* @date 2018-05-28 09:26:17
*/
@Service
public class ServerManagerServiceImpl extends BaseBiz<ServerManager, ServerManagerDao> implements ServerManagerService{

    @Override
    public List<ServerManagerVo> getServerForWeb(Long id) {
        return baseDao.findForWeb(id);
    }

    @Override
    public void updateStatus(Long id, String status) {
        baseDao.updateStatus(id,status);
    }

    @Override
    public List<ServerManager> getAllManager() {
        return baseDao.findAllForWeb();
    }
}