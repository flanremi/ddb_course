package com.flanremi.ddb;

import android.app.Application;
import android.content.SharedPreferences;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.flanremi.ddb.bean.User;
import com.flanremi.ddb.bean.Warehouse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application{

    public static final String MYNOTE = "MYNOTE";
    public static final String DATA = "DATA";
    public static final String URL = "http://45.128.208.38:8067";
    private SharedPreferences preferences;

    private static MyApplication mApplication;
    private List<User> users = new ArrayList<>();
    private int userPosition = 0;
    private Retrofit retrofit;


    private String password;

    public static MyApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        init();
    }

    private void init(){
        Fresco.initialize(this);
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public SharedPreferences getShare() {
        if (preferences == null) {
            preferences = getSharedPreferences(MYNOTE, MODE_PRIVATE);
        }
        return preferences;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(int userPosition) {
        this.userPosition = userPosition;
    }
}