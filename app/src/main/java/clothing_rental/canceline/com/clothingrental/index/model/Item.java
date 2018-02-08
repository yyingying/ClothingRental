package clothing_rental.canceline.com.clothingrental.index.model;

/**
 * Created by 张宇 on 2018/2/8.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */

public class Item {
    public Item(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Item() {
    }

    private String url; //图片
    private String title; //标题

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
