package clothing_rental.canceline.com.clothingrental.data_base;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by kingShin on 2018/3/16.
 */

public class Goods extends BmobObject {
    private Integer goodsID;
    private String name;
    private Integer price;
    private Integer rental_price;
    private Integer stock;
    private BmobFile photo;
    private String place;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRental_price() {
        return rental_price;
    }

    public void setRental_price(Integer rental_price) {
        this.rental_price = rental_price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
