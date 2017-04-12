package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Call call;
    List<Bean>list=new ArrayList<>();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.rv_list);
        button=(Button)findViewById(R.id.button);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Request request=null;
        HashMap<String,String>mBody=new HashMap<>();
        mBody.put("userid", "370123195008155717");
        mBody.put("password","b");
        mBody.put("startindex","1");
        StringBuilder stringBuilder = new StringBuilder("");
        if (mBody!=null){
            Set<String> keys = mBody.keySet();
            for (String key : keys){
                String value = mBody.get(key);
                if (StringUtils.isNotEmpty(value)){
                    stringBuilder.append(key).append('=').append(value).append('&');
                }
            }
        }
        String content = StringUtils.removeEnd(stringBuilder.toString(),"&");
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),content);
        request = new Request.Builder()
               // .url("http://221.214.98.55:8880/mobile/bingan/community/listwithoutorg")
                .url("http://221.214.98.55:8880/mobile/self/list/370123195008155717/1/0")
               // .post(requestBody)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        call=okHttpClient.newCall(request);

        try{
            Response response=call.execute();
            String dddd=response.body().toString();
            Log.i("ssss",dddd);
           BaseResponse<List<Bean>>ponse= new Gson().fromJson(response.body().toString(),new TypeToken<BaseResponse<List<Bean>>>(){}.getType());
            Log.i("TAG",ponse.toString());
            list=ponse.getData();
        }catch (Exception e){

        }

        recyclerView.setAdapter(new MyAdapter(this,list));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ThirdActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(WebActivity.KEY_URL, "https://www.baidu.com/");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
