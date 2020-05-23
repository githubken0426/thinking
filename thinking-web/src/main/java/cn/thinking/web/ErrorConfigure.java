package cn.thinking.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回404页面
 */
@Controller
@RequestMapping("/error")
public class ErrorConfigure implements ErrorController {

    @Override
    public String getErrorPath() {
        return "404";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
