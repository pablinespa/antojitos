package ucb.bo.edu.antojitosapp;

/**
 * Created by ASUS on 31/10/2017.
 */

public class RestaurantResponse {

    private String name;

    public RestaurantResponse(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
