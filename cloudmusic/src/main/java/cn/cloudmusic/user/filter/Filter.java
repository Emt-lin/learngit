package cn.cloudmusic.user.filter;

import cn.cloudmusic.user.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter",urlPatterns = {""})
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User)request.getSession().getAttribute("session_user");
        if (user != null){
            chain.doFilter(req, resp);
        }else{
            resp.getWriter().write("您还没有登录!");
            request.getRequestDispatcher("").forward(request,response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
