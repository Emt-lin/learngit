package cn.cloudmusic.music_page.domain;

import java.util.List;

/**
 * @author psl
 */
public class Comment {
    private int id;//这条评论的id
    private int pid;//父节点的id
    private String uid;//用户名
    private String text;//评论的内容
    //子节点
    private List<Comment> childContent;

    public Comment() {
    }

    public Comment(int pid, String uid, String text) {
        this.pid = pid;
        this.uid = uid;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getChildContent() {
        return childContent;
    }

    public void setChildContent(List<Comment> childContent) {
        this.childContent = childContent;
    }
}
