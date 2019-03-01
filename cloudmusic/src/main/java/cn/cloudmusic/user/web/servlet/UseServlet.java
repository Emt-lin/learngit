package cn.cloudmusic.user.web.servlet;

import cn.cloudmusic.user.domain.Mail;
import cn.cloudmusic.user.service.impl.UserException;
import cn.cloudmusic.user.domain.User;
import cn.cloudmusic.user.service.UserService;
import cn.cloudmusic.user.service.impl.UserServiceImpl;
import myUtils.BaseServlet;
import myUtils.CommonUtils;
import myUtils.MailUtils;
import net.sf.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User表述层
 */
@WebServlet(name = "UseServlet",urlPatterns = "/UseServlet")
public class UseServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     *  退出功能
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return "";
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        try {
            User user = userService.login(form);
            request.getSession().setAttribute("session_user",user);
            return "";
        } catch (UserException e) {
//            request.setAttribute("msg",e.getMessage());
            PrintWriter pw = response.getWriter();
            pw.write(e.getMessage());
//            request.setAttribute("form",form);
            //回显
            pw.write(JSONObject.fromObject(form).toString());
            return "";
        }
    }

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //一键封装
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
//        form.setUsername(form.getUid());
        /**
         * 输入校验
         */
        Map<String,String> errors = new HashMap<>();
        String uid = form.getUid();
//        if (uid.endsWith("@qq.com")){

            if (uid == null || uid.trim().isEmpty()) {
                errors.put("uid", "请输入QQ邮箱");
            } else if (!uid.matches("[1-9][0-9]{6,9}@qq.com")) {
                errors.put("uid", "请输入正确的QQ邮箱");
            }
            String password = form.getPassword();
            if (password == null || password.trim().isEmpty()) {
                errors.put("password", "请输入登录密码");
            } else if (password.length() < 6 || password.length() > 16) {
                errors.put("password", "请输入6-16位的密码");
            }

//        }else {
//
//            if (uid == null || uid.trim().isEmpty()) {
//                errors.put("uid", "请输入手机号");
//            } else if (!uid.matches("1[0-9]{10}")) {
//                errors.put("uid", "请输入正确的手机号");
//            }
//
//            String code = form.getCode();
//            if (code == null || uid.trim().isEmpty()){
//                errors.put("code","请输入验证码");
//            }else if (!code.equals("")){
//                errors.put("code","验证码错误");
//            }
//        }
        PrintWriter pw = response.getWriter();
        if (errors.size() > 0){
            pw.write(JSONObject.fromObject(errors).toString());
//            request.setAttribute("msg",errors);
//            request.setAttribute("form",form);
            //回显
            pw.write(JSONObject.fromObject(form).toString());
            return "f:/index.jsp";
        }
        try {
            userService.regist(form);

        } catch (UserException e) {
            pw.write(e.getMessage());
//            request.setAttribute("msg",e.getMessage());
//            request.setAttribute("form",form);
            pw.write(JSONObject.fromObject(form).toString());
            return "f:/index.jsp";
        }

        /**
         * 发邮件
         * 准备配置文件
         */
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
        String host = props.getProperty("host");

        String uname = props.getProperty("uname");

        String pwd = props.getProperty("pwd");

        String from = props.getProperty("from");

        String to = form.getUid();

        String subject = props.getProperty("subject");

        String content = props.getProperty("content");

        content = MessageFormat.format(content,form.getCode());//替换{0}占位符



        Session session = MailUtils.createSession(host,uname,pwd);
        Mail mail = new Mail(from,to,subject,content);
        try {
            MailUtils.send(session,mail);//发邮件
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        pw.write("恭喜，注册成功！请马上到邮箱激活");
        return "";
    }
    /**
     * 激活功能
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String code = request.getParameter("code");
        PrintWriter pw = response.getWriter();
        try {
            userService.active(code);
            pw.write("恭喜，您激活成功！请马上登录");
//            request.setAttribute("msg","恭喜，您激活成功！请马上登录");
        } catch (UserException e) {
//            request.setAttribute("msg",e.getMessage());
            pw.write(e.getMessage());
        }
        return "f:/index.jsp";
    }
}
