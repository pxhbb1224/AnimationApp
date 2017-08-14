package com.example.huangwei.animationapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/10/13:55
 */

public class ViewAnimActivity extends AppCompatActivity implements View.OnClickListener {
  Button btnAlpha;      //渐变
  Button btnRotate;     //旋转
  Button btnTranslate;  //位移
  Button btnScale;      //缩放
  Button btnSetFirst;   //集合1
  Button btnSetSec;     //集合2
  TextView textTipsTitle;      //提示标题
  TextView textTipsContent;    //输出一个提示

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_animation);
    setTitle(getString(R.string.title_view_animation_activity));

    btnAlpha = (Button) findViewById(R.id.btn_view_animation_alpha);
    btnRotate = (Button) findViewById(R.id.btn_view_animation_rotate);
    btnTranslate = (Button) findViewById(R.id.btn_view_animation_translate);
    btnScale = (Button) findViewById(R.id.btn_view_animation_scale);
    btnSetFirst = (Button) findViewById(R.id.btn_view_animation_set_first);
    btnSetSec = (Button) findViewById(R.id.btn_view_animation_set_sec);
    textTipsTitle = (TextView) findViewById(R.id.text_view_animation_tips_title);
    textTipsContent = (TextView) findViewById(R.id.text_view_animation_tips_content);

    btnAlpha.setOnClickListener(this);
    btnRotate.setOnClickListener(this);
    btnTranslate.setOnClickListener(this);
    btnScale.setOnClickListener(this);
    btnSetFirst.setOnClickListener(this);
    btnSetSec.setOnClickListener(this);

    textTipsTitle.setVisibility(View.GONE);
    textTipsContent.setVisibility(View.GONE);
  }

  protected void clickAlpha() {
    if (btnAlpha != null) {
      closeTips();
      AlphaAnimation aa = new AlphaAnimation(0, 1);
      aa.setDuration(1000); //1s
      btnAlpha.startAnimation(aa);
    }
  }

  protected void clickRotate() {
    if (btnRotate != null) {
      closeTips();
      RotateAnimation ra =
          new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.2f);
      ra.setDuration(1000);
      btnRotate.startAnimation(ra);
    }
  }

  protected void clickTranslate() {
    if (btnTranslate != null) {
      closeTips();
      TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_PARENT, 0.3f,
                                                     Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
      ta.setDuration(1000);
      btnTranslate.startAnimation(ta);
    }
  }

  protected void clickScale() {
    if (btnScale != null) {
      closeTips();
      ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
      sa.setDuration(1000);
      btnScale.startAnimation(sa);
    }
  }

  protected void clickSet() {
    if (btnSetFirst != null) {
      closeTips();
      AnimationSet set = new AnimationSet(true);
      set.setDuration(1000);

      AlphaAnimation aa = new AlphaAnimation(0, 0.8f);
      aa.setDuration(1000);
      set.addAnimation(aa);

      TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 200);
      ta.setDuration(1000);
      set.addAnimation(ta);
      //所有动画默认同时进行
      btnSetFirst.startAnimation(set);
    }
  }

  /**
   * 点击使用xml写的动画
   */
  void clickSetSec() {
    if (btnSetSec == null) return;
    closeTips();
    Animation animation = AnimationUtils.loadAnimation(this, R.anim.view_animation_set);
    btnSetSec.startAnimation(animation);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
        Log.i("tea", "btn-X:" + btnSetSec.getX() + " btn-Y:" + btnSetSec.getY());
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        Log.i("tea", "btn-X:" + btnSetSec.getX() + " btn-Y:" + btnSetSec.getY());
        updateTips(getString(R.string.text_view_animation_tips_content_set_sec));
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
  }

  /**
   * 更新tips内容
   */
  protected void updateTips(String content) {
    //先检查
    try {
      if (textTipsContent.getVisibility() != textTipsTitle.getVisibility()) {
        throw new Exception("the tips title and content have different visible.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (TextUtils.isEmpty(content)) {
      return;
    }
    textTipsContent.setText(content);
    textTipsTitle.setVisibility(View.VISIBLE);
    textTipsContent.setVisibility(View.VISIBLE);
  }

  protected void closeTips() {
    textTipsTitle.setVisibility(View.GONE);
    textTipsContent.setVisibility(View.GONE);
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
      case R.id.btn_view_animation_set_first:
        clickSet();
        break;
      case R.id.btn_view_animation_translate:
        clickTranslate();
        break;
      case R.id.btn_view_animation_set_sec:
        clickSetSec();
      default:
        break;
    }
  }
}
