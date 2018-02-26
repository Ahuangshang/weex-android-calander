package cn.ltwc.cft.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import cn.ltwc.bitmaputils.BitMapUtil;
import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.utils.PopWindowUtil;
import cn.ltwc.viewutils.recycleviewutils.CommonAdapter;
import cn.ltwc.viewutils.recycleviewutils.MultiItemTypeAdapter;
import cn.ltwc.viewutils.recycleviewutils.base.ViewHolder;
import rx.functions.Action1;

/**
 * ChooseView
 * Created by LZL on 17/3/17.
 */

public class ChooseView extends LinearLayout {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.search_layout)
    View searchLayout;
    @BindView(R.id.search)
    EditText search;
    private View chooseView;
    private Action1<Integer> chooseBack;
    private CommonAdapter adapter;
    private List<String> list;
    private Action1<String> searchCall;
    public static final int SELECT_WEEX_URL = 1;
    private int flag = -1;

    public void setList(List<String> list) {
        setList(list, VISIBLE);
    }

    public void setList(List<String> list, int visibility) {
        this.list.clear();
        this.list.addAll(list);
        if (list.size() > 5) {
            ViewGroup.LayoutParams layoutParams = rv.getLayoutParams();
            layoutParams.height = BitMapUtil.dip2px(MyApplication.getInstance(), 350);
        }
        searchLayout.setVisibility(visibility);
        adapter.notifyDataSetChanged();
    }

    public ChooseView(Context context) {
        super(context);
        init(context, -1);
    }

    public ChooseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, -1);
    }

    public ChooseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, -1);
    }

    @TargetApi(21)
    public ChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, -1);
    }

    public ChooseView(Context context, int flag) {
        super(context);
        init(context, flag);
    }

    public void setChooseBack(Action1<Integer> chooseBack) {
        this.chooseBack = chooseBack;
    }

    private void init(Context context, int flag) {
        this.flag = flag;
        chooseView = LayoutInflater.from(context).inflate(R.layout.view_choose_item, this);
        ButterKnife.bind(chooseView);
        initRecycleView(context);
        if (this.flag == SELECT_WEEX_URL) {
            search.setHint("输入你要链接的IP,样式为 192.168.16.89");
        } else {
            search.setHint("输入您要搜索的美女");
        }
    }

    private void initRecycleView(final Context context) {
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new CommonAdapter<String>(context, R.layout.item_choose, list) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                ((TextView) holder.getView(R.id.item)).setText(o);
                if (position == list.size() - 1) {
                    holder.getView(R.id.line).setVisibility(GONE);
                } else {
                    holder.getView(R.id.line).setVisibility(VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                dismiss();
                if (chooseBack != null) {
                    chooseBack.call(position);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        dismiss();
    }

    public void show() {
        PopWindowUtil.make(chooseView).setBackKeyDismiss(true).show();
    }

    public void dismiss() {
        PopWindowUtil.getPopWindowUtil().dismiss();
    }

    public void setSearchCall(Action1<String> searchCall) {
        this.searchCall = searchCall;
    }

    @OnEditorAction(R.id.search)
    public boolean doSearch() {
        if (searchCall != null) {
            dismiss();
            if (flag == SELECT_WEEX_URL) {
                StringBuilder sb = new StringBuilder();
                sb.append("http://").append(search.getText().toString().trim()).append(":8081/").append("dist/weex/");
                searchCall.call(sb.toString());
            } else {
                searchCall.call(search.getText().toString().trim());
            }
        }
        return true;
    }
}
