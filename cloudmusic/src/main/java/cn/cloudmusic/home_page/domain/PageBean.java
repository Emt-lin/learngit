package cn.cloudmusic.home_page.domain;

import java.util.List;

/**
 * @author psl
 */
public class PageBean<T> {
    private int pc;//当前页码 page code
//    private int tp;//总页数total page
    private int tr;//总记录数total record
    private int ps;//每页记录数page size
    private List<T> beanList;//当前页的记录
    private String url;//它是url后的条件

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
    /**
     * 计算总页数
     * @return
     */
    public int getTp() {
        int tp =tr/ps;
        return tr%ps==0 ? tp : tp+1;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pc=" + pc +
                ", tr=" + tr +
                ", ps=" + ps +
                ", beanList=" + beanList +
                ", url='" + url + '\'' +
                '}';
    }
}
