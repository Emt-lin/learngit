package cn.cloudmusic.home_page.service.impl;

import cn.cloudmusic.home_page.dao.PageDao;
import cn.cloudmusic.home_page.dao.impl.PageDaoImpl;
import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;
import cn.cloudmusic.home_page.service.PageService;

import java.util.List;

/**
 * @author psl
 */
public class PageServiceImpl implements PageService {
    //service的单例
    private static PageService instance = null;
    //dao的单例
    private PageDao pageDao = null;

    public PageServiceImpl(){
        pageDao = PageDaoImpl.getInstance();
    }

    /**
     * 得到service的单例
     * @return
     */
    public static PageService getInstance(){
        if (instance == null){
            synchronized(PageServiceImpl.class){
                if (instance == null){
                    instance = new PageServiceImpl();
                }
            }
        }
        return instance;
    }

    /**
     * 查询音乐
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Music> findByName_m(String name, int pc, int ps) {
        return pageDao.findByName_m(name,pc,ps);
    }

    /**
     * 查询专辑
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Album> finByName_a(String name, int pc, int ps) {
        return pageDao.findByName_a(name,pc,ps);
    }

    @Override
    public PageBean<Singer> finByName_s(String name, int pc, int ps) {
        return pageDao.findByName_s(name,pc,ps);
    }

    /**
     * 加载日推的音乐
     * @param id
     * @return
     */
    @Override
    public List<Music> loadMusic(String id) {
        return pageDao.findMusic(id);
    }
}
