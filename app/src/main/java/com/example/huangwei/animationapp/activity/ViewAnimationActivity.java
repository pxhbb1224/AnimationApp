package com.example.huangwei.animationapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/10/13:55
 */

public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener {
  Button btnAlpha;      //渐变
  Button btnRotate;     //旋转
  Button btnTranslate;  //位移
  Button btnScale;      //缩放
  Button btnSet;        //集合

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_animation);
    btnAlpha = (Button) findViewById(R.id.btn_view_animation_alpha);
    btnRotate = (Button) findViewById(R.id.btn_view_animation_rotate);
    btnTranslate = (Button) findViewById(R.id.btn_view_animation_translate);
    btnScale = (Button) findViewById(R.id.btn_view_animation_scale);
    btnSet = (Button) findViewById(R.id.btn_view_animation_set);

    btnAlpha.setOnClickListener(this);
    btnRotate.setOnClickListener(this);
    btnTranslate.setOnClickListener(this);
    btnScale.setOnClickListener(this);
    btnSet.setOnClickListener(this);
  }

  protected void clickAlpha() {
    if (btnAlpha != null) {
      AlphaAnimation aa = new AlphaAnimation(0, 1);
      aa.setDuration(1000); //1s
      btnAlpha.startAnimation(aa);
    }
  }

  protected void clickRotate() {
    if (btnRotate != null) {
      RotateAnimation ra =
          new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.2f);
      ra.setDuration(1000);
      btnRotate.startAnimation(ra);
    }
  }

  protected void clickTranslate() {
    if (btnTranslate != null) {
      TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_PARENT, 0.3f,
                                                     Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
      ta.setDuration(1000);
      btnTranslate.startAnimation(ta);
    }
  }

  protected void clickScale() {
    if (btnScale != null) {
      ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
      sa.setDuration(1000);
      btnScale.startAnimation(sa);
    }
  }

  protected void clickSet() {
    if (btnSet != null) {
      AnimationSet set = new AnimationSet(true);
      set.setDuration(1000);

      AlphaAnimation aa = new AlphaAnimation(0, 0.8f);
      aa.setDuration(1000);
      set.addAnimation(aa);

      TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 200);
      ta.setDuration(1000);
      set.addAnimation(ta);

      btnSet.startAnimation(set);
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_view_animation_alpha:
        clickAlpha();
        break;
      case R.id.btn_view_animation_rotate:
        clickRotate();
        break;
      case R.id.btn_view_animation_scale:
        clickScale();
        break;
      case R.id.btn_view_animation_set:
        clickSet();
        break;
      case R.id.btn_view_animation_translate:
        clickTranslate();
        break;
      default:
        break;
    }
  }
}
