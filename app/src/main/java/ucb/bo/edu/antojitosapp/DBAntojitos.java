package ucb.bo.edu.antojitosapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Pablo on 11/10/2017.
 */

public class DBAntojitos extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("Antojitos.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    /**
     * Created by ASUS on 28/1/2018.
     */

    public static final class GPSTracker {
    }
}
