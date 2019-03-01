package cn.cloudmusic.user.dao.impl;

import cn.cloudmusic.user.dao.UserDao;
import cn.cloudmusic.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import myUtils.TxQueryRunner;

import java.sql.SQLException;

/**
 * @author psl
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 按手机号查询
     * @param uid
     * @return
     */
    @Override
    public User findByUid(String uid) {
        String sql = "select * from tb_user where uid = ?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void add(User user) {
        String sql = "insert into tb_user(uid,password,code) value(?,?,?)";
        Object[] params = {user.getUid(),user.getPassword(),user.getCode()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 按激活码查询
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        String sql = "select * from tb_user where code=?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改用户的指定状态
     * @param uid
     * @param state
     */
    @Override
    public void updateState(String uid,boolean state){
        String sql = "update tb_user set state=? where uid=?";
        Object[] params = {state,uid};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
