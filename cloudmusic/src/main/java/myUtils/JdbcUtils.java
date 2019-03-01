package myUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author psl
 */
public class JdbcUtils {
    //默认文件的默认配置。要求你必须给出配置文件c3p0-config.xml!!!
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection > tl = new ThreadLocal<>();
    /**
     * 使用连接池返回一个连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();
        //当con不为空，说明已经调用过beginTransaction（）方法了，表示开启了事务
        if (con!=null) return con;
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 开启事务
     * 1、获取一个Connection，设置它setAutoCommit（false)
     * 2、还要保证dao中使用的是这个连接
     */
    public static void beginTransaction() throws SQLException {
        Connection con = tl.get();
        if (con != null) throw new SQLException("已经开启事务，就不要重复开启");
        /**
         * 1、给con赋值
         * 2、设置为手动提交
         */
        con = getConnection();//表示事务已经开始
        con.setAutoCommit(false);

        tl.set(con);//把当前线程连接保存起来
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的专用连接
        if (con == null) throw new SQLException("还没有开启事务，不能提交");
        /**
         *直接使用con.commit()
         */
        con.commit();
        con.close();
        //设置为null，表示事务已经结束了
        tl.remove();//从tl中移除
    }

    /**
     * 使用beginTransaction提供的con，然后调用roallback方法
     */
    public static void rollbackTransaction() throws SQLException {
        Connection con = tl.get();
        if (con == null) throw new SQLException("还没有开启事务，不能回滚");
        con.rollback();
        con.close();
        tl.remove();
    }

    /**
     * 释放连接
     * @param connection
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        Connection con = tl.get();
        /**
         *判断是不是事务专用，如果是，就不关闭
         * 如果不是，就需要关闭！
         */
        //如果con==null，说明没有事务，那么connection一定不是事务专用，需要关闭
        if (con == null) connection.close();
        //如果con！=null，说明有事务，那么需要判断参数连接是否与con相等，若不相等，关闭
        if (con != connection) connection.close();
    }
}
