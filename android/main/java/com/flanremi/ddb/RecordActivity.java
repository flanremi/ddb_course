package com.flanremi.ddb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flanremi.ddb.bean.Product;
import com.flanremi.ddb.bean.Record;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecordActivity extends AppCompatActivity {

    RecyclerView lv;
    private List<Record> records = new ArrayList<>();
    RecordAdapter adapter = new RecordAdapter();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        init();
    }
    
    private void init(){
        EditText etSearch = findViewById(R.id.et_desc);
        Button btnSearch = findViewById(R.id.btn_search);
        lv = findViewById(R.id.list_record);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lv.setLayoutManager(manager);
        lv.setAdapter(adapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service service = ((MyApplication)getApplication()).getRetrofit().create(Service.class);
                service.getRecord(((MyApplication)getApplication()).getUsers()
                        .get(((MyApplication)getApplication()).getUserPosition()%3).getUid(),
                        etSearch.getText().toString()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Record>>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull List<Record> records) throws Exception {
                                RecordActivity.this.records.clear();
                                RecordActivity.this.records.addAll(records);
                                adapter.notifyDataSetChanged();
                                etSearch.setText("");
                            }
                        });

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate;
        TextView tvDesc;
        TextView tvUid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvUid = itemView.findViewById(R.id.tv_uid);
        }

        public void setData(Record record){
            if(record == null){
                return;
            }
            tvDate.setText("记录日期："+record.getDate());
            tvDesc.setText("记录内容："+record.getDescp());
            tvUid.setText("记录提交者id："+record.getUid());
        }


    }

    class RecordAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Record record = records.get(position);
            holder.setData(record);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(RecordActivity.this)
                    .inflate(R.layout.item_record,null,false));
            return holder;
        }

        @Override
        public int getItemCount() {
            return records.size();
        }
    }
}
