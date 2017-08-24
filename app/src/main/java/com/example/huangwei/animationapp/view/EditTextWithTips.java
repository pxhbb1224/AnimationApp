package com.example.huangwei.animationapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/11/11:44
 */

public class EditTextWithTips extends LinearLayout {
  TextView leftText;
  EditText rightEdit;
  public EditTextWithTips(Context context) {
    super(context);
  }

  public EditTextWithTips(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public EditTextWithTips(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  private void init(Context context,AttributeSet attrs){

    LayoutInflater.from(context).inflate(R.layout.view_edit_text_with_tips, this);
    leftText = (TextView) findViewById(R.id.text_edit_text_with_tips);
    rightEdit = (EditText) findViewById(R.id.edit_edit_text_with_tips);

    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithTips);

    //通过getDimension()/getDimensionPixelSize/getDimensionPixelOffset获取的都是像素px值，如果在xml输入16dp在这里也会获取到
    //相应的像素值，三者不同的是精度： float/四舍五入int/直接取整int
    float leftTextSize = ta.getDimension(R.styleable.EditTextWithTips_leftTextSize,0);
    if (leftTextSize != 0){
      //setTextSize(float) 输入的是 sp
      leftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
    }

    int rightTextSize = ta.getDimensionPixelSize(R.styleable.EditTextWithTips_rightTextSize,0);
    if(rightTextSize != 0){
      rightEdit.setTextSize(rightTextSize);
    }
    ta.recycle();
  }
}
