package cn.cloudmusic.user.service;

import cn.cloudmusic.user.service.impl.UserException;
import cn.cloudmusic.user.domain.User;

public interface UserService {
    /**
     * 注册功能
     * @param form
     */
    void regist  (User form)throws UserException;

    /**
     * 登录功能
     * @param form
     * @throws UserException
     */
    User login(User form) throws UserException;
    /**
     * 激活功能
     * @param code
     * @throws UserException
     */
    void active(String code)throws UserException;
}
