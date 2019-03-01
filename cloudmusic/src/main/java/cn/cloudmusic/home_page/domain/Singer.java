package cn.cloudmusic.home_page.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 歌手
 * @author psl
 */
public class Singer {
    private String sid;//主键
    private String sname;//歌手名
    private String image;//图片位置
    private String text;//歌手简介
    private Set<Music> musicSet = new HashSet<>();//音乐
    private Set<Album> albumSet = new HashSet<>();//专辑

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Album> getAlbumSet() {
        return albumSet;
    }

    public void setAlbumSet(Set<Album> albumSet) {
        this.albumSet = albumSet;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Set<Music> getMusicSet() {
        return musicSet;
    }

    public void setMusicSet(Set<Music> musicSet) {
        this.musicSet = musicSet;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", musicSet=" + musicSet +
                ", albumSet=" + albumSet +
                '}';
    }
}
