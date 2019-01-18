package cn.ltwc.cft.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andview.refreshview.XRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.entiy.Joke;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;

import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;
import cn.ltwc.viewutils.recycleviewutils.fresh.MRefreshView;
import cn.ltwc.viewutils.recycleviewutils.fresh.RefreshUpView;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.URL_GET_JOKE;

/**
 * TODO:内涵段子的Activity
 *
 * @author huangshang 2015-11-15 下午10:46:42
 * @Modified_By:
 */
public class JokeActivity extends BaseActivity {
    private TitleView title;
    private RecyclerView listJoke;
    private List<Joke> jokeList;// 笑话的集合
    private CommonAdapter adapter;
    private MRefreshView freshView;

    public JokeActivity() {
        super(R.layout.activity_joke);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        title = (TitleView) findViewById(R.id.joke_title);
        title.setTitletext("一起笑吧");
        title.setRightVisibility(View.INVISIBLE);
        listJoke = (RecyclerView) findViewById(R.id.joke_list);
        freshView = (MRefreshView) findViewById(R.id.fresh_view);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        listJoke.setLayoutManager(lm);
    }

    @Override
    public void initData() {
        jokeList = new ArrayList<Joke>();
    }

    @Override
    public void bindView() {
        freshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                getData(false, false);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                getData(true, false);
            }
        });
        adapter = new CommonAdapter<Joke>(this, R.layout.item_joke, jokeList) {
            @Override
            protected void convert(ViewHolder holder, Joke joke, int position) {
                holder.setText(R.id.content, joke.getContent());
            }
        };
        adapter.setCustomLoadMoreView(new RefreshUpView(this));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                HLUtil.toMyShare(
                        c,
                        Constant.SHARE_TYPE_TEXT,
                        "\t\t\t\t王朝黄历内涵段子\n"
                                + jokeList.get(position).getContent()
                                .replaceAll(" ", "")
                                .replaceAll("\r\n\r\r\r\r\r\r\r\r",
                                        "\r\n")
                                .replaceAll("\r\r\r\r\r\r\r\r", ""),
                        null);
                return false;
            }
        });
        listJoke.setAdapter(adapter);
        getData(false, true);
    }

    private void getData(final boolean isLoadMore, boolean showProgress) {
        long beforeDate = 0;
        if (isLoadMore && jokeList.size() > 0) {
            beforeDate = jokeList.get(jokeList.size() - 1).getPublishTime();
        }
        APIService.getInstance(URL_GET_JOKE, 1).getJoke(isLoadMore, beforeDate, 1, new ProgressSubscriber<Object>(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (isLoadMore) {
                    freshView.stopLoadMore(true);
                } else {
                    jokeList.clear();
                    freshView.stopRefresh();
                }
                httpSuccess((String) o);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (isLoadMore) {
                    freshView.stopLoadMore(true);
                } else {
                    freshView.stopRefresh();
                }
            }
        }, showProgress));
    }

    public void httpSuccess(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject o = jsonArray.optJSONObject(i);
                    String content = o.optString("Content");
                    long publishTime = o.optLong("Pubtime");
                    jokeList.add(new Joke(content, publishTime));
                }
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
