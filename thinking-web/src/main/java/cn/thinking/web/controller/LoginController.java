package cn.thinking.web.controller;

import cn.thinking.web.entity.User;
import cn.thinking.web.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 11:33
 * @Description:
 */
@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    public String login(@ModelAttribute @Valid User user, BindingResult binding, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String account = user.getAccount();
        User loginUser = userService.selectByAccount(account);
        if (loginUser == null) {//账号错误
            binding.rejectValue("account", "accountError", "您输入的账号不存在");
            return "login";
        }
        String password = user.getPassword();
        if (password.length() < 6) //密码长度小于6
            return "login";
        if (!password.equals(loginUser.getPassword())) { //密码错误
            binding.rejectValue("password", "passwordError", "您输入的密码有误");
            return "login";
        }
       /* Role role = roleService.selectByPrimaryKey(loginUser.getRoleId());
		if (role != null) {
			String[] functionIds = role.getFunctionArray();
			List<String> functionList = (functionIds != null) ? Arrays.asList(functionIds) : new ArrayList<String>();
			List<String> pageMenus =functionService.selectAllFunctionIds();
			for (String menu : pageMenus) {
				session.setAttribute("menu" + menu, functionList.contains(menu));
			}
		}*/
        session.setAttribute("loginUser", loginUser);
        return "redirect:/home";
    }

    @GetMapping(value = "/logout")
    public String logout(@ModelAttribute User user,HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        session.invalidate();
        return "login";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }
    @GetMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 修改密码
     * @param account
     * @param password
     * @return
     */
    @PostMapping(value = "/password/change")
    public void changePassword(@RequestParam(value = "account") String account,
                               @RequestParam(value = "validatePwd") String password,
                               HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setPassword(password);
        int result = userService.updateByPrimaryKey(loginUser);
        response.getWriter().write(result+"");
    }
}
