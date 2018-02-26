package cn.ltwc.cft.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.beans.HistoryOnTodayBeanJUHE;
import cn.ltwc.cft.beans.HistoryOnTodayImgBean;
import rx.functions.Action1;

public class HistoryOnToadyJUHEDeatilAdapter extends
        RecyclerView.Adapter<ViewHolder> {
    private final int LAYOUT_ONE = 1;
    private final int LAYOUT_TWO = 2;
    private final int LAYOUT_THREE = 3;
    private Context c;
    private HistoryOnTodayBeanJUHE bean;
    private List<HistoryOnTodayImgBean> imgUrl;// 图片地址的集合
    private String con = "";// 内容
    private Action1<View> listener;

    public HistoryOnToadyJUHEDeatilAdapter(Context c,
                                           HistoryOnTodayBeanJUHE bean, List<HistoryOnTodayImgBean> imgUrl,
                                           String con) {
        super();
        this.c = c;
        this.bean = bean;
        this.imgUrl = imgUrl;
        this.con = con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public void setListener(Action1<View> listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return imgUrl.size() + 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO Auto-generated method stub
        int viewType = getItemViewType(position);
        switch (viewType) {
            case LAYOUT_ONE:
                TitleHolder tHolder = (TitleHolder) holder;
                tHolder.setUI();
                break;
            case LAYOUT_TWO:
                ImageHolder imgHolder = (ImageHolder) holder;
                imgHolder.setUI(position - 1);
                break;
            case LAYOUT_THREE:
                ContentHolder cHolder = (ContentHolder) holder;
                cHolder.setUI();
                break;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO Auto-generated method stub
        switch (viewType) {
            case LAYOUT_ONE:
                TitleHolder tHolder = new TitleHolder(LayoutInflater.from(c)
                        .inflate(R.layout.item_history_on_today_title, parent,
                                false));
                return tHolder;
            case LAYOUT_TWO:
                ImageHolder imgHolder = new ImageHolder(LayoutInflater.from(c)
                        .inflate(R.layout.item_history_on_today_img, parent, false));
                return imgHolder;
            case LAYOUT_THREE:
                ContentHolder cHolder = new ContentHolder(LayoutInflater.from(c)
                        .inflate(R.layout.item_history_on_today_content, parent,
                                false));
                return cHolder;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LAYOUT_ONE;
        } else if (position == getItemCount() - 1) {
            return LAYOUT_THREE;
        } else {
            return LAYOUT_TWO;
        }
    }

    public class ImageHolder extends ViewHolder {
        ImageView img;
        TextView imgName;
        View item;

        public ImageHolder(View itemView) {
            super(itemView);
            // TODO Auto-generated constructor stub
            img = (ImageView) itemView.findViewById(R.id.img_show);
            imgName = (TextView) itemView.findViewById(R.id.img_title);
            item = itemView.findViewById(R.id.item);
        }

        public void setUI(int i) {
            // TODO Auto-generated method stub
            HistoryOnTodayImgBean imgBean = imgUrl.get(i);
            GlideUtil.loadImgWithDifferentHeight(c, imgBean.getImgUrl(), img, 0);
            imgName.setText(imgBean.getImgTitle());
            imgName.setVisibility(TextUtils.isEmpty(imgBean.getImgTitle()) ? View.GONE
                    : View.VISIBLE);
            item.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.call(v);
                    }
                    return false;
                }
            });
        }

    }

    public class TitleHolder extends ViewHolder {
        TextView year, title;

        public TitleHolder(View itemView) {
            super(itemView);
            // TODO Auto-generated constructor stub
            year = (TextView) itemView.findViewById(R.id.juhe_history_year);
            title = (TextView) itemView.findViewById(R.id.juhe_history_title);
        }

        public void setUI() {
            // TODO Auto-generated method stub
            year.setText(bean.getYear());
            title.setText(bean.getTitle());
        }

    }

    public class ContentHolder extends ViewHolder {
        TextView content;

        public ContentHolder(View itemView) {
            super(itemView);
            // TODO Auto-generated constructor stub
            content = (TextView) itemView.findViewById(R.id.juhe_history_event);
        }

        public void setUI() {
            // TODO Auto-generated method stub
            content.setText(con);
        }

    }
}
