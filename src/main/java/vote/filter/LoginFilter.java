package vote.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("fliter now");
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession httpSession=request.getSession();
        boolean isUserExist=true;
        if(httpSession.getAttribute("userID")==null){
            isUserExist=false;
        }
        if(!isUserExist){
            String url=request.getRequestURI().substring(0,request.getRequestURI().indexOf("/",1));
            response.sendRedirect(url+"/signIn");
        }else{
            String url=request.getRequestURI().substring(request.getRequestURI().indexOf("/",1));
            request.getRequestDispatcher(url).forward(servletRequest,servletResponse);
        }
    }
}
