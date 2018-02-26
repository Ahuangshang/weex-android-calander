package cn.ltwc.cft.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.WifiInfo;

public class WifiAdapter extends BaseAdapter {

    private List<WifiInfo> wifiInfos;
    private Context con;

    public WifiAdapter(List<WifiInfo> wifiInfos, Context con) {
        this.wifiInfos = wifiInfos;
        this.con = con;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return wifiInfos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return wifiInfos.get(position);
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
        Holder holer;
        if (convertView == null) {
            convertView = LayoutInflater.from(con).inflate(
                    R.layout.item_wifi_psk, null);
            holer = new Holder();
            holer.txt = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(holer);
        } else {
            holer = (Holder) convertView.getTag();
        }
        holer.txt.setText("Wifi:" + wifiInfos.get(position).Ssid + "\n密码:"
                + wifiInfos.get(position).Password);
        return convertView;
    }

    class Holder {
        private TextView txt;
    }
}
