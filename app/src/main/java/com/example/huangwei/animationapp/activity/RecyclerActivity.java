package com.example.huangwei.animationapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import com.example.huangwei.animationapp.RecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/25/17:15
 */

public class RecyclerActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RecyclerAdapter adapter;
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

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
      }

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
    initData();
  }

  private void initData() {
    for (int i = 0; i < 50; i++) {
      items.add(getString(R.string.title_recycler_view) + String.valueOf(i));
    }
    adapter = new RecyclerAdapter(items);
    recyclerView.setAdapter(adapter);
    adapter.setInnerListener(new RecyclerAdapter.OnItemClickListener() {
      @Override
      public void OnItemClick(final View view, int position) {
        view.animate().translationZ(15F).setDuration(1000).setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            view.animate().translationZ(1f).setDuration(1000).start();
          }
        }).start();
      }
    });
  }

  public void addRecycler(View view) {
    int position = items.size(); //如果有50个，那个此时插入新数据的序号应该是50
    items.add(getString(R.string.title_recycler_view) + String.valueOf(position));
    if (items.size() > 0) {
      adapter.notifyDataSetChanged();
    }
  }

  public void deleteRecycler(View view) {
    int position = items.size() - 1;
    if (position >= 0) {
      items.remove(position);
      adapter.notifyItemRemoved(position);
    }
  }
}
