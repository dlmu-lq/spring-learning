package top.itlq.spring.configure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecurityTestController {
    @RequestMapping("/open/test.do")
    public ResponseEntity openTest(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/user/test.do")
    public ResponseEntity userTest(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/admin/test.do")
    public ResponseEntity adminTest(){
        return ResponseEntity.ok().build();
    }

}
