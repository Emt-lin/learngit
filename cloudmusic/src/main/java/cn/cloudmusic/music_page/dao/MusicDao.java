package cn.cloudmusic.music_page.dao;

import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.music_page.domain.Comment;

import java.util.List;

public interface MusicDao {
    /**
     * 插入评论
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 查出父节点为pid的留言的集合
     * @param pid
     * @return
     */
    List<Comment> findCommentByPid(int pid);
}
