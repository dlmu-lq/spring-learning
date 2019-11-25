package top.itlq.spring.test.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private UserSession userSession;

    @Autowired
    public UserService(UserSession userSession){
        this.userSession = userSession;
    }

    public String testRequestParam(){
        // 获得绑定在线程上的request
        HttpServletRequest request = (HttpServletRequest)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_REQUEST);
        return request.getParameter("test");
    }

    public String testSessionAttr(){
        return userSession.testSession();
    }
}
