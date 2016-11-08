package zmap.example.com.vidobox.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import zmap.example.com.vidobox.MainActivity;
import zmap.example.com.vidobox.other.DIGUI_shuxing;
/**
 * Created by 男神 on 2016/11/8/008.
 */
public class Digui_service extends Service {
    private List<String[]> list_m,list_v,list_p;
    private DIGUI_shuxing shuxing;
    @Override
    public void onCreate() {
        super.onCreate();
        list_m=new ArrayList<>();
        list_v=new ArrayList<>();
        list_p=new ArrayList<>();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public void start(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DiGui(Environment.getExternalStorageDirectory());
                    shuxing= DIGUI_shuxing.getIntence_digui();
                    shuxing.setList_v(list_v);
                    shuxing.setList_p(list_p);
                    shuxing.setList_m(list_m);

                    Writ(list_m,MainActivity.m_path);
                    Writ(list_p,MainActivity.p_path);
                    Writ(list_v,MainActivity.v_path);
                    MainActivity.han.sendEmptyMessage(1);
                }
            }).start();
        }
        void DiGui(File file){
            File[] files=file.listFiles();
            for(int i=0;i<files.length;i++){
                if(files[i].isDirectory()){
                    DiGui(files[i]);
                }else{
                    String path=files[i].getPath();
                    String name=files[i].getName();
                    String[] atu=new String[2];
                    atu[0]=path;
                    atu[1]=name;
                    if(name.endsWith(".mp3")){
                        if(files[i].length()>1024*1024){
                            list_m.add(atu);
                        }

                    }
                    if(name.endsWith(".mp4")){
                        if(files[i].length()>1024*1024*5){
                            list_v.add(atu);
                        }

                    }
                    if(name.endsWith(".jpg")||name.endsWith(".gif")||name.endsWith(".png")||name.endsWith(".jpeg")){
                        if(files[i].length()>1024*20){
                            list_p.add(atu);
                        }

                    }
                }
            }
        }

        void Writ(List<String[]> list,String path){
            SharedPreferences shared=getSharedPreferences(path, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit=shared.edit();
            for(int i=0;i<list.size();i++){
                edit.putString(i+"path",list.get(i)[0]);
                edit.putString(i+"name",list.get(i)[1]);
            }
            edit.commit();
        }
    }
}
