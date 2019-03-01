package cn.cloudmusic.home_page.domain;

import java.util.List;

/**
 * 专辑
 * @author psl
 */
public class Album {
    private String aid;//主键
    private String aname;//专辑名
    private String albumtime;//发行时间
    private String image;//图片位置
    private List<Music> musicList;//音乐
    private Singer singer;//歌手

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getAlbumtime() {
        return albumtime;
    }

    public void setAlbumtime(String albumtime) {
        this.albumtime = albumtime;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Album{" +
                "aid='" + aid + '\'' +
                ", aname='" + aname + '\'' +
                ", albumtime=" + albumtime +
                ", image='" + image + '\'' +
                ", musicList=" + musicList +
                ", singer=" + singer +
                '}';
    }
}
