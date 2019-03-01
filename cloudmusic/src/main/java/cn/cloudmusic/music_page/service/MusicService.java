package cn.cloudmusic.music_page.service;

import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.music_page.domain.Comment;

import java.util.List;

public interface MusicService {
    /**
     * 插入评论
     * @param comment
     * @return
     */
    boolean insertComment(Comment comment);

    /**
     * 加载所有的评论
     * @return
     */
    List<Comment> findAllComments();
}
