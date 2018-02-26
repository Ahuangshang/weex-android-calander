package cn.ltwc.cft.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.net.URLDecoder;

import cn.ltwc.bitmaputils.glide.GlideUtil;
import cn.ltwc.cft.R;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.x5web.utils.X5WebView;

/**
 * 资讯详情页
 * Created by Queen on 2018/1/27 0027.
 */

public class NewsDetailActivity extends BaseActivity {
    private String title, content, webUrl, imgUrl, shareUrl, shareDec;
    private ImageView img;
    private X5WebView webView, webViewHide;
    private View viewContent;
    private TextView link;

    public NewsDetailActivity() {
        super(R.layout.news_detail);
    }

    @Override
    public void initView() {
        img = (ImageView) findViewById(R.id.img);
        webView = (X5WebView) findViewById(R.id.web);
        webViewHide = (X5WebView) findViewById(R.id.web_hide);
        viewContent = findViewById(R.id.view_content);
        link = (TextView) findViewById(R.id.link);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        webViewHide.getSettings().setSupportZoom(false);
        webViewHide.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        webViewHide.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDefaultTextEncodingName("utf-8");

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

    @Override
    public void bindView() {
        TitleView titleView = (TitleView) findViewById(R.id.title_view);
        titleView.setTitletext(title);
        titleView.setRightIcon(R.drawable.title_share);
        titleView.setRightVisibility(TextUtils.isEmpty(shareUrl) ? View.GONE : View.VISIBLE);
        titleView.getRightIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HLUtil.toMyShare(NewsDetailActivity.this, Constant.SHARE_TYPE_WEB, getString(R.string.share_news_detail) + "\n" + (TextUtils.isEmpty(shareDec) ? title : shareDec) + "\n" + shareUrl, null, shareUrl);
            }
        });
        img.setVisibility(TextUtils.isEmpty(imgUrl) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(imgUrl)) {
            GlideUtil.loadImg(this, imgUrl, img);
        }
        webView.loadDataWithBaseURL(null, URLDecoder.decode(content), "text/html", "UTF-8", null);
        webViewHide.loadDataWithBaseURL(null, URLDecoder.decode(content), "text/html", "UTF-8", null);
        webViewHide.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String s) {
                super.onPageFinished(view, s);
                webViewHide.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewContent.setVisibility(View.VISIBLE);
                        webViewHide.measure(0, 0);
                        int measuredHeight = webViewHide.getMeasuredHeight();
                        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                        layoutParams.height = measuredHeight;
                        webView.setLayoutParams(layoutParams);
                    }
                }, 100);

            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                NewsDetailActivity.this.webView.loadUrl(s);
                return true;
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link(v);
            }
        });

    }

    public void link(View view) {
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

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        String style = "<style>" +
                "  body{" +
                "    -webkit-user-select: none;" +
                "    -webkit-tap-highlight-color: transparent;" +
                "  }" +
                "</style>";
        return "<html>" + head + style + "<body>" + bodyHTML + "</body></html>";
    }
}
