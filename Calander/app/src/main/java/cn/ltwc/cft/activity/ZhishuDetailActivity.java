package cn.ltwc.cft.activity;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.bitmaputils.BitMapUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.ZhishuDetailAdapter;
import cn.ltwc.cft.beans.Link;
import cn.ltwc.cft.beans.ZhishuDetailBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.view.TitleView;
import rx.functions.Action1;

public class ZhishuDetailActivity extends BaseActivity {
    private TitleView titleView;
    private ListView list;
    private String channelId;
    private String headPic;
    private String title, summary;
    private View headView;
    private ImageView headImg;
    private TextView headTitle, headContent;
    private View headC;

    public ZhishuDetailActivity() {
        super(R.layout.activity_zhishu_detail);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        titleView = (TitleView) findViewById(R.id.zhishu_title);
        list = (ListView) findViewById(R.id.list);
        headView = LayoutInflater.from(this).inflate(
                R.layout.zhishu_detail_head, list, false);
        headImg = (ImageView) headView.findViewById(R.id.head_pic);
        headTitle = (TextView) headView.findViewById(R.id.head_title);
        headContent = (TextView) headView.findViewById(R.id.head_content);
        headC = headView.findViewById(R.id.head_c);
        titleView.setTitleAlpha(0);
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        channelId = getIntent().getStringExtra("channelId");
        headPic = getIntent().getStringExtra("headPic");
        title = getIntent().getStringExtra("title");
        summary = getIntent().getStringExtra("summary");
    }

    @Override
    public void bindView() {
        // TODO Auto-generated method stub
        titleView.setTitletext(title);
        Glide.with(this).load(headPic).into(headImg);
        headTitle.setText(title);
        headContent.setText(summary);
        list.addHeaderView(headView);
        APIService.getInstance(Constant.GET_XIAO_MI_LAYOUT2, 1).getLaoutDetail(channelId, new ProgressSubscriber<Object>(new Action1<Object>() {
            @Override
            public void call(Object xiaoMILayoutDetail) {
                try {
                    JSONObject object = new JSONObject((String) xiaoMILayoutDetail);
                    setView(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, true));
        list.setOnScrollListener(new OnScrollListener() {
            float hlast;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                int location[] = new int[2];
                headView.getLocationInWindow(location);
                float h = location[1];
                if (VERSION.SDK_INT > VERSION_CODES.M) {
                    //处理7.0以上获取高度不正确的问题。
                    if (firstVisibleItem == 0) {
                        hlast = h;
                    } else {
                        if (h == 0) {
                            h = hlast;
                        }
                    }
                }
                float top = 0;
                if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                    top = BitMapUtil.dip2px(c, 70);
                } else {
                    top = BitMapUtil.dip2px(c, 50);
                }
                float y = BitMapUtil.dip2px(c, 180);
                float hc = BitMapUtil.dip2px(c, 70);
                if (y + h >= 2 * top) {
                    titleView.setTitleAlpha(0);
                } else {
                    titleView.setTitleAlpha(1 - ((y + h - top) / top));
                }
                if ((y + h) <= (top + hc)) {
                    headC.setAlpha(0);
                } else {
                    headC.setAlpha(((y + h) - (top + hc)) / (y - top - hc));
                }
            }
        });
    }

    private void setView(JSONObject object) {
        // TODO Auto-generated method stub
        JSONArray array = object.optJSONArray("cards");
        final List<ZhishuDetailBean> datas = new ArrayList<ZhishuDetailBean>();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.optJSONObject(i);
                if (Utils.isNull(obj)) {
                    continue;
                }
                JSONArray list = obj.optJSONArray("list");
                if (list != null) {
                    for (int j = 0; j < list.length(); j++) {
                        JSONObject o = list.optJSONObject(j);
                        if (Utils.isNull(o)) {
                            continue;
                        }
                        JSONObject data = o.optJSONObject("data");
                        if (Utils.isNull(data)) {
                            continue;
                        }
                        String title = data.optString("wtrTitle");
                        String summary = data.optString("wtrSummary");
                        String source = data.optString("wtrResource");
                        JSONArray imgArray = data.optJSONArray("wtrImges");
                        List<String> images = new ArrayList<>();
                        if (!Utils.isNull(imgArray)) {
                            for (int k = 0; k < imgArray.length(); k++) {
                                images.add(imgArray.optString(k));
                            }
                        }
                        JSONObject wtrLink = data.optJSONObject("wtrLink");
                        if (Utils.isNull(wtrLink)) {
                            continue;
                        }
                        Link link = new Link();
                        link.setUrl(wtrLink.optString("url"));
                        ZhishuDetailBean bean = new ZhishuDetailBean(title,
                                summary, source, images, link);
                        datas.add(bean);
                    }

                }
            }
        }
        ZhishuDetailAdapter adapter = new ZhishuDetailAdapter(this, datas);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (position > 0) {
                    Intent intent = new Intent(ZhishuDetailActivity.this,
                            MyX5WebView.class);
                    intent.putExtra(Constant.WEBURL, datas.get(position - 1).getLink()
                            .getUrl());
                    jumpToActivity(intent);
                }

            }
        });

    }
}
