package cn.cloudmusic.singer_page.web.servlet;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.singer_page.service.SingerService;
import cn.cloudmusic.singer_page.service.impl.SingerServiceImpl;
import cn.cloudmusic.user.domain.User;
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

@WebServlet(name = "SingerServlet",urlPatterns = "/SingerServlet")
public class SingerServlet extends BaseServlet {

    private SingerService singerService = new SingerServiceImpl();

    /**
     * 查询热门前50首单曲
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Music> musicList = singerService.findAll(request.getParameter("id"));
//        request.setAttribute("musicList",musicList);
        response.getWriter().write(JSONArray.fromObject(musicList).toString());
        return "";
    }

    /**
     * 加载每个专辑的音乐
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Album album = singerService.loadAlbum(request.getParameter("aid"));
//        request.setAttribute("album",album);
        response.getWriter().write(JSONObject.fromObject(album).toString());
        return "";
    }

    /**
     * 查询歌手的所有专辑
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAllAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pc = getPc(request);
        int ps = 12;
        PageBean<Album> pb = singerService.findAllAlbum(request.getParameter("id"),pc,ps);
        pb.setUrl(getUrl(request));
        response.getWriter().write(JSONObject.fromObject(pb).toString());
//        request.setAttribute("pb",pb);
        return "";
    }

    /**
     * 加载歌手简介
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadIntro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = singerService.loadIntro(request.getParameter("id"));
//        request.setAttribute("text",text);
        response.getWriter().write(text);
        return "";
    }
    public String addMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mid = request.getParameter("mid");
        User user = (User) request.getSession().getAttribute("session_user");
        Boolean flag = singerService.addMusic(mid,user.getUid());
        PrintWriter pw = response.getWriter();
        if (flag){
            pw.write("加入成功");
        }else {
            pw.write("加入失败");
        }
        return "";
    }
    private int getPc(HttpServletRequest request) {
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
