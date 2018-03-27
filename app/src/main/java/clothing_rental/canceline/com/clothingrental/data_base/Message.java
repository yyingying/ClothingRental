package clothing_rental.canceline.com.clothingrental.data_base;

import cn.bmob.v3.BmobObject;

/**
 * Created by kingShin on 2018/3/26.
 */

public class Message extends BmobObject {
    private String message;
    private Integer weight;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
