package com.example.huangwei.animationapp.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/22/11:02
 */

public class TempActivity extends AppCompatActivity implements View.OnClickListener{
  ImageView image;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_temp_jianshu);
    findViewById(R.id.btn_temp_jianshu).setOnClickListener(this);
    findViewById(R.id.btn_svg_temp_jianshu).setOnClickListener(this);
    image = (ImageView) findViewById(R.id.image_temp_jianshu);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_temp_jianshu:
        clickScale();
        break;
      case R.id.btn_svg_temp_jianshu:
        clickSvg();
        break;
      default:
        break;
    }
  }

  private void clickScale(){
    if (image != null){
      Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_temp_demo);
      animation.setDuration(1000);
      image.startAnimation(animation);
    }
  }
  private void clickSvg(){
    if (image != null){
      image.setImageDrawable(getDrawable(R.drawable.animated_vector_drawable_temp));
      Drawable drawable = image.getDrawable();
      if (drawable instanceof Animatable)
      ((Animatable)drawable).start();
    }
  }
}
