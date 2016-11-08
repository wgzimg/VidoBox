package zmap.example.com.vidobox.other;

import java.util.List;

/**
 * Created by 男神 on 2016/11/8/008.
 */

public class DIGUI_shuxing {
    private List<String[]> list_m;
    private List<String[]> list_v;
    private List<String[]> list_p;
    public static DIGUI_shuxing digui;

    public DIGUI_shuxing() {
    }

    public static DIGUI_shuxing getIntence_digui(){
        if(digui==null){
            digui=new DIGUI_shuxing();
            return digui;
        }else{
            return digui;
        }
    }

    public List<String[]> getList_p() {
        return list_p;
    }

    public void setList_p(List<String[]> list_p) {
        this.list_p = list_p;
    }

    public List<String[]> getList_m() {
        return list_m;
    }

    public void setList_m(List<String[]> list_m) {
        this.list_m = list_m;
    }

    public List<String[]> getList_v() {
        return list_v;
    }

    public void setList_v(List<String[]> list_v) {
        this.list_v = list_v;
    }
}
