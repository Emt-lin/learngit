package cn.cloudmusic.home_page.domain;

/**
 * @author psl
 */
public class Music {
    private String mid;//主键名
    private String mname;//音乐名
    private Singer singer;//歌手名
    private String path;//路径
    private Album album;//专辑
    private String mtime;//音乐时长

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Music{" +
                "mid='" + mid + '\'' +
                ", mname='" + mname + '\'' +
                ", singer=" + singer +
                ", path='" + path + '\'' +
                ", album=" + album +
                ", mtime='" + mtime + '\'' +
                '}';
    }
}
