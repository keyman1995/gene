package com.ycjcjy.gene.web.action.serverdetail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycjcjy.gene.model.CourseDetail;
import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import net.onebean.core.PageResult;
import net.onebean.core.Pagination;
import net.onebean.core.extend.Sort;
import com.ycjcjy.gene.core.BaseController;
import com.ycjcjy.gene.model.ServerDetail;
import com.ycjcjy.gene.service.ServerDetailService;
import net.onebean.util.DateUtils;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
* @author chenjie
* @description 5A服务详情 controller
* @date 2018-05-28 11:10:47
*/
@Controller
@RequestMapping("serverdetail")
public class ServerDetailController extends BaseController<ServerDetail,ServerDetailService> {

    @RequestMapping("editDetail")
    @ResponseBody
    public ServerDetail editDetail(String id){
        ServerDetail serverDetail = null;
        if(id!=null && !"".equals(id)){
            serverDetail = baseService.findById(id);
        }
        return serverDetail;
    }



    @RequestMapping("batchSave")
    @ResponseBody
    public Map<String,Object> batchSave(@RequestParam(value = "value")String value, HttpServletRequest request){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("flag",true);
        result.put("msg","上传成功");
        List<ServerDetail> serverDetailList = new ArrayList<ServerDetail>();
        JSONObject jsonObject = JSON.parseObject(value);
        String courseId = jsonObject.getString("serverId");
        String sort = jsonObject.getString("sort");
        JSONArray img_url = jsonObject.getJSONArray("imgs");

        for(int i=0;i<img_url.size();i++){
            ServerDetail serverDetail = new ServerDetail();
            String img = (String)img_url.get(i);
            serverDetail.setServerid(Long.valueOf(courseId));
            serverDetail.setImg_url(img);
            if(sort==null || "".equals(sort)){
                serverDetail.setSort(i);
            }else{
                serverDetail.setSort(Integer.valueOf(sort));
            }
            serverDetailList.add(serverDetail);
        }
        baseService.saveBatch(serverDetailList);
        return result;
    }
}
