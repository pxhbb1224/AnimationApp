package com.example.huangwei.animationapp.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import com.example.huangwei.animationapp.R;

/**
 * 提示音
 *
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/31/16:46
 */

public class SoundUtils {
  SoundUtils instance;
  Context context;
  private final static int DEFAULT_MAX_STREAM = 1;
  private final static int DEFAULT_STREAM_TYPE = 0;  //
  private final static int DEFAULT_PRIORITY = 1;  //权限,dang

  public SoundUtils getInstance(Activity activity) {
    if (instance == null) {
      return new SoundUtils(activity);
    }
    return instance;
  }

  private SoundUtils(Activity activity) {
    this.context = activity.getApplicationContext();
  }

  public void playAlarmOnDone() {
    SoundPool soundPool = createSoundPool(DEFAULT_MAX_STREAM, DEFAULT_STREAM_TYPE);
    soundPool.load(context, R.raw.money,DEFAULT_PRIORITY);
  }

  /**
   * 为了兼容性,创建该方法返回SoundPool对象
   */
  private SoundPool createSoundPool(int maxStream, int streamType) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //封装描述音频流信息的属性集合的类
      //由于soundPool 里面基本都是放着时间短、内容小的提示音，目前也只有当交易成功之后提醒，
      // 所以使用 USAGE_ALARM 和 CONTENT_TYPE_SONIFICATION(源码注释：...such as the
      // type of a sound for a bonus being received in a game.匹配需求)
      AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM)
                                                                     .setContentType(
                                                                         AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                                                     .setLegacyStreamType(streamType)
                                                                     .build();
      return new SoundPool.Builder().setMaxStreams(maxStream).setAudioAttributes(audioAttributes).build();
    } else {
      //最后一个参数源代码里面并没有使用，使用默认0传入即可
      return new SoundPool(maxStream, streamType, 0);
    }
  }
}
