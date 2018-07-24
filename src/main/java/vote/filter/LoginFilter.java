package vote.filter;


import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession httpSession=request.getSession();
        boolean isUserExist=false;
        if(httpSession.getAttribute("userID")==null){
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("userID")){
                        httpSession.setAttribute("userID",Integer.parseInt(cookie.getValue()));
                        isUserExist=true;
                        break;
                    }
                }
            }
        }else
            isUserExist=true;
        if(!isUserExist){
            String url=request.getRequestURI().substring(0,request.getRequestURI().indexOf("/",1));
            response.sendRedirect(url+"/signIn");
        }else{
            String url=request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));
            request.getRequestDispatcher(url).forward(servletRequest,servletResponse);
        }
    }
}
