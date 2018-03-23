package clothing_rental.canceline.com.clothingrental.login.ui;

import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.data_base.Person;

/**
 * Created by kingShin on 2018/3/20.
 */

public class LoginUtil {
    static boolean state = false;
    static Person person;
    static String personID;
    //存最常用的addreaa
    static Adress adress;
    static boolean first_set_address;

    public static Person getPerson(){
        if (person == null){
            return null;
        }else {
            return person;
        }
    }
    public static String getPersonID(){
        if (person == null){
            return null;
        }else {
            return person.getAccount();
        }
    }

    public static void setPersonID(String id){
        personID = id;
    }

    public static void setPerson(Person mperson){
        person = mperson;
    }

    public static void setState(boolean mstate){
        state = mstate;
    }

    public static boolean isLogin(){
        return state ;
    }

    public static Adress getAdress(){
        if (adress == null){
            return null;
        }else {
            return adress;
        }
    }

    public static void setAdress(Adress adress1){
        adress = adress1;
    }
    public static boolean isEqualToAddress(Adress adress2){
        if(adress2 == adress){return true;}
        else {return false;}
    }

    public static void setFirst_set_address(boolean b){
        first_set_address = b;
    }

    public static boolean getFirst_set_address(){
        return first_set_address;
    }
}
