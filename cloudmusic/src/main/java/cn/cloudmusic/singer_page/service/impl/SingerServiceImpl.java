package cn.cloudmusic.singer_page.service.impl;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.singer_page.dao.SingerDao;
import cn.cloudmusic.singer_page.dao.impl.SingerDaoImpl;
import cn.cloudmusic.singer_page.service.SingerService;

import java.util.List;

/**
 * @author psl
 */
public class SingerServiceImpl implements SingerService {

    private SingerDao singerDao = new SingerDaoImpl();

    /**
     * 查询热门音乐50首
     * @return
     * @param id
     */
    @Override
    public List<Music> findAll(String id) {
        return singerDao.findAll(id);
    }

    /**
     * 加载专辑
     * @param aid
     * @return
     */
    @Override
    public Album loadAlbum(String aid) {
        return singerDao.loadAlbum(aid);
    }

    /**
     * 加载这个歌手的所有专辑
     * @param id
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Album> findAllAlbum(String id, int pc, int ps) {
        return singerDao.findAlbum(id,pc,ps);
    }

    /**
     * 加载歌手简介
     * @param id
     * @return
     */
    @Override
    public String loadIntro(String id) {
        return singerDao.loadIntro(id);
    }

    /**
     * 加入歌单
     * @param mid
     * @param uid
     * @return
     */
    @Override
    public Boolean addMusic(String mid, String uid) {
        return singerDao.addMusic(mid,uid);
    }
}
