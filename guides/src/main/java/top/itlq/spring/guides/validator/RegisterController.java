package top.itlq.spring.guides.validator;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("validator")
public class RegisterController {
    @PostMapping("/register.do")
    public ResponseEntity register(@RequestBody @Valid UserEntity userEntity, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors("name")){
            return ResponseEntity.badRequest().body("name 长度限制：2-30");
        }else if(bindingResult.hasFieldErrors("age")){
            return ResponseEntity.badRequest().body("age 必须大于或等于18");
        }
        return ResponseEntity.ok("success");
    }
}
