package com.example.huangwei.animationapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/28/15:09
 */

public class SkipActivityB extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
    int flag = getIntent().getExtras().getInt("flag");
    switch (flag){
      case 1:
        getWindow().setEnterTransition(new Explode());
        break;
      case 2:
        getWindow().setEnterTransition(new Slide());
        break;
      case 3:
        getWindow().setEnterTransition(new Fade());
        break;
      case 4:
        break;
      default:
        break;
    }
    setContentView(R.layout.activity_skip_b);
    if (flag == 4){
      findViewById(R.id.btn_skip_fab).setVisibility(View.VISIBLE);
    }
  }
}
