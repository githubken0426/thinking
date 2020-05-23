package cn.thinking.signature.tool.controller;

import cn.thinking.signature.tool.enumration.PageEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping(value ={"/", "/login"} )
    public ModelAndView login() {
        return new ModelAndView(PageEnum.LOGIN.getName());
    }

    @GetMapping("/main")
    public ModelAndView loginSuccess() {
        return new ModelAndView(PageEnum.MAIN.getName());
    }

    @GetMapping("/oauth")
    public ModelAndView oauth() {
        return new ModelAndView(PageEnum.OAUTH.getName());
    }

    @GetMapping("/signature")
    public ModelAndView signature() {
        return new ModelAndView(PageEnum.SIGNATURE.getName());
    }
}
