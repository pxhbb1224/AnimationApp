package com.example.huangwei.animationapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/25/18:00
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

  private OnItemClickListener innerListener;
  private List<String> items;

  public RecyclerAdapter(List<String> data) {
    this.items = data;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
    holder.setText(items.get(position));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    onBindViewHolder(holder, position, null);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView v =
        (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_string_item, parent, false);
    return new ViewHolder(v);
  }

  public void setInnerListener(OnItemClickListener listener) {
    this.innerListener = listener;
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textView;

    ViewHolder(TextView itemView) {
      super(itemView);
      textView = itemView;
      textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (textView != null) {
        innerListener.OnItemClick(v, this.getAdapterPosition());
      }
    }

    void setText(CharSequence text) {
      if (textView != null) {
        textView.setText(text);
      }
    }
  }

  public interface OnItemClickListener {
    void OnItemClick(View view, int position);
  }
}
