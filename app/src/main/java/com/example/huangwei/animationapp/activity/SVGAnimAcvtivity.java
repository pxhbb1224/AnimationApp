package com.example.huangwei.animationapp.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/18/11:13
 */

public class SVGAnimAcvtivity extends AppCompatActivity implements View.OnClickListener {
  ImageView imageAnim;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_svg_anim);
    findViewById(R.id.btn_svg_line_anim).setOnClickListener(this);
    findViewById(R.id.btn_svg_three_ball_anim).setOnClickListener(this);
    findViewById(R.id.btn_svg_path_anim).setOnClickListener(this);

    imageAnim = (ImageView) findViewById(R.id.image_svg_anim);
    imageAnim.setVisibility(View.GONE);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_svg_line_anim:
        clickLineAnim();
        break;
      case R.id.btn_svg_three_ball_anim:
        clickThreeBallAnim();
        break;
      case R.id.btn_svg_path_anim:
        clickPathAnim();
        break;
      default:
        break;
    }
  }

  private void clickLineAnim(){
    setImageAnim(R.drawable.animated_vector_drawable_line_anim);
    startAnim();
  }
  private void clickPathAnim(){}
  private void clickThreeBallAnim(){
    setImageAnim(R.drawable.animated_vector_drawable_three_ball);
    startAnim();
  }

  private void setImageAnim(@DrawableRes int resId){
    if (imageAnim == null) return;
    setImageAnim(getDrawable(resId));
  }

  private void setImageAnim(Drawable drawable){
    if (imageAnim == null) return;
    imageAnim.setImageDrawable(drawable);
    imageAnim.setVisibility(View.VISIBLE);
  }

  private void startAnim(){
    if (imageAnim == null || imageAnim.getDrawable() == null) return;
    Drawable drawable = imageAnim.getDrawable();
    if (drawable instanceof Animatable){
      ((Animatable)drawable).start();
    }
  }
}
