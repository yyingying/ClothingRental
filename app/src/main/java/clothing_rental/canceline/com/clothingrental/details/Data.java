package clothing_rental.canceline.com.clothingrental.details;

/**
 * Created by kingShin on 2018/3/14.
 */

public class Data {
    private String dUrl;
    private String dName;
    private double dPrice;
    private double dRental_price;
    private Integer dID;
    private String dPlace;

    public Data(String name,String url,double price,double rental_price,Integer ID,String placce){
        super();
        this.dName = name;
        this.dUrl = url;
        this.dPrice = price;
        this.dRental_price = rental_price;
        this.dID = ID;
        this.dPlace = placce;
    }

    public String getName() {
        return dName;
    }

    public String getUrl() {
        return dUrl;
    }

    public double getPrice() {
        return dPrice;
    }

    public double getRental_price() {
        return dRental_price;
    }

    public double getID() {
        return dID;
    }

    public String getPlace() {
        return dPlace;
    }
}
