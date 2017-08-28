package com.example.huangwei.animationapp.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import com.example.huangwei.animationapp.R;
import java.util.Map;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/28/15:09
 */

public class SkipActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_skip_a);
  }

  public void explode(View view){
    createIntentToB(1);
  }
  public void fade(View view){
    createIntentToB(3);
  }
  public void share(View view){
    View fab = findViewById(R.id.btn_skip_fab);
    Intent intent = new Intent(this,SkipActivityB.class);
    intent.putExtra("flag",4);
    startActivity(intent,
                  ActivityOptions.makeSceneTransitionAnimation(this,
                                                               Pair.create(view, getString(R.string.btn_text_share)),
                                                               Pair.create(fab,getString(R.string.btn_text_fab))).toBundle());
  }
  public void slide(View view){
    createIntentToB(2);
  }

  private void createIntentToB(int flag)
  {
    Intent intent = new Intent(this,SkipActivityB.class);
    intent.putExtra("flag",flag);
    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
  }
}
