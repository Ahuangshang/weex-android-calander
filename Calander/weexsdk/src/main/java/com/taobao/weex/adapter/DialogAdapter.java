package com.taobao.weex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taobao.weappplus_sdk.R;

import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder> implements View.OnClickListener{
    private List<String> datas;
    public DialogAdapter(List<String> datas) {
        this.datas = datas;
    }
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,  int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,  int position) {
        if(position == 1){
            viewHolder.line.setVisibility(View.INVISIBLE);
        }
        viewHolder.mTextView.setText(datas.get(position));
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        View line;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            line = view.findViewById(R.id.line);
        }
    }
}