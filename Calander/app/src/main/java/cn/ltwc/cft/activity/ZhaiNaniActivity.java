package cn.ltwc.cft.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.bitmaputils.ScreenUtils;
import cn.ltwc.bitmaputils.glide.GlideListener;
import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.TiangouBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.helper.HideService;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.cft.view.ChooseView;
import cn.ltwc.cft.view.SpacesItemDecoration;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;
import cn.ltwc.viewutils.recycleviewutils.fresh.MRefreshView;
import cn.ltwc.viewutils.recycleviewutils.fresh.RefreshUpView;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.PAGE_SIZE;

/**
 * TODO:宅男天堂的Activity
 *
 * @author huangshang
 * @Modified_By:
 */
public class ZhaiNaniActivity extends BaseActivity implements Action1<Integer> {
    private MRefreshView mRefreshView;
    private String TAG = "ZhaiNaniActivity";
    private ArrayList<TiangouBean> al = null;// 存放数据的集合
    private TitleView titleView;
    private RecyclerView rv;
    private CommonAdapter adapter;
    private int pageNum;
    private List<String> girlType;
    private int currentType;
    private ChooseView chooseView;

    public ZhaiNaniActivity() {
        super(R.layout.activity_zhainan);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        //startHideService();
        titleView = (TitleView) findViewById(R.id.zhainan_title);
        titleView.setLeftIcon(R.drawable.title_back);
        titleView.setRightVisibility(View.VISIBLE);
        titleView.setRightIcon(R.drawable.title_more);
        titleView.setTitletext(girlType.get(0));
        mRefreshView = (MRefreshView) findViewById(R.id.refresh_view);
        rv = (RecyclerView) findViewById(R.id.rv);
        // 设置为瀑布流
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        // 设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        rv.addItemDecoration(decoration);
        final int with = ScreenUtils.getScreenWith(this) / 2 - 24;
        adapter = new CommonAdapter<TiangouBean>(this, R.layout.item_zhainan, al) {
            @Override
            protected void convert(ViewHolder holder, TiangouBean tiangouBean, int position) {
                holder.setText(R.id.zainan_title, Html.fromHtml(tiangouBean.getTitle()));
                //GlideUtil.loadImgWithDifferentHeight(ZhaiNaniActivity.this, al.get(position).getImg(), (ImageView) holder.getView(R.id.zainan_img), 0, with);
                GlideUtil.loadImgWithListener(ZhaiNaniActivity.this, al.get(position).getImg(), GlideListener.SETALL, ImageView.ScaleType.CENTER_CROP, null, R.drawable.main_color_perload, R.drawable.load_failed2, (ImageView) holder.getView(R.id.zainan_img));
            }
        };
        adapter.setCustomLoadMoreView(new RefreshUpView(this));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                onClick(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public void initData() {
        al = new ArrayList<>();
        girlType = new ArrayList();
        girlType.add("校花");
        girlType.add("时尚");
        girlType.add("少女");
        girlType.add("唯美");
        girlType.add("气质");
        girlType.add("可爱");
        girlType.add("素颜");
        girlType.add("张天爱");
        girlType.add("赵丽颖");
        girlType.add("林允儿");
        girlType.add("高圆圆");
        girlType.add("刘诗诗");
        girlType.add("宋慧乔");
        girlType.add("甜素纯");
        girlType.add("小清新");
        girlType.add("足球宝贝");
        girlType.add("网络美女");
        girlType.add("新垣结衣");
    }

    @Override
    public void bindView() {
        titleView.getRightIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseView == null) {
                    chooseView = new ChooseView(ZhaiNaniActivity.this);
                    chooseView.setList(girlType);
                    chooseView.setChooseBack(ZhaiNaniActivity.this);
                    chooseView.setSearchCall(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            search(s);
                        }
                    });
                }
                chooseView.show();
            }
        });
        getData(false, true);
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                getData(false, false);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                getData(true, false);
            }
        });


    }

    /**
     * 获取数据
     *
     * @param isLoadMore
     */

    private void getData(final boolean isLoadMore, boolean showProgress) {
        if (isLoadMore) {
            pageNum += PAGE_SIZE;
        } else {
            GlideUtil.clearImageMemoryCache(this);
            GlideUtil.trimMemory(this, TRIM_MEMORY_RUNNING_LOW);
            pageNum = 0;
        }
        APIService.getInstance(Constant.URL_GET_GIRL_IMG, 1).getGirlImg(pageNum, titleView.getTitletext().getText().toString(), new ProgressSubscriber<Object>(new Action1<Object>() {
            @Override
            public void call(Object o) {
                httpSuccess((String) o, isLoadMore);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (isLoadMore) {
                    mRefreshView.stopLoadMore(false);
                } else {
                    mRefreshView.stopRefresh();
                }
            }
        }, showProgress));
    }

    /**
     * 成功时
     */
    public void httpSuccess(String result, boolean isLoadMore) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray JSONArray = jsonObject.getJSONArray("data");
            if (isLoadMore) {
                if (JSONArray.length() < PAGE_SIZE) {
                    mRefreshView.setLoadComplete(true);
                } else {
                    mRefreshView.stopLoadMore(false);
                }
            } else {
                al.clear();
                if (JSONArray.length() < PAGE_SIZE) {
                    mRefreshView.setLoadComplete(true);
                } else {
                    mRefreshView.stopRefresh();
                }
            }
            for (int i = 0; i < JSONArray.length(); i++) {
                JSONObject jsonObject2 = JSONArray.getJSONObject(i);
                String title = jsonObject2.getString("title");
                String img = jsonObject2.getString("obj_url");
                TiangouBean bean = new TiangouBean(img, title);
                al.add(bean);

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            adapter.notifyDataSetChanged();
            e.printStackTrace();
        }


    }

    public void onClick(int position) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(ZhaiNaniActivity.this,
                ShowImageGallery.class);
        intent.putParcelableArrayListExtra(Constant.IMGURL_LIST, al);
        intent.putExtra(Constant.POSITION, position);
        jumpToActivity(intent);
    }

    /**
     * 选择返回
     *
     * @param position
     */
    @Override
    public void call(Integer position) {
        if (currentType != position) {
            pageNum = 0;
            currentType = position;
            titleView.setTitletext(girlType.get(currentType));
            getData(false, true);
        }
    }

    private void search(String s) {
        if (!TextUtils.isEmpty(s)) {
            titleView.setTitletext(s);
            getData(false, true);
        }
    }

    private void startHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.startService(intent);
    }

    private void stopHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHideService();
    }

    @Override
    public void finish() {
        super.finish();

        if (al != null) {
            al.clear();
            al = null;
        }
        if (girlType != null) {
            girlType.clear();
            girlType = null;
        }
        if (rv != null) {
            rv = null;
        }
        if (adapter != null) {
            adapter = null;
        }

    }
}
