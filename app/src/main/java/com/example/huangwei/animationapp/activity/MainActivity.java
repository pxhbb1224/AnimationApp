package com.example.huangwei.animationapp.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.huangwei.animationapp.R;

public class MainActivity extends ListActivity {
  private final int ITEM_VIEW_ANIMATION = 0;
  private final int ITEM_PROPERTY_ANIMATION = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String[] itemArray = getResources().getStringArray(R.array.animation_items);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.layout_string_list_item, itemArray);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    switch (position) {
      case ITEM_VIEW_ANIMATION:
        startActivity(new Intent(this, ViewAnimationActivity.class));
        break;
      case ITEM_PROPERTY_ANIMATION:
        startActivity(new Intent(this, PropertyAnimActivity.class));
        break;
      default:
        break;
    }
  }
}
