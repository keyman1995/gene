package com.ycjcjy.gene.web.action.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 0neBean
 */
@Controller
public class LoginController {

	public static String ERROR_SESSION_MSG_KEY = "SPRING_SECURITY_LAST_EXCEPTION";

		@RequestMapping({"/login"})
		public String view(HttpServletRequest request,Model model) {
			HttpSession session = request.getSession();
			if (null != session.getAttribute(ERROR_SESSION_MSG_KEY)) {
				String errorMsg = session.getAttribute(ERROR_SESSION_MSG_KEY).toString();
				if (null != errorMsg){
					model.addAttribute("errorMsg", errorMsg);
					session.removeAttribute(ERROR_SESSION_MSG_KEY);
				}

			}
			return "login/view";
		}
}
	