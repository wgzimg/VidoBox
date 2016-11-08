package zmap.example.com.vidobox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zmap.example.com.vidobox.other.DIGUI_shuxing;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static zmap.example.com.vidobox.other.DIGUI_shuxing.digui;

public class MapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
}
