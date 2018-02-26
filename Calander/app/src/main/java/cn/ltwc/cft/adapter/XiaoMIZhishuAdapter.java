package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.XiaomiZhishuList;
import cn.ltwc.viewutils.statusbar.StatusBarUtil;

public class XiaoMIZhishuAdapter extends BaseAdapter {
    private Context context;
    private List<XiaomiZhishuList> list;

    public XiaoMIZhishuAdapter(Context context, List<XiaomiZhishuList> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_xiao_mi_zhi_shu, null);
            holder = new Holder();
            holder.layout1 = convertView.findViewById(R.id.layout_1);
            holder.layout2 = convertView.findViewById(R.id.layout_2);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.erTitle = (TextView) convertView.findViewById(R.id.er_title);
            holder.erContent = (TextView) convertView
                    .findViewById(R.id.er_content);
            holder.img2 = (ImageView) convertView.findViewById(R.id.img_2);
            holder.erContent2 = (TextView) convertView.findViewById(R.id.er_content_2);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(list.get(i).getLink().getChannelId())) {
                holder.layout1.setVisibility(View.GONE);
                holder.layout2.setVisibility(StatusBarUtil.ISMIUI(((Activity) context).getWindow()) ? View.VISIBLE : View.GONE);
            } else {
                holder.layout1.setVisibility(View.VISIBLE);
                holder.layout2.setVisibility(View.GONE);
            }
        }
//        Glide.with(context).load(list.get(position).getImage())
//                .into(holder.img);
        holder.erTitle.setText(list.get(position).getTitle());
        holder.erContent.setText(list.get(position).getSummary());
//        Glide.with(context).load(list.get(position).getImage()).into(holder.img2);
        holder.erContent2.setText(list.get(position).getSummary());
        try {
            GlideUtil.loadImg(context, list.get(position).getImage(), holder.img);
            GlideUtil.loadImg(context, list.get(position).getImage(), holder.img2);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            Log.e("AA", error.toString());
        }
        return convertView;
    }

    class Holder {
        private ImageView img, img2;
        private TextView erTitle, erContent, erContent2;
        private View layout1, layout2;

    }
}
