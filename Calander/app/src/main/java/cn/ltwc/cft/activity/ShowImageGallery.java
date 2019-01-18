package cn.ltwc.cft.activity;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import cn.ltwc.bitmaputils.rvviewpager.RecyclerViewPager;
import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.ShowImageGalleryAdapter;
import cn.ltwc.cft.beans.TiangouBean;
import cn.ltwc.cft.data.Constant;

/**
 * 显示大图片（cn.ltwc.cft.gallery包下面就是显示该效果的文件）
 *
 * @author LZL
 */
public class ShowImageGallery extends BaseActivity {
    private List<TiangouBean> imgList;// 图片信息的集合
    private int position;// 当前点击进来的是第几张
    private RecyclerViewPager rv;

    public ShowImageGallery() {
        super(R.layout.activity_show_image_gallery);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        rv = (RecyclerViewPager) findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        imgList = getIntent().getParcelableArrayListExtra(Constant.IMGURL_LIST);
        position = getIntent().getIntExtra(Constant.POSITION, 0);
    }

    @Override
    public void bindView() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layout);
        ShowImageGalleryAdapter adapter = new ShowImageGalleryAdapter(this, imgList);
        rv.setAdapter(adapter);
        rv.scrollToPosition(position);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imgList != null) {
            imgList = null;
        }

    }
}
