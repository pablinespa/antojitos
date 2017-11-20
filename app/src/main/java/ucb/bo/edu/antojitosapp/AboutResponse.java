package ucb.bo.edu.antojitosapp;

/**
 * Created by Pablo on 11/9/2017.
 */

public class AboutResponse {

    private String description;

    public AboutResponse(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
