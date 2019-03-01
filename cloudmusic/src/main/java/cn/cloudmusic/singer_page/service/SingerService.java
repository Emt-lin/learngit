package cn.cloudmusic.singer_page.service;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;

import java.util.List;

public interface SingerService {
    /**
     * 查询热门音乐50首
     * @return
     * @param id
     */
    List<Music> findAll(String id);

    /**
     * 加载专辑
     * @param aid
     * @return
     */
    Album loadAlbum(String aid);

    /**
     * 加载这个歌手的所有专辑
     * @param id
     * @param pc
     * @param ps
     * @return
     */
    PageBean<Album> findAllAlbum(String id, int pc, int ps);

    /**
     * 加载歌手简介
     * @param id
     * @return
     */
    String loadIntro(String id);

    /**
     * 加入歌单
     * @param mid
     * @param uid
     * @return
     */
    Boolean addMusic(String mid, String uid);
}
