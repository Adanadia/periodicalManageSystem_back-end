package top.ahcdc.periodical.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.utils.JWTUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CommonResponse commonResponse;
        String token = request.getHeader("Authorization");//请求头中的token
        try{
            JWTUtils.verify(token);
            //commonResponse = CommonResponse.createForSuccess("请求成功",token);
            return true;
        }catch (Exception e){
            commonResponse = CommonResponse.createForError("token无效");
        }
        String json = new ObjectMapper().writeValueAsString(commonResponse);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}