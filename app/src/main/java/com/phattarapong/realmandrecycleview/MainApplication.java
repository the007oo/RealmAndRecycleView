package com.phattarapong.realmandrecycleview;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Phattarapong on 25-Jul-17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        Contextor.getInstance().init(getApplicationContext());
    }
    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Satatement")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}

