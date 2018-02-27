package ucb.bo.edu.antojitosapp;

/**
 * Created by ASUS on 31/10/2017.
 */

public class RestaurantResponse {

    private String id;
    private String name;
    private String address;
    private String picture;
    private String phone_number;
    private String latitude;
    private String longitude;
    private Double distance;

    public RestaurantResponse(String name, String address, String picture, String phone_number, String latitude, String longitude, Double distance){
        this.name = name;
        this.address = address;
        this.picture = picture;
        this.phone_number = phone_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }
    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() { return picture; }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone_number() { return phone_number; }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Double getDistance() { return distance; }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
