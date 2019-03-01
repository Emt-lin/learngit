package cn.cloudmusic.music_page.web.servlet;

import cn.cloudmusic.music_page.domain.Comment;
import cn.cloudmusic.music_page.service.MusicService;
import cn.cloudmusic.music_page.service.impl.MusicServiceImpl;
import cn.cloudmusic.user.domain.User;
import myUtils.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MusicServlet",urlPatterns = "/MusicServlet")
public class MusicServlet extends BaseServlet {
    private MusicService musicService = new MusicServiceImpl();

    /**
     * 插入评论
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String insertComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = ((User) request.getSession().getAttribute("session_user")).getUid();
        String text = request.getParameter("text");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Comment comment = new Comment(pid,uid,text);
        String res = "";
        if (musicService.insertComment(comment)){
            res = "发送成功";
        }else {
            res = "发送失败";
        }
        response.getWriter().write(res);
        return "";
    }

    /**
     * 加载所有的评论
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAllComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comment> commentList = musicService.findAllComments();
        String comments = JSONArray.fromObject(commentList).toString();

        response.getWriter().write(comments);

        return "";
    }
//    public String loadMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
