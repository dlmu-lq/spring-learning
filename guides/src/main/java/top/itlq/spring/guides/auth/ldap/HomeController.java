package top.itlq.spring.guides.auth.ldap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth-ldap")
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "Welcome to the home page!";
    }
}
