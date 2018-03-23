package clothing_rental.canceline.com.clothingrental.data_base;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by kingShin on 2018/3/22.
 */

public class Order extends BmobObject implements Parcelable {

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }
        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }

    };
    private String orderID;
    private String personID;
    private String totalPrice;
    private String name;
    private String phone;
    private String address;
    private Goods goods;

    public Order(){

    }

    protected Order(Parcel in) {
        this.orderID = in.readString();
        this.personID = in.readString();
        this.totalPrice = in.readString();
        this.name = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.goods = (Goods)in.readSerializable();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }


    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.orderID);
        parcel.writeString(this.personID);
        parcel.writeString(this.totalPrice);
        parcel.writeString(this.name);
        parcel.writeString(this.phone);
        parcel.writeString(this.address);
        parcel.writeSerializable(this.goods);
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
