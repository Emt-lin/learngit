package cn.cloudmusic.music_page.dao.impl;

import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.music_page.dao.MusicDao;
import cn.cloudmusic.music_page.domain.Comment;
import myUtils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author psl
 */
public class MusicDaoImpl implements MusicDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 插入评论
     * @param comment
     */
    @Override
    public void insertComment(Comment comment) {
        String sql = "insert into comment(uid,text,pid) value(?,?,?)";
        Object[] params = {comment.getUid(),comment.getText(),comment.getPid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查出父节点为pid的留言的集合
     * @param pid
     * @return
     */
    @Override
    public List<Comment> findCommentByPid(int pid) {
        String sql = "select * from comment where pid=?";
        try {
            List<Comment> list = qr.query(sql, new BeanListHandler<>(Comment.class), pid);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
