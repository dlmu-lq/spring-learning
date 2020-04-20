package top.itlq.spring.mvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.itlq.spring.mvc.model.User;

import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    //    messageConverter
    @RequestMapping("add")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user){
        return ResponseEntity.ok(user.toString());
    }

    @RequestMapping("update")
    public void updateUser(@RequestBody User user, Model model){
        model.addAttribute(user);
    }

    @RequestMapping("print-post-body")
    @ResponseBody
    public String pringPostBody(@RequestBody String object){
        System.out.println(object);
        return object.toString();
    }

    @RequestMapping("print-get-body")
    @ResponseBody
    public String printGetBody(@RequestBody String object){
        System.out.println(object);
        return object.toString();
    }
}
