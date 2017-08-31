package com.example.huangwei.animationapp.activity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/31/14:48
 */

public class SoundTest extends AppCompatActivity {
  SoundPool soundPool;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sound);
    soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM,0);
    soundPool.load(this,R.raw.money,1);
    findViewById(R.id.btn_sound).setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        soundPool.play(1,1,1,0,0,1);
      }
    });
  }
}
