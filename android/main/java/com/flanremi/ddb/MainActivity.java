package com.flanremi.ddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flanremi.ddb.bean.User;
import com.flanremi.ddb.bean.Warehouse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTvName;
    private TextView mTvRank;
    private TextView mTvWarehouse;
    private TextView mTvSwitch;
    private Button mBtnStock;
    private List<User> users;
    private int usePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        users = ((MyApplication)getApplication()).getUsers();
        usePosition = ((MyApplication)getApplication()).getUserPosition();
        mTvName = findViewById(R.id.tv_name);
        mTvRank = findViewById(R.id.tv_rank);
        mTvWarehouse = findViewById(R.id.tv_warehouse);
        mTvSwitch = findViewById(R.id.tv_switch);
        initUser();
        init();
    }

    private void init(){
        mTvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usePosition ++;
                ((MyApplication)getApplication()).setUserPosition(usePosition);
                User user = users.get(usePosition % users.size());
                mTvName.setText("员工姓名：" + user.getName());
                mTvRank.setText("员工等级：P" + user.getLevel());
                mTvWarehouse.setText("员工所属：" + user.getWarehouseName());
            }
        });
        mBtnStock = findViewById(R.id.btn_stock);
        mBtnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StockActivity.class);
                startActivity(intent);
            }
        });
        Button mBtnStaff = findViewById(R.id.btn_staff);
        mBtnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StaffActivity.class);
                startActivity(intent);
            }
        });
        Button mBtnOut = findViewById(R.id.btn_out);
        mBtnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OutActivity.class);
                startActivity(intent);
            }
        });
        Button mBtnRecord = findViewById(R.id.btn_record);
        mBtnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUser(){
        Service service = ((MyApplication)getApplication()).getRetrofit().create(Service.class);
        service.getUsers("Zhang","Z123").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        users.add(user);
                        mTvName.setText("员工姓名：" + user.getName());
                        mTvRank.setText("员工等级：P" + user.getLevel());
                        mTvWarehouse.setText("员工所属：北京总公司");
                    }
                });
        service.getUsers("Santa","Hooo").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        users.add(user);
                    }
                });
        service.getUsers("FAA","F123").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        users.add(user);
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(users.size() == 3){
                        service.getAllWarehouse().subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<List<Warehouse>>() {
                                    @Override
                                    public void accept(@NonNull List<Warehouse> warehouses) throws Exception {
                                        for (int i = 0; i < warehouses.size(); i++) {
                                            Warehouse warehouse = warehouses.get(i);
                                            for (int j = 0; j < users.size(); j++) {
                                                if(users.get(j).getWarehouse() == warehouse.getId()){
                                                    users.get(j).setWarehouseName(warehouse.getName());
                                                }
                                            }
                                        }
                                    }
                                });
                        break;
                    }
                }
            }
        }).start();
    }


}