package com.flanremi.ddb;

import com.flanremi.ddb.bean.Product;
import com.flanremi.ddb.bean.Record;
import com.flanremi.ddb.bean.User;
import com.flanremi.ddb.bean.Warehouse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("/login")
    Observable<User> getUsers(@Query("username") String un, @Query("password") String pw);

    @GET("/staff_manager")
    Observable<List<User>> getStaff(@Query("uid") int uid);

    @GET("/get_all_warehouse")
    Observable<List<Warehouse>> getAllWarehouse();

    @GET("/get_warehouse")
    Observable<List<Product>> getWarehouse(@Query("uid") int uid);

    @GET("/pop_product")
    Observable<String> popProduct(@Query("uid") int uid,@Query("warehouse") int warehouseId
            ,@Query("code") String code,@Query("num") int num);

    @GET("/get_record")
    Observable<List<Record>> getRecord(@Query("uid") int uid, @Query("desc") String desc);
}
