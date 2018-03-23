package clothing_rental.canceline.com.clothingrental.data_base;

import cn.bmob.v3.BmobObject;

/**
 * Created by kingShin on 2018/3/16.
 */

public class Favourite extends BmobObject {
    private String favouriteID;
    private String personID;
    private Integer goodSID;

    public String getFavouriteID() {
        return favouriteID;
    }

    public void setFavouriteID(String favouriteID) {
        this.favouriteID = favouriteID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Integer getGoodSID() {
        return goodSID;
    }

    public void setGoodSID(Integer goodSID) {
        this.goodSID = goodSID;
    }
}
