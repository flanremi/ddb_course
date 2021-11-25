package com.flanremi.ddb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flanremi.ddb.bean.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);

        init();
    }

    private  void init(){
        EditText etOut = findViewById(R.id.et_out);
        EditText etNum = findViewById(R.id.et_num);
        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service service = ((MyApplication)getApplication()).getRetrofit().create(Service.class);
                service.popProduct(((MyApplication)getApplication()).getUsers()
                        .get(((MyApplication)getApplication()).getUserPosition()%3).getUid(),
                        ((MyApplication)getApplication()).getUsers()
                                .get(((MyApplication)getApplication()).getUserPosition()%3).getWarehouse(),
                        etOut.getText().toString(),Integer.parseInt(etNum.getText().toString()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String str) throws Exception {
                                Toast.makeText(getApplicationContext(),"出库完成",Toast.LENGTH_LONG).show();
                                etOut.setText("");
                                etNum.setText("");
                            }
                        });
            }
        });
    }
}
