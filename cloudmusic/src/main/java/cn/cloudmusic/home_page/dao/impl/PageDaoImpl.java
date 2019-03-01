package cn.cloudmusic.home_page.dao.impl;

import cn.cloudmusic.home_page.dao.PageDao;
import cn.cloudmusic.home_page.domain.Album;
import cn.cloudmusic.home_page.domain.Music;
import cn.cloudmusic.home_page.domain.PageBean;
import cn.cloudmusic.home_page.domain.Singer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import myUtils.CommonUtils;
import myUtils.TxQueryRunner;

import javax.print.attribute.standard.NumberUp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author psl
 */
public class PageDaoImpl implements PageDao {
    private QueryRunner qr = new TxQueryRunner();

    //这里用了一个单例模式 这是PageDao的单例
    private static PageDao instance = null;

    /**
     * 得到Dao层的单例
     * @return
     */
    public static PageDao getInstance(){
        //双重校验锁 防止高并发的情况下new出来多个PageDao的实例
        if (instance == null){
            synchronized(PageDao.class){
                if (instance == null){
                    instance = new PageDaoImpl();
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
    public PageBean<Music> findByName_m(String name, int pc, int ps){
        PageBean<Music> pb_m = new PageBean<>();
        pb_m.setPc(pc);
        pb_m.setPs(ps);
        /**
         * 得到tr
         */
        //用模糊查询
        name = "%"+name+"%";
        String sql = "select count(*) from music where mname like ?";
        try {
            Number num = (Number) qr.query(sql, new ScalarHandler<>(),name);
            int tr = num.intValue();
            pb_m.setTr(tr);
        //"select * from music m,singer s,album a where mname like ? and m.sid=s.sid and m.aid=a.aid";
        //"SELECT * FROM music m,singer s,album a WHERE mname LIKE ? AND m.sid=s.sid AND m.aid=a.aid ORDER BY mname LIMIT ?,?"
        //sql = "select * from music where mname like ? order by mname limit ?,?";

        sql = "SELECT * FROM music m,singer s,album a WHERE " +
                "mname LIKE ? AND m.sid=s.sid AND m.aid=a.aid ORDER BY mname LIMIT ?,?";
        Object[] params = { name,(pc-1)*ps,ps};

            //由于查询的一行结果集不是javaBean对象，所以用MapListHandler
            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), params);

            List<Music> musicList = toMusicList_m(mapList);
            //添加到PageBean对象中
            pb_m.setBeanList(musicList);
            return pb_m;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 将maplist转换为Music对象
     * @param mapList
     * @return
     */
    private List<Music> toMusicList_m(List<Map<String, Object>> mapList) {

        List<Music> musicList = new ArrayList<>();
        for (Map<String,Object> map:mapList
             ) {
           Music music = CommonUtils.toBean(map,Music.class);
           //转换成Singer对象
           Singer singer = CommonUtils.toBean(map,Singer.class);

            //转换成Album对象
           Album album = CommonUtils.toBean(map,Album.class);

           music.setSinger(singer);
           music.setAlbum(album);
           //添加到集合中
           musicList.add(music);
        }
        return musicList;
    }

    /**
     * 查询专辑
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Album> findByName_a(String name, int pc, int ps) {
        PageBean<Album> pb_a = new PageBean<>();
        pb_a.setPc(pc);
        pb_a.setPs(ps);
        name = "%"+name+"%";
        String sql = "select count(*) from album where aname like ?";
        try {
            //得到总的记录数
            Number num = (Number) qr.query(sql, new ScalarHandler<>(), name);
            int tr = num.intValue();
            pb_a.setTr(tr);
            //"select * from album a,music m,singer s where " +
            //                    "aname like ? and a.sid=s.sid and a.aid=m.aid order by aname limit ?,?"
            sql = "select * from album  where " +
                    "aname like ? order by aname limit ?,?";
            Object[] params = {name,(pc-1)*ps,ps};
            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), params);
            List<Album> albumList = new ArrayList<>();
            for (Map<String,Object> map:mapList
                 ) {
                Album album = CommonUtils.toBean(map, Album.class);
                String sid = (String)map.get("sid");
                Singer singer = getSinger(sid);
                album.setSinger(singer);
                albumList.add(album);
            }
            pb_a.setBeanList(albumList);
            return pb_a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询搜索的歌手
     * @param name
     * @param pc
     * @param ps
     * @return
     */
    @Override
    public PageBean<Singer> findByName_s(String name, int pc, int ps) {
        PageBean<Singer> pb_s = new PageBean<>();
        pb_s.setPc(pc);
        pb_s.setPs(ps);
        name = "%"+name+"%";
        String sql = "select count(*) from singer where sname like ?";
        try {
            Number num1 = (Number)qr.query(sql,new ScalarHandler<>(),name);
            int tr1 = num1.intValue();
            sql = "select count(*) from music where mname like ?";
            Number num2 = (Number)qr.query(sql,new ScalarHandler<>(),name);
            int tr2 = num2.intValue();
            pb_s.setTr(tr1+tr2);
            sql = "select * from singer where sname like ? order by sname limit ?,?";
            List<Singer> singerList = qr.query(sql, new BeanListHandler<>(Singer.class), name,(pc-1)*ps,ps);
            /**
             * 后面的有缺陷
             */
            if (singerList.size()<ps){
                sql = "select * from music where mname like ? order by mname limit ?,?";
                int tp = tr1/ps;
                List<Map<String, Object>> mapList = null;
                if (tr1 % ps == 0){
                     mapList = qr.query(sql, new MapListHandler(), name, (pc - tp-1) * ps, ps);

                }else {
                    tp++;
                     mapList = qr.query(sql, new MapListHandler(), name, (pc - tp) * ps, (ps-singerList.size()));
                }
                for (Map<String, Object> map:mapList
                ) {
                    String sid = (String) map.get("sid");
                    sql = "select * from singer where sid=?";
                    Singer singer = qr.query(sql, new BeanHandler<>(Singer.class), sid);
                    singerList.add(singer);
                }
            }
            pb_s.setBeanList(singerList);
            return pb_s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 加载日推的音乐
     * @param id
     * @return
     */
    @Override
    public List<Music> findMusic(String id) {
        String sql = "select * from music order by mname limit ?,?";
        try {
            List<Map<String, Object>> maps = qr.query(sql, new MapListHandler(), 0, 30);
            List<Music> musicList = toMusicList_m(maps);
            return musicList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 得到歌手名
     * @param sid
     * @return
     */
    private Singer getSinger(String sid) throws SQLException {
        String sql = "select * from singer where sid=?";
        Singer singer = qr.query(sql, new BeanHandler<>(Singer.class), sid);
        return singer;
    }

//    /**
//     * 加载所有专辑
//     * @param mapList
//     * @return
//     * @throws SQLException
//     */
//    private List<Album> toAlbumList(List<Map<String, Object>> mapList) throws SQLException {
//        List<Album> albumList = new ArrayList<>();
//        for (Map<String,Object> map:mapList
//             ) {
//            Album album = CommonUtils.toBean(map,Album.class);
//            //得到这个专辑的所有音乐
//            List<Music> musicList = findMusic(album.getAid());
//            album.setMusicList(musicList);
//            //得到歌手
//            String sid = (String)map.get("sid");
//            Singer singer = findSinger(sid);
//
//            Music music = CommonUtils.toBean(map,Music.class);
//
//        }
//        return albumList;
//    }

//    *
//     * 查询这个专辑属于的歌手
//     * @param sid
//     * @return
//
//    private Singer findSinger(String sid) throws SQLException {
//        String sql = "select * from singer where sid=?";
//        Singer singer = qr.query(sql, new BeanHandler<>(Singer.class), sid);
//        List<Music> musicList = loadMusic(singer.getSid());
//        return singer;
//    }
//
//    *
//     * 查询这个歌手的所有音乐
//     * @param sid
//     * @return
//
//    private List<Music> loadMusic(String sid) throws SQLException {
//        String sql = "select * from music where sid=?";
//        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), sid);
//        List<Music> musicList = toMusicList_a(mapList);
//    }
//
//    *
//     * 得到这个歌手的所有音乐
//     * @param mapList
//     * @return
//
//    private List<Music> toMusicList_a(List<Map<String, Object>> mapList) {
//        List<Music> musicList = new ArrayList<>();
//        for (Map<String,Object> map:mapList
//             ) {
//
//        }
//    }
//
//    *
//     * 得到这个专辑的所有音乐
//     * @param aid
//     * @return
//     * @throws SQLException
//
//    private List<Music> findMusic(String aid) throws SQLException {
//        String sql = "select * from music where aid=?";
//        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), aid);
//        List<Music> musicList = toMusicList_a(mapList);
//        return musicList;
//    }


}
