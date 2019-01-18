package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.HistoryOnTodayBeanJUHE;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.DEFAULT_HOST_NAME;
import static cn.ltwc.cft.data.Constant.OPTIONS;
import static cn.ltwc.cft.data.Constant.SHARE_URL;

/**
 * TODO:历史上的今天
 *
 * @author LZL
 */
public class TodayonhistoryActivity extends BaseActivity {
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private TitleView title;
    private RecyclerView rv;
    private List<HistoryOnTodayBeanJUHE> juheList;
    private CommonAdapter juheAdapter;

    private View head;
    private TextView headTitle;

    public TodayonhistoryActivity() {
        super(R.layout.activity_todayonhistory);
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        // TODO Auto-generated method stub
        title = (TitleView) findViewById(R.id.title);
        rv = (RecyclerView) findViewById(R.id.history_lv);
        head = LayoutInflater.from(this).inflate(R.layout.history_head_view,
                null);
        headTitle = (TextView) head.findViewById(R.id.head_title);
        headTitle.setText("历史上的" + month_c + "月" + day_c + "日" + "都发生了什么");
        head.setVisibility(View.GONE);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void initData() {
        // TODO Auto-generated method stub
        juheList = new ArrayList<HistoryOnTodayBeanJUHE>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date); // 当期日期
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
    }

    @Override
    public void bindView() {
        title.setTitletext("历史上的今天");
        title.setRightVisibility(View.VISIBLE);
        title.setRightIcon(R.drawable.title_more);
        title.getRightIcon().setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(TodayonhistoryActivity.this,
                        MyX5WebView.class);
                intent.putExtra(Constant.WEBURL,
                        "http://www.todayonhistory.com");
                intent.putExtra(Constant.WEBTITLE, "历史上的今天");
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        juheAdapter = new CommonAdapter<HistoryOnTodayBeanJUHE>(this, R.layout.item_history_on_today, juheList) {
            @Override
            protected void convert(ViewHolder holder, HistoryOnTodayBeanJUHE o, int position) {
                holder.setText(R.id.year, o.getYear().substring(0, o.getYear().indexOf("年") + 1));
                holder.setText(R.id.title, o.getTitle());

            }
        };
        juheAdapter.setHeaderView(head, rv);
        rv.setAdapter(juheAdapter);
        juheAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position == 0) {
                    return;
                }
                Intent intent = new Intent(TodayonhistoryActivity.this,
                        HistoryDetailJUHEActivity.class);
                intent.putExtra("bean", juheList.get(position - 1));
                intent.putExtra(SHARE_URL, DEFAULT_HOST_NAME + "Ahuangshang/html/todayOnHistory.html");
                final HistoryOnTodayBeanJUHE beanJUHE = juheList.get(position - 1);
                intent.putExtra(OPTIONS, new HashMap<String, String>() {
                    {
                        put("year", beanJUHE.getYear());
                        put("title", beanJUHE.getTitle());
                        put("e_id", beanJUHE.getE_id());
                    }
                });
                jumpToActivity(intent);
//                Intent intent = new Intent(TodayonhistoryActivity.this, WeexActivity.class);
//                intent.putExtra(JS_NAME, "home");
//                intent.putExtra(WEBTITLE, "历史上的今天");
//                intent.putExtra(SHARE_URL, "http://localhost:63342/rili_weex/weex.html");
//                final HistoryOnTodayBeanJUHE beanJUHE = juheList.get(position - 1);
//                intent.putExtra(OPTIONS, new HashMap<String, String>() {
//                    {
//                        put("year", beanJUHE.getYear());
//                        put("title", beanJUHE.getTitle());
//                        put("e_id", beanJUHE.getE_id());
//                    }
//                });
//                jumpToActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        getData();
    }

    public void getData() {
        String date = month_c + "/" + day_c;
        APIService.getInstance(Constant.URL_HISTORY_TODAY_JUHE, 1).getTodayOnHistory(date, new ProgressSubscriber<Object>(new Action1<Object>() {
            @Override
            public void call(Object o) {
                head.setVisibility(View.VISIBLE);
                success((String) o);
            }
        }, true));
    }

    public void success(String result) {
        juheList.clear();
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray array = obj.optJSONArray("result");
            if (array != null) {
                for (int i = array.length() - 1; i > -1; i--) {
                    JSONObject object = array.getJSONObject(i);
                    String title = object.optString("title");
                    String data = object.optString("date");
                    String e_id = object.optString("e_id");
                    HistoryOnTodayBeanJUHE beanJUHE = new HistoryOnTodayBeanJUHE(
                            data, title, e_id);
                    juheList.add(beanJUHE);
                }
                juheAdapter.notifyDataSetChanged();
            }

        } catch (Exception e) {

        }
    }
}
