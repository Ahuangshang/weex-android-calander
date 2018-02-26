package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.bitmaputils.glide.GlideListener;
import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.ZhishuDetailBean;

public class ZhishuDetailAdapter extends BaseAdapter {
    private Context context;
    private List<ZhishuDetailBean> datas;

    public ZhishuDetailAdapter(Context context, List<ZhishuDetailBean> datas) {
        super();
        this.context = context;
        this.datas = datas;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);
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
                    R.layout.item_zhishu_detail, null);
            holder = new Holder();
            holder.layout1 = convertView.findViewById(R.id.layout_1);
            holder.layout2 = convertView.findViewById(R.id.layout_2);
            holder.title1 = (TextView) convertView.findViewById(R.id.title_1);
            holder.title2 = (TextView) convertView.findViewById(R.id.title_2);
            holder.content1 = (TextView) convertView
                    .findViewById(R.id.content_1);
            holder.content2 = (TextView) convertView
                    .findViewById(R.id.content_2);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img_1_1);
            holder.img2 = (ImageView) convertView.findViewById(R.id.img_1_2);
            holder.img3 = (ImageView) convertView.findViewById(R.id.img_1_3);
            holder.img = (ImageView) convertView.findViewById(R.id.img_2);
            holder.source = (TextView) convertView.findViewById(R.id.source);
            holder.item = convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ZhishuDetailBean bean = datas.get(position);
        if (bean.getImages().size() >= 3) {
            holder.layout1.setVisibility(View.VISIBLE);
            holder.layout2.setVisibility(View.GONE);
            holder.title1.setText(bean.getTitle());
            holder.content1.setText(bean.getSummary());
            GlideUtil.loadImgWithListenerCenterCrop(context, bean.getImages().get(0), GlideListener.SETALL, R.drawable.load_failed2, R.drawable.main_color_perload, holder.img1);
            GlideUtil.loadImgWithListenerCenterCrop(context, bean.getImages().get(1), GlideListener.SETALL, R.drawable.load_failed2, R.drawable.main_color_perload, holder.img2);
            GlideUtil.loadImgWithListenerCenterCrop(context, bean.getImages().get(2), GlideListener.SETALL, R.drawable.load_failed2, R.drawable.main_color_perload, holder.img3);
        } else if (bean.getImages().size() > 0) {
            holder.layout1.setVisibility(View.GONE);
            holder.layout2.setVisibility(View.VISIBLE);
            holder.title2.setText(bean.getTitle());
            holder.content2.setText(bean.getSummary());
            GlideUtil.loadImgWithListenerCenterCrop(context, bean.getImages().get(0), GlideListener.SETALL, R.drawable.load_failed2, R.drawable.main_color_perload, holder.img);
        }
        holder.source.setText(bean.getSource());
        if (TextUtils.isEmpty(bean.getSummary()) || bean.getImages().size() == 0) {
            holder.item.setVisibility(View.GONE);
        } else {
            holder.item.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class Holder {
        private View item;
        private View layout1, layout2;
        private TextView title1, content1, title2, content2;
        private ImageView img1, img2, img3, img;
        private TextView source;
    }
}
