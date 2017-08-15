package com.example.huangwei.animationapp.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/10/15:44
 */

public class ObjectAnimActivity extends AppCompatActivity implements View.OnClickListener {
  Button btnAlpha;      //渐变
  Button btnRotate;     //旋转
  Button btnTranslate;  //位移
  Button btnScale;      //缩放
  Button btnSet;        //集合
  TextView textTips;    //tips

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_animation);

    btnAlpha = (Button) findViewById(R.id.btn_view_animation_alpha);
    btnRotate = (Button) findViewById(R.id.btn_view_animation_rotate);
    btnTranslate = (Button) findViewById(R.id.btn_view_animation_translate);
    btnScale = (Button) findViewById(R.id.btn_view_animation_scale);
    btnSet = (Button) findViewById(R.id.btn_view_animation_set_first);
    textTips = (TextView) findViewById(R.id.text_view_animation_tips_content);

    findViewById(R.id.btn_view_animation_set_sec).setVisibility(View.GONE);
    btnAlpha.setOnClickListener(this);
    btnRotate.setOnClickListener(this);
    btnTranslate.setOnClickListener(this);
    btnScale.setOnClickListener(this);
    btnSet.setOnClickListener(this);
    updateTips(getString(R.string.text_object_animation_tips_def));
  }

  /**
   * 渐变
   */
  protected void clickAlpha() {
    if (this.btnAlpha != null) {
      ObjectAnimator animator = ObjectAnimator.ofFloat(this.btnAlpha, "alpha", 1f, 0.7f, 0.5f, 0.2f, 0f);
      animator.setDuration(4000);
      animator.start();
    }
  }

  /**
   * 旋转
   * path={(x1,y1),(x2,y2)}
   * x1->x2 为x轴的旋转，y1->y2 为y轴旋转
   */
  protected void clickRotate() {
    if (btnRotate != null) {
      Path path = new Path();
      path.lineTo(0, 360);
      path.lineTo(360, 360);
      ObjectAnimator animator = ObjectAnimator.ofFloat(btnRotate, "rotationX", "rotationY", path);
      animator.setDuration(2000);
      animator.start();
    }
  }

  /**
   * 平移
   */
  protected void clickTranslate() {
    if (btnTranslate != null) {
      Path path = new Path();
      path.lineTo(0, 100);
      path.lineTo(100, 100);
      path.lineTo(100, 0);
      path.lineTo(0, 0);
      ObjectAnimator animator = ObjectAnimator.ofFloat(btnTranslate, "translationX", "translationY", path);
      animator.setDuration(4000);
      animator.start();
    }
  }

  /**
   * 实现将宽度拉长，但是内容不改变
   */
  protected void clickScale() {
    if (btnScale == null) return;
    ViewWrapper wrapper = new ViewWrapper(btnScale);
    ObjectAnimator.ofFloat(wrapper, "width", 500).setDuration(1000).start();
  }

  /**
   * 组合
   */
  protected void clickSet() {
    if (btnSet == null) return;
    btnSet.setText(getString(R.string.text_view_animation_set_def));
    PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300f);
    PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 2f);
    PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 2f);
    ObjectAnimator.ofPropertyValuesHolder(btnSet, pvh1, pvh2, pvh3).setDuration(3000).start();
  }

  private void updateTips(String tips) {
    if (textTips == null || TextUtils.isEmpty(tips)) return;
    textTips.setText(tips);
    textTips.setVisibility(View.VISIBLE);
    findViewById(R.id.text_view_animation_tips_title).setVisibility(View.VISIBLE);
  }

  private void closeTips() {
    if (textTips != null) {
      textTips.setVisibility(View.GONE);
      findViewById(R.id.text_view_animation_tips_title).setVisibility(View.GONE);
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
      case R.id.btn_view_animation_translate:
        clickTranslate();
        break;
      case R.id.btn_view_animation_set_first:
        clickSet();
        break;
      default:
        break;
    }
  }

  public static class ViewWrapper {
    View target;

    public ViewWrapper(@Nullable View view) {
      target = view;
    }

    public void setWidth(float width) {
      target.getLayoutParams().width = (int) width;
      target.requestLayout();
    }

    /**
     * 不能用 target.getLayoutParams().width,因为这里会直接得到 0，因为
     * layoutParams里面是wrap_content，而里面的文字还没有测量，所以一开始
     * 就是0，当measure之后才得到有文字的宽度
     */
    public float getWidth() {
      target.measure(0, 0);
      return target.getMeasuredWidth();
      //return target.getLayoutParams().width;
    }
  }
}
