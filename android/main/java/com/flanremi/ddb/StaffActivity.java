package com.flanremi.ddb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flanremi.ddb.bean.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StaffActivity extends AppCompatActivity {

    private RecyclerView lv;
    private List<User> users = new ArrayList<>();
    private UserAdapter adapter = new UserAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        init();
    }

    private void init(){
        lv = findViewById(R.id.list_staff);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lv.setLayoutManager(manager);
        lv.setAdapter(adapter);
        Service service = ((MyApplication)getApplication()).getRetrofit().create(Service.class);
        service.getStaff(((MyApplication)getApplication()).getUsers()
                .get(((MyApplication)getApplication()).getUserPosition()%3).getUid()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<User> users) throws Exception {
                        StaffActivity.this.users.addAll(users);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvWage;
        TextView tvRank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvWage = itemView.findViewById(R.id.tv_wage);
            tvRank = itemView.findViewById(R.id.tv_rank);
        }

        public void setData(User user){
            if(user == null){
                return;
            }
            tvName.setText("员工姓名：" + user.getName());
            tvWage.setText("员工工资：" + user.getWage());
            tvRank.setText("员工级别：R" + user.getLevel());
        }

    }

    class UserAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            User user = users.get(position);
            holder.setData(user);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(StaffActivity.this)
                    .inflate(R.layout.item_staff,null,false));
            return holder;
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
