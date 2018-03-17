package clothing_rental.canceline.com.clothingrental.data_base;

import cn.bmob.v3.BmobObject;

/**
 * Created by kingShin on 2018/3/16.
 */

public class Person extends BmobObject {
    private Integer personID;
    private String account;
    private String password;
    private String address;
    private Boolean certification;
    private Boolean ginge_certification;
    private Boolean pledge;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCertification() {
        return certification;
    }

    public void setCertification(Boolean certification) {
        this.certification = certification;
    }

    public Boolean getGinge_certification() {
        return ginge_certification;
    }

    public void setGinge_certification(Boolean ginge_certification) {
        this.ginge_certification = ginge_certification;
    }

    public Boolean getPledge() {
        return pledge;
    }

    public void setPledge(Boolean pledge) {
        this.pledge = pledge;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }
}
