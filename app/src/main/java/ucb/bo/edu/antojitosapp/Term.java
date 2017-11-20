package ucb.bo.edu.antojitosapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 11/10/2017.
 */

public class Term extends RealmObject {

    @PrimaryKey
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Term{" +
                "description='" + description + '\'' +
                '}';
    }



}