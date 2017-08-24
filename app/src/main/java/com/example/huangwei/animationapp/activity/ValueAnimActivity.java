package com.example.huangwei.animationapp.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/24/10:59
 */

public class ValueAnimActivity extends AppCompatActivity implements View.OnClickListener {
  ImageView imageShow;
  TextView textShow;
  Button btnShow;

  boolean flag = false;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_value_animation);
    findViewById(R.id.btn_value_timer).setOnClickListener(this);
    findViewById(R.id.btn_value_drop_down).setOnClickListener(this);
    imageShow = (ImageView) findViewById(R.id.image_show);
    textShow = (TextView) findViewById(R.id.text_show);
    btnShow = (Button) findViewById(R.id.btn_value_show);
    imageShow.setVisibility(View.GONE);
    textShow.setVisibility(View.GONE);
    btnShow.setVisibility(View.GONE);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_value_timer:
        clickTimer();
        break;
      case R.id.btn_value_drop_down:
        clickDropDown();
        break;
      default:
        break;
    }
  }

  private void clickTimer() {
    if (imageShow == null || textShow == null) return;
    isShowText(true);
    final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        textShow.setText(getString(R.string.btn_text_yuan, (Integer) valueAnimator.getAnimatedValue()));
      }
    });
    textShow.setOnClickListener(new View.OnClickListener() {
      boolean flag = false;

      @Override
      public void onClick(View v) {
        if (flag) {
          valueAnimator.resume();
        } else {
          valueAnimator.pause();
        }
        flag = !flag;
      }
    });
    valueAnimator.setDuration(3000);
    valueAnimator.start();
  }

  private void clickDropDown() {
    if (btnShow == null) return;

    if (!flag) {
      animOpen(btnShow);
    } else {
      animClose(btnShow);
    }
    flag = !flag;
  }

  private void isShowText(boolean show) {
    if (textShow == null || imageShow == null) return;
    if (show) {
      textShow.setVisibility(View.VISIBLE);
      imageShow.setVisibility(View.GONE);
    } else {
      textShow.setVisibility(View.GONE);
      imageShow.setVisibility(View.VISIBLE);
    }
  }

  private void animOpen(final View view) {
    view.measure(0, 0);
    final int height = view.getMeasuredHeight();
    ValueAnimator animator = createAnimDrop(view, 0, height);
    animator.start();
  }

  private void animClose(final View view) {
    ValueAnimator animator = createAnimDrop(view, view.getHeight(), 0);
    animator.start();
  }

  private ValueAnimator createAnimDrop(final View view, int start, int end) {
    ValueAnimator animator = ValueAnimator.ofInt(start, end);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        int value = (Integer) animation.getAnimatedValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = value;
        view.setLayoutParams(layoutParams);
        if (value > 0) {
          view.setVisibility(View.VISIBLE);
        } else {
          view.setVisibility(View.GONE);
        }
      }
    });
    animator.setDuration(2000);
    return animator;
  }
}
