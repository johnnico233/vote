package vote.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession httpSession=request.getSession();
        if(httpSession.getAttribute("userID")==null){
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("userID")){
                        httpSession.setAttribute("userID",Integer.parseInt(cookie.getValue()));
                        break;
                    }
                }
            }
        }
        filterChain.doFilter(request,servletResponse);
    }
}
