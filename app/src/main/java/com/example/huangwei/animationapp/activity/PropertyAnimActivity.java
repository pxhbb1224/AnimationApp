package com.example.huangwei.animationapp.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/10/14:50
 */

public class PropertyAnimActivity extends ListActivity {
  final int ITEM_OBJECT_ANIMATOR = 0;
  final int ITEM_VALUE_ANIMATOR = 1;
  final int ITEM_SVG_ANIMATOR = 2;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String[] items = getResources().getStringArray(R.array.property_animation_items);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.layout_string_list_item,items);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    switch (position){
      case ITEM_OBJECT_ANIMATOR:
        startActivity(new Intent(this,ObjectAnimActivity.class));
        break;
      case ITEM_VALUE_ANIMATOR:
        break;
      case ITEM_SVG_ANIMATOR:
        startActivity(new Intent(this,SVGAnimAcvtivity.class));
        break;
      default:
        break;
    }
  }
}
