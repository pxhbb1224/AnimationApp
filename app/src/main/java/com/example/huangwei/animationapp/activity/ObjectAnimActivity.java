package com.example.huangwei.animationapp.activity;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/10/15:44
 */

public class ObjectAnimActivity extends AppCompatActivity {
  Button btnAlpha;      //渐变
  Button btnRotate;     //旋转
  Button btnTranslate;  //位移
  Button btnScale;      //缩放
  Button btnSet;        //集合
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * 渐变
   */
  protected void clickAlpha() {
    if (this.btnAlpha != null) {
      ObjectAnimator animator = ObjectAnimator.ofFloat(this.btnAlpha, "alpha", 0, 0.2f, 0.3f, 0.7f, 1f);
      animator.setDuration(4000);
      animator.start();
    }
  }

  /**
   * 旋转
   */
  protected void clickRotate() {
    if (btnRotate != null){
      Path path = new Path();
      path.lineTo(0,360);
      ObjectAnimator animator = ObjectAnimator.ofFloat(btnRotate,"rotationX","rotationY",path);
      animator.setDuration(1000);
      animator.start();
    }
  }

  /**
   * 平移
   */
  protected void clickTranslate() {
  }

  /**
   * 缩放
   */
  protected void clickScale() {
  }

  /**
   * 组合
   */
  protected void clickSet() {
  }
}
