package cn.cloudmusic.singer_page.dao.impl;

import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;
import cn.cloudmusic.singer_page.dao.SingerDao;
import myUtils.CommonUtils;
import myUtils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author psl
 */
public class SingerDaoImpl implements SingerDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 查询热门音乐50首
     * @return
     * @param id
     */
    @Override
    public List<Music> findAll(String id) {
        String sql = "select * from music where sid=? order by mname limit ?,?";
        Object[] params = {id,0,50};
        try {
            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), params);
            //加载音乐
            List<Music> musicList = toMusicList(mapList);
            return musicList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载专辑
     * @param aid
     * @return
     */
    @Override
    public Album loadAlbum(String aid) {
        String sql = "select * from album where aid=?";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler(), aid);
            Album album = toAlbum(map);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 加载这个歌手的所有专辑
     * @param id
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Album> findAlbum(String id, int pc, int ps) {
        PageBean<Album> pb = new PageBean<>();
        pb.setPc(pc);
        pb.setPs(ps);
        String sql = "select * from album where aid=? order by aname limit ?,?";
        Object[] params = {id,(pc-1)*ps,ps};
        try {
            List<Album> albumList = qr.query(sql, new BeanListHandler<>(Album.class), params);
            pb.setBeanList(albumList);
            return pb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载歌手简介
     * @param id
     * @return
     */
    @Override
    public String loadIntro(String id) {
        String sql = "select * from singer where sid=?";
        try {
            Singer query = qr.query(sql, new BeanHandler<>(Singer.class), id);
            return query.getText();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加入歌单
     * @param mid
     * @param uid
     */
    @Override
    public Boolean addMusic(String mid, String uid) {
        String sql = "insert into tb_user_music values(?,?)";
        try {
            qr.update(sql,uid,mid);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Album toAlbum(Map<String, Object> map) throws SQLException {
        Album album = CommonUtils.toBean(map,Album.class);
        String sql = "select * from music where aid=?";
        List<Music> musicList = qr.query(sql, new BeanListHandler<>(Music.class), album.getAid());
        //查询歌手
        sql = "select * from singer where sid=?";
        Singer s = qr.query(sql, new BeanHandler<>(Singer.class), map.get("sid"));
        //添加到每一首音乐中
        for (Music music:musicList
             ) {
            music.setSinger(s);
        }

        album.setMusicList(musicList);
        return album;
    }

    private List<Music> toMusicList(List<Map<String, Object>> mapList) throws SQLException {
        List<Music> musicList = new ArrayList<>();
        for (Map<String,Object> map:mapList
             ) {
            Music music = CommonUtils.toBean(map,Music.class);
            //得到这首歌的专辑
            String aid = (String) map.get("aid");
            String sql = "select * from album where aid=?";
            Album album = qr.query(sql, new BeanHandler<>(Album.class));
            music.setAlbum(album);
            //添加到集合中
            musicList.add(music);
        }
        return musicList;
    }
}
