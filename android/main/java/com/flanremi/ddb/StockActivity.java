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
import com.flanremi.ddb.bean.Product;
import com.flanremi.ddb.bean.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StockActivity extends AppCompatActivity {

    private RecyclerView lv;
    private List<Product> products = new ArrayList<>();
    private ProductAdapter adapter = new ProductAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoct);
        lv = findViewById(R.id.list_product);

        init();
    }

    private void init(){
        Service service = ((MyApplication)getApplication()).getRetrofit().create(Service.class);
        service.getWarehouse(((MyApplication)getApplication()).getUsers()
                .get(((MyApplication)getApplication()).getUserPosition()%3).getUid()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<Product> products) throws Exception {
                        StockActivity.this.products.addAll(products);
                        adapter.notifyDataSetChanged();
                    }
                });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        lv.setLayoutManager(manager);
        lv.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView icon;
        TextView tvName;
        TextView tvCode;
        TextView tvPrice;
        TextView tvNum;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvNum = itemView.findViewById(R.id.tv_num);
        }

        public void setData(Product product){
            if(product == null){
                return;
            }
            icon.setImageURI(product.getImg());
            tvName.setText("商品名：" + product.getName());
            tvCode.setText("商品条码：" + product.getCode());
            tvPrice.setText("商品价格：¥" + product.getPrice());
            tvNum.setText("商品库存：" + product.getNum());
        }


    }

    class ProductAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product product = products.get(position);
            holder.setData(product);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(StockActivity.this)
                    .inflate(R.layout.item_product,null,false));
            return holder;
        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }
}
