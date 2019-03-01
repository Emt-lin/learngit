import cn.cloudmusic.home_page.dao.PageDao;
import cn.cloudmusic.home_page.dao.impl.PageDaoImpl;
import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;
import cn.cloudmusic.user.dao.UserDao;
import cn.cloudmusic.user.dao.impl.UserDaoImpl;
import cn.cloudmusic.user.domain.User;
import cn.cloudmusic.user.service.UserService;
import cn.cloudmusic.user.service.impl.UserException;
import cn.cloudmusic.user.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import myUtils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author psl
 */
public class TestDemo {
    @Test
    public void fun1(){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUid("1");
        user.setUsername("lisi");
        user.setCode(CommonUtils.uuid()+CommonUtils.uuid());
        user.setEmail("11");
        user.setPassword("11");
        user.setSex("male");
//        user.setState(false);
        userDao.add(user);
    }
    @Test
    public void fun2() throws UserException {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUid("1");
        user.setUsername("lisi");
        user.setCode(CommonUtils.uuid()+CommonUtils.uuid());
        user.setEmail("11");
        user.setPassword("11");
        user.setSex("male");
        userService.regist(user);
    }
    @Test
    public void fun3(){
        PageDao pageDao = new PageDaoImpl();
        PageBean<Music> pb_a = pageDao.findByName_m("Here With You", 1, 10);
        System.out.println(pb_a);
    }
    @Test
    public void fun5(){
        PageDao pageDao = new PageDaoImpl();
        PageBean<Singer> pb_a = pageDao.findByName_s("G.E.M.邓紫棋", 1, 10);
        String s = JSONObject.fromObject(pb_a).toString();
        System.out.println(s);
        System.out.println(pb_a);
    }
    @Test
    public void fun4(){
        PageBean<Music> pb = new PageBean<>();
        pb.setPs(10);
        pb.setTr(10);
        pb.setPc(20);
        pb.setUrl("nihao");
        List<Music> musicList = new ArrayList<>();
        Music m1 = new Music();
        m1.setMid("1");
        m1.setMname("张三");
        m1.setMtime("11");
        musicList.add(m1);
        Music m2 = new Music();
        m2.setMid("1");
        m2.setMname("张三");
        m2.setMtime("11");
        musicList.add(m2);
        pb.setBeanList(musicList);
        JSONObject jsonObject = JSONObject.fromObject(pb);
        String s1 = jsonObject.toString();
        System.out.println(s1);
        JSONArray jsonArray = JSONArray.fromObject(musicList);
        String s = jsonArray.toString();
        System.out.println(s);
    }
}
