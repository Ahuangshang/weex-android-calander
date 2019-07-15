package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.net.URLDecoder;

import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.x5web.utils.X5WebView;
import cn.ltwc.utils.LogUtil;

/**
 * 资讯详情页
 * Created by Queen on 2018/1/27 0027.
 */

public class NewsDetailActivity extends BaseActivity {
    private String title, content, webUrl, imgUrl, shareUrl, shareDec;

    private X5WebView x5WebView;


    public NewsDetailActivity() {
        super(R.layout.news_detail);
    }

    @Override
    public void initView() {
        x5WebView = (X5WebView) findViewById(R.id.web);
        x5WebView.getSettings().setSupportZoom(false);
        x5WebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        x5WebView.getSettings().setDefaultTextEncodingName("utf-8");

    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra(Constant.TITLE);
        content = getIntent().getStringExtra(Constant.CONTENT);
        webUrl = getIntent().getStringExtra(Constant.WEBURL);
        imgUrl = getIntent().getStringExtra(Constant.IMG_URL);
        shareUrl = getIntent().getStringExtra(Constant.SHARE_URL);
        shareDec = getIntent().getStringExtra(Constant.SHARE_DEC);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void bindView() {
        TitleView titleView = (TitleView) findViewById(R.id.title_view);
        titleView.setTitletext(title);
        titleView.setRightIcon(R.drawable.title_share);
        titleView.setRightVisibility(TextUtils.isEmpty(shareUrl) ? View.GONE : View.VISIBLE);
        titleView.getRightIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HLUtil.toMyShare(NewsDetailActivity.this, Constant.SHARE_TYPE_WEB, getString(R.string.share_news_detail) + "\n" + (TextUtils.isEmpty(shareDec) ? title : shareDec) + "\n" + shareUrl, imgUrl, shareUrl);
            }
        });
        x5WebView.getSettings().setJavaScriptEnabled(true);
        x5WebView.addJavascriptInterface(this, "ltwc");
        x5WebView.loadDataWithBaseURL(null, URLDecoder.decode(content), "text/html", "UTF-8", null);

    }

    @Override
    public void finish() {
        super.finish();
        x5WebView = null;
    }

    @JavascriptInterface
    public void linkThird() {
        if (TextUtils.isEmpty(webUrl)) {
            return;
        }
        Intent intent = new Intent(this, MyX5WebView.class);
        intent.putExtra(Constant.WEBURL,
                webUrl);
        intent.putExtra(Constant.WEBTITLE, title);
        intent.putExtra(Constant.CAN_GO_BACK, false);
        intent.putExtra(Constant.BAR_SHOW, false);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
