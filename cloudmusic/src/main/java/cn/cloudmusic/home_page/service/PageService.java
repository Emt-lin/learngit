package cn.cloudmusic.home_page.service;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;

import java.util.List;

public interface PageService {


    /**
     * 查询音乐
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    PageBean<Music> findByName_m(String name, int pc, int ps);

    /**
     * 查询专辑
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    PageBean<Album> finByName_a(String name, int pc, int ps);

    /**
     * 查询歌手
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    PageBean<Singer> finByName_s(String name, int pc, int ps);

    /**
     * 加载日推的音乐
     * @param id
     * @return
     */
    List<Music> loadMusic(String id);
}
