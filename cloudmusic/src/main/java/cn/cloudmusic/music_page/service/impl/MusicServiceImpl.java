package cn.cloudmusic.music_page.service.impl;

import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.music_page.dao.MusicDao;
import cn.cloudmusic.music_page.dao.impl.MusicDaoImpl;
import cn.cloudmusic.music_page.domain.Comment;
import cn.cloudmusic.music_page.service.MusicService;

import java.util.List;

/**
 * @author psl
 */
public class MusicServiceImpl implements MusicService {
    private MusicDao musicDao = new MusicDaoImpl();

    /**
     * 插入评论
     * @param comment
     * @return
     */
    @Override
    public boolean insertComment(Comment comment) {
        if (comment.getText() != null && comment.getUid() != null){
            musicDao.insertComment(comment);
            return true;
        }
        return false;
    }

    /**
     * 加载所有的评论
     * @return
     */
    @Override
    public List<Comment> findAllComments() {
        //先找到pid为0的所有评论 即评论上所有无父节点的评论
        List<Comment> comments = musicDao.findCommentByPid(0);
        //然后找每条评论的评论 并赋值
        for (Comment comment:comments
             ) {
               List<Comment> childList = findCommentChild(comment);
               comment.setChildContent(childList);
        }
        return comments;
    }

    /**
     * 找到每条评论子节点
     * @param comment
     * @return
     */
    private List<Comment> findCommentChild(Comment comment) {
        //找到这条评论的的子节点，即pid为该条评论id的评论
        List<Comment> commentList = musicDao.findCommentByPid(comment.getId());
        //遍历它的子节点 然后递归找每条评论下的评论 即节点的子节点
        for (Comment c:commentList
             ) {
            //递归找这条评论的节点 然后赋值
            List<Comment> commentChild = findCommentChild(c);
            c.setChildContent(commentChild);
        }
        return commentList;
    }
}
