package zmap.example.com.vidobox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zmap.example.com.vidobox.other.DIGUI_shuxing;

public class Zhu extends AppCompatActivity {

    private List<String[]> list_m,list_v,list_p;
    private TextView tv_m,tv_v,tv_p;
    private DIGUI_shuxing shuxing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        shuxing=DIGUI_shuxing.getIntence_digui();
        init();
    }
    private void init() {
        tv_m= (TextView) findViewById(R.id.zhu_tv_mp3);
        tv_p= (TextView) findViewById(R.id.zhu_tv_map);
        tv_v= (TextView) findViewById(R.id.zhu_tv_mp4);

        list_m=shuxing.getList_m();
        list_v=shuxing.getList_v();
        list_p=shuxing.getList_p();

        tv_m.setText("歌曲"+list_m.size()+"首");
        tv_v.setText("视频"+list_v.size()+"部");
        tv_p.setText("图片"+list_p.size()+"张");

    }
}
