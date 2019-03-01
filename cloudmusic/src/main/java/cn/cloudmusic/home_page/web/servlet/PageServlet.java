package cn.cloudmusic.home_page.web.servlet;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;
import cn.cloudmusic.home_page.service.PageService;
import cn.cloudmusic.home_page.service.impl.PageServiceImpl;
import myUtils.BaseServlet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PageServlet",urlPatterns = "/PageServlet")
public class PageServlet extends BaseServlet {

    //service的单例
    private PageService pageService = PageServiceImpl.getInstance();

    /**
     *  查询音乐
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String search_M(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) return "";
        int pc = getPc(request);
        int ps = 5;
        PageBean<Music> pb_m = pageService.findByName_m(name,pc,ps);
        //得到url
        pb_m.setUrl(getUrl(request));
        PrintWriter pw = response.getWriter();
        if (pb_m.getTr() != 0){
            String m = JSONObject.fromObject(pb_m).toString();
            pw.write(m);
        }else {
            pw.write("没有相关的音乐");
        }

        return "";
    }

    /**
     * 查询歌手
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
        public String search_S(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) return "f:/index.jsp";
        int pc = getPc(request);
        int ps = 5;
        PageBean<Singer> pb_s = pageService.finByName_s(name,pc,ps);
        pb_s.setUrl(getUrl(request));

        PrintWriter pw = response.getWriter();
        if (pb_s.getTr() != 0){
            String s = JSONObject.fromObject(pb_s).toString();
            pw.write(s);
        }else {
            pw.write("没有相关的歌手");
        }
        return "";
    }

    /**
     * 查询专辑
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String search_A(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) return "";
        int pc = getPc(request);
        int ps = 5;
        PageBean<Album> pb_a = pageService.finByName_a(name,pc,ps);
        pb_a.setUrl(getUrl(request));

        PrintWriter pw = response.getWriter();
        if (pb_a.getTr() != 0){

            String a = JSONObject.fromObject(pb_a).toString();
            pw.write(a);
        }else {
            pw.write("没有相关的专辑");
        }
        return "";
    }
    /**
     * 加载音乐
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Music> musicList = pageService.loadMusic(request.getParameter("id"));
        String json = JSONArray.fromObject(musicList).toString();
        if (json != null){
            response.getWriter().write(json);
        }else {
            response.getWriter().write("加载出错");
        }
        return "";
    }
    private int getPc(HttpServletRequest request){
        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }
    private String getUrl(HttpServletRequest request){
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();
        if (queryString.contains("&pc=")){
            int index = queryString.indexOf("&pc=");
            queryString = queryString.substring(0,index);
        }
        return contextPath + servletPath + "?" + queryString;
    }
}
