package com.ycjcjy.gene.web.action.center;

import com.ycjcjy.gene.model.SysUser;
import com.ycjcjy.gene.security.SpringSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CenterController {
	
	@RequestMapping({"/center",""})
	public String view(HttpServletRequest request, Model model) {
		SysUser currentUser = SpringSecurityUtil.getCurrentLoginUser(request);
		model.addAttribute("current_sys_user",currentUser);
		return "center/bone";
	}

	@RequestMapping("/index")
	public String index() {
		return "center/view";
	}
}


