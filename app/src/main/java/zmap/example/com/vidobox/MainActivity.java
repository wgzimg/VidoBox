package zmap.example.com.vidobox;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import zmap.example.com.vidobox.other.DIGUI_shuxing;
import zmap.example.com.vidobox.service.Digui_service;

import static android.R.id.list;
import static zmap.example.com.vidobox.other.DIGUI_shuxing.digui;

public class MainActivity extends AppCompatActivity {
    private List<String[]> list_m,list_v,list_p;
    public static final String m_path="mm_path";
    public static final String v_path="vv_path";
    public static final String p_path="pp_path";
    public static final String ACTION="android.intent.action.RESPOND_VIA_MESSAGE";
    public static Handler han=null;
    private DIGUI_shuxing digui_shuxing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        han=new MainHandl();
        digui_shuxing=DIGUI_shuxing.getIntence_digui();
        list_m=new ArrayList<>();
        list_p=new ArrayList<>();
        list_v=new ArrayList<>();
        init();
    }
    ServiceConnection conn=new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Digui_service.MyBinder binder=(Digui_service.MyBinder)service;
        binder.start();
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
    };
    private void init() {
        SharedPreferences sharedm=getSharedPreferences(m_path,Context.MODE_PRIVATE);
        SharedPreferences sharedv=getSharedPreferences(v_path,Context.MODE_PRIVATE);
        SharedPreferences sharedp=getSharedPreferences(p_path,Context.MODE_PRIVATE);
        String strm_path=sharedm.getString("0path","");
        String strv_path=sharedv.getString("0path","");
        String strp_path=sharedp.getString("0path","");
        if(strm_path.equals("")&&strv_path.equals("")&&strp_path.equals("")){
            Intent intent_ser=new Intent();
            intent_ser.setClass(MainActivity.this,Digui_service.class);
            startService(intent_ser);
            bindService(intent_ser,conn,this.BIND_AUTO_CREATE);

        }else{
            list_p=read(p_path);
            list_v=read(v_path);
            list_m=read(m_path);
            digui_shuxing.setList_v(list_v);
            digui_shuxing.setList_p(list_p);
            digui_shuxing.setList_m(list_m);
            Intent intent=new Intent(this,Zhu.class);
            startActivity(intent);
            finish();
        }
    }
    class MainHandl extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Intent intent=new Intent(MainActivity.this,Zhu.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    private List<String[]> read(String path){
        int index=0;
        List<String[]> list=new ArrayList<>();
        SharedPreferences shar=getSharedPreferences(path, Context.MODE_PRIVATE);
        while ((!shar.getString(index+"path","path_null").equals("path_null"))){
            String[] str=new String[2];
            str[0]=shar.getString(index+"path","path_null");
            str[1]=shar.getString(index+"name","name_null");
            list.add(str);
            index++;
        }
        return list;
    }
}
