package clothing_rental.canceline.com.clothingrental.data_base;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by kingShin on 2018/3/16.
 */

public class Goods extends BmobObject implements Parcelable {

    public static final Parcelable.Creator<Goods> CREATOR = new Parcelable.Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel source) {
            return new Goods(source);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

    private Integer goodsID;
    private String name;
    private Integer price;
    private Integer rental_price;
    private Integer stock;
    private BmobFile photo;
    private String place;


    public Goods() {
    }

    protected Goods(Parcel in) {
        this.goodsID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.rental_price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.photo = (BmobFile) in.readSerializable();
        this.place = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.goodsID);
        dest.writeString(this.name);
        dest.writeValue(this.price);
        dest.writeValue(this.rental_price);
        dest.writeValue(this.stock);
        dest.writeSerializable(this.photo);
        dest.writeString(this.place);
    }
}
