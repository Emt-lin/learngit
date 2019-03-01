package cn.cloudmusic.user.service.impl;

import cn.cloudmusic.user.dao.UserDao;
import cn.cloudmusic.user.dao.impl.UserDaoImpl;
import cn.cloudmusic.user.domain.User;
import cn.cloudmusic.user.service.UserService;

/**
 * @author psl
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册功能
     * @param form
     * @throws UserException
     */
    @Override
    public void regist(User form) throws UserException {
        User user = userDao.findByUid(form.getUid());
        if (user != null) throw new  UserException("邮箱已被注册");
        userDao.add(form);
    }
//    public void regist(User form) throws UserException {
//        User user = userDao.findByUid(form.getUid());
//        if (user != null) throw new  UserException("手机号已被注册");
//        userDao.add(form);
//    }

    /**
     * 登录功能
     * @param form
     * @return
     * @throws UserException
     */
    @Override
    public User login(User form) throws UserException {
        User user = userDao.findByUid(form.getUid());
        if (user == null) {
            if (form.getUid() == null) throw new UserException("请输入邮箱号");
            else throw new UserException("该邮箱号尚未注册");
        }
        if ( !user.getPassword().equals(form.getPassword())) throw new UserException("邮箱号或密码错误");
        if (!user.isState()) throw new UserException("您的账号尚未激活，请前往邮箱激活");
        return user;
    }

    /**
     * 激活功能
     * @param code
     * @throws UserException
     */
    @Override
    public void active (String code) throws UserException {
        User user = userDao.findByCode(code);

        if (user == null) throw new UserException("激活码无效！");

        if (user.isState()) throw new UserException("您已经激活过了，不要在激活了。");

        /**
         * 修改用户状态
         */
        userDao.updateState(user.getUid(),true);
    }



//    public User login(User form) throws UserException {
//        User user = userDao.findByUid(form.getUid());
//        if (user == null) {
//            if (form.getUid() == null) throw new UserException("请输入手机号");
//            else throw new UserException("该手机号尚未注册");
//        }
//        if ( !user.getPassword().equals(form.getPassword())) throw new UserException("手机号或密码错误");
//        if (!user.isState()) throw new UserException("您的账号尚未激活，请输入验证码激活");
//        return user;
//    }
}
