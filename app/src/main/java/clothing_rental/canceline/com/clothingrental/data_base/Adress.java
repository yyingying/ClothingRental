package clothing_rental.canceline.com.clothingrental.data_base;

import cn.bmob.v3.BmobObject;

/**
 * Created by kingShin on 2018/3/20.
 */

public class Adress extends BmobObject {
    private String userID;
    private String personName;
    private String phone;
    private String adress;
    private boolean weight;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isWeight() {
        return weight;
    }

    public void setWeight(boolean weight) {
        this.weight = weight;
    }
}
