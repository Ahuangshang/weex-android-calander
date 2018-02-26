package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.MyX5WebView;
import cn.ltwc.cft.activity.ZhishuDetailActivity;
import cn.ltwc.cft.beans.XiaomiWeather;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.view.MyListView;
import cn.ltwc.utils.ToastUtil;
import cn.ltwc.viewutils.statusbar.StatusBarUtil;

public class XiaoMIWeatherAdapter extends BaseAdapter {
    private Context context;
    private List<XiaomiWeather> datas;

    public XiaoMIWeatherAdapter(Context context, List<XiaomiWeather> datas) {
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
        Holder holde;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_xiao_mi_weather, null);
            holde = new Holder();
            holde.title = (TextView) convertView.findViewById(R.id.title);
            holde.erList = (MyListView) convertView.findViewById(R.id.er_list);
            holde.item = convertView.findViewById(R.id.item);
            convertView.setTag(holde);
        } else {
            holde = (Holder) convertView.getTag();
        }
        final XiaomiWeather model = datas.get(position);
        holde.title.setText(model.getTitle());
        //暂时隐藏天气预报
        if (model.getTitle().contains("天气") && !StatusBarUtil.ISMIUI(((Activity) context).getWindow())) {
            holde.item.setVisibility(View.GONE);
        } else {
            holde.item.setVisibility(View.VISIBLE);
        }
        XiaoMIZhishuAdapter ada = new XiaoMIZhishuAdapter(context,
                model.getListZhishu());
        holde.erList.setAdapter(ada);
        holde.erList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i,
                                    long id) {
                if (TextUtils.isEmpty(model.getListZhishu().get(i).getLink()
                        .getChannelId())) {

                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getListZhishu().get(i).getLink().getUrl()));
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    } catch (Exception e) {
                        ToastUtil.showMessageCenter("手机里没有打开此链接的应用");
                    }

//                    Intent intent = new Intent(context,
//                            MyX5WebView.class);
//                    intent.putExtra(Constant.WEBURL, model.getListZhishu().get(i).getLink().getUrl());
//                    intent.putExtra(Constant.WEBTITLE, "天气预报");
//                    context.startActivity(intent);
//                    ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                } else {
                    Intent intent = new Intent(context, ZhishuDetailActivity.class);
                    intent.putExtra("channelId", model.getListZhishu().get(i).getLink()
                            .getChannelId());
                    intent.putExtra("headPic", model.getListZhishu().get(i).getHeadData().getImgUrl()
                    );
                    intent.putExtra("title", model.getListZhishu().get(i).getHeadData()
                            .getTitle());
                    intent.putExtra("summary", model.getListZhishu().get(i).getHeadData()
                            .getSummary());
                    intent.putExtra("indexType", model.getListZhishu().get(i).getLink().getType()
                    );
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                }
            }
        });
        return convertView;
    }

    class Holder {
        private TextView title;
        private MyListView erList;
        private View item;
    }
}
