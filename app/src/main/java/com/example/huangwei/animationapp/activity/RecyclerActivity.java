package com.example.huangwei.animationapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.huangwei.animationapp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/25/17:15
 */

public class RecyclerActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RecyclerView.Adapter adapter;
  RecyclerView.LayoutManager layoutManager;
  Spinner spinner;
  private List<String> items = new ArrayList<>();
  private final static int ITEM_SPINNER_LINEARLAYOUT = 0;
  private final static int ITEM_SPINNER_GRIDLAYOUT = 1;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    spinner = (Spinner) findViewById(R.id.spinner);

    layoutManager = new LinearLayoutManager(this);
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
          case ITEM_SPINNER_LINEARLAYOUT:
            recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
            break;
          case ITEM_SPINNER_GRIDLAYOUT:
            recyclerView.setLayoutManager(new GridLayoutManager(RecyclerActivity.this, 3));
            break;
          default:
            break;
        }
      }
    });
  }

}
