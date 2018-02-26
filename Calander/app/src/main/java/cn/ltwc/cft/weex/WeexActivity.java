package cn.ltwc.cft.weex;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.VideoActivity;

import java.util.Iterator;
import java.util.Map;

import cn.ltwc.cft.R;
import cn.ltwc.cft.activity.BaseActivity;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.view.TitleView;

import static cn.ltwc.cft.data.Constant.JS_NAME;
import static cn.ltwc.cft.data.Constant.OPTIONS;
import static cn.ltwc.cft.data.Constant.SHARE_URL;
import static cn.ltwc.cft.data.Constant.WEBTITLE;

/**
 * 动态展示界面的activity
 * Created by admin on 2017/7/27.
 */

public class WeexActivity extends BaseActivity {
    private TitleView title;
    private ViewGroup parent;
    private String jsName;
    private Map<String, Object> options;
    private String webTitle;//标题名称
    private String shareUrl;//分享的URL。
    private WeexUtil weexUtil;

    public WeexActivity() {
        super(R.layout.activity_weex);
    }

    @Override
    public void initView() {
        title = (TitleView) findViewById(R.id.weex_title);
        parent = findViewById(R.id.weex_content);
    }

    @Override
    public void initData() {
        jsName = getIntent().getStringExtra(JS_NAME);
        options = (Map<String, Object>) getIntent().getSerializableExtra(OPTIONS);
        webTitle = getIntent().getStringExtra(WEBTITLE);
        shareUrl = getIntent().getStringExtra(SHARE_URL);
    }

    @Override
    public void bindView() {
        title.setTitletext(webTitle);
        if (!TextUtils.isEmpty(shareUrl)) {
            title.setRightVisibility(View.VISIBLE);
            title.setRightIcon(R.drawable.title_share);
            title.getRightIcon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.notNull(options)) {
                        shareUrl += "?";
                        Iterator<Map.Entry<String, Object>> entryIterator = options.entrySet().iterator();
                        String key;
                        String value;
                        StringBuilder shareUrlBuilder = new StringBuilder(shareUrl);
                        for (Map.Entry<String, Object> entry : options.entrySet()) {

                            key = entry.getKey();
                            value = (String) entry.getValue();
                            if (key.equals("hot-reload_controller") || key.equals("_wx_tpl") || key.equals("bundleUrl")) {
                                continue;
                            }
                            shareUrlBuilder.append(key).append("=").append(value).append("&");
                        }
                        shareUrl = shareUrlBuilder.toString();
                        shareUrl = shareUrl.substring(0, shareUrl.length() - 1);

                    }
                    HLUtil.toMyShare(WeexActivity.this, Constant.SHARE_TYPE_WEB, "王朝黄历\n" + webTitle + "\n" + shareUrl, null, shareUrl);
                }
            });
        }

        title.getTitletext().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (weexUtil != null) {
                    weexUtil.selectWeexUrl();
                }
                return false;
            }
        });
        weexUtil = new WeexUtil(jsName, options, parent, this);
        weexUtil.fireFresh();

    }


    @Override
    public void onResume() {
        super.onResume();
        if (weexUtil != null) {
            weexUtil.mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (weexUtil != null) {
            weexUtil.mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (weexUtil != null) {
            weexUtil.mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (weexUtil != null) {
            weexUtil.mWXSDKInstance.onActivityDestroy();
        }
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
        VideoActivity a;
        TbsVideo tbsVideo;
    }
}
