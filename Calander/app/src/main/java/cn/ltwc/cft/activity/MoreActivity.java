package cn.ltwc.cft.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.MenuBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.weex.WeexActivity;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;

import static cn.ltwc.cft.data.Constant.JS_NAME;
import static cn.ltwc.cft.data.Constant.WEBTITLE;

/**
 * TODO:更多的Activity
 *
 * @author huangshang 2015-11-15 下午10:47:56
 * @Modified_By:
 */
public class MoreActivity extends BaseActivity {
    private TitleView title;
    private RecyclerView list;
    private List<MenuBean> menu;

    public MoreActivity() {
        super(R.layout.activity_more);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        title = (TitleView) findViewById(R.id.more_title);
        title.setLeftIcon(R.drawable.title_back);
        title.setRightVisibility(View.INVISIBLE);// 设置右边图片不显示
        title.setTitletext("更多");
        list = (RecyclerView) findViewById(R.id.more_list);// 列表视图
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        menu = new ArrayList<>();
        //menu.add(new MenuBean(R.drawable.phone, "手机号码归属地查询"));
        menu.add(new MenuBean(R.drawable.message, "消息中心"));
        menu.add(new MenuBean(R.drawable.zhainan, "宅男天堂"));
        menu.add(new MenuBean(R.drawable.todayonhistory, "历史上的今天"));
        menu.add(new MenuBean(R.drawable.joke, "内涵段子"));
        menu.add(new MenuBean(R.drawable.junshi, "中华军事"));
        menu.add(new MenuBean(R.drawable.qq, "QQ空間遊戲"));
        menu.add(new MenuBean(R.drawable.wifi, "WIFI密码查看"));
    }

    @Override
    public void bindView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        CommonAdapter adapter = new CommonAdapter<MenuBean>(this, R.layout.item_more, menu) {
            @Override
            protected void convert(ViewHolder holder, MenuBean o, int position) {
                holder.setText(R.id.more_adapter_txt, o.getMenuName());
                holder.setImageResource(R.id.more_adapter_img, o.getSelectIcon());
            }
        };

        // 列表的点击事件
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                switch (menu.get(position).getSelectIcon()) {
                    case R.drawable.message:
                        Intent t = new Intent(c, WeexActivity.class);
                        t.putExtra(JS_NAME, "news");
                        t.putExtra(WEBTITLE, "消息记录");
                        jumpToActivity(t);
                        break;
                    case R.drawable.junshi:
                        Intent intent = new Intent(c, MyX5WebView.class);
                        intent.putExtra(Constant.WEBURL,
                                "http://3g.china.com/html/mili.html");
                        intent.putExtra(Constant.WEBTITLE, "中华军事");
                        jumpToActivity(intent);
                        break;
                    case R.drawable.joke:
                        jumpToActivity(new Intent(c, JokeActivity.class));
                        break;
                    case R.drawable.zhainan:
                        jumpToActivity(new Intent(c, ZhaiNaniActivity.class));
                        break;
                    case R.drawable.todayonhistory:
                        jumpToActivity(new Intent(c, TodayonhistoryActivity.class));
                        break;
                    case R.drawable.qq:
                        Intent qq = new Intent(c, MyX5WebView.class);
                        qq.putExtra(Constant.WEBURL,
                                "http://mfkp.qzapp.z.qq.com/qshow/cgi-bin/wl_card_mainpage");
                        qq.putExtra(WEBTITLE, "QQ");
                        jumpToActivity(qq);
                        break;
                    case R.drawable.wifi:
                        jumpToActivity(new Intent(c, ShowWifiPakActivity.class));
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        list.setAdapter(adapter);
    }
}
