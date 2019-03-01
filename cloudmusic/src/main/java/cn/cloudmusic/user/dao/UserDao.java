package cn.cloudmusic.user.dao;

import cn.cloudmusic.user.domain.User;

public interface UserDao {

    /**
     * 按手机号查询
     * @param uid
     * @return
     */
    User findByUid(String uid);
    /**
     * 添加用户
     * @param user
     */
    void add(User user);
    /**
     * 按激活码查询
     * @param code
     * @return
     */
    User findByCode(String code);
    /**
     * 修改用户的指定状态
     * @param uid
     * @param state
     */
    void updateState(String uid, boolean state);
}
