package com.ycjcjy.gene.web.action.server;

import com.ycjcjy.gene.VO.ServerManagerVo;
import com.ycjcjy.gene.service.ServerManagerService;
import com.ycjcjy.gene.web.action.system.util.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * version V1.0
 * class_name: $METHOD_NAME$
 * param: $METHOD_PARAM$
 * describe: TODO
 * creat_user: chenjie
 * creat_time: 2018/5/28
 **/
@Controller
@RequestMapping("server")
public class ServerController {

    @Autowired
    private ServerManagerService serverManagerService;

    private Logger  logger = LoggerFactory.getLogger(ServerController.class);

    @RequestMapping("getById")
    @ResponseBody
    public ResponseBean toGetById(Long id){
        ResponseBean responseBean = new ResponseBean();
        try{
           List<ServerManagerVo> serverManagerVos = serverManagerService.getServerForWeb(id);
           responseBean.setSuccess(serverManagerVos);
        }catch (Exception e){
            responseBean.setError();
         logger.error(e.getMessage(),e);
        }
        return responseBean;
    }
}
