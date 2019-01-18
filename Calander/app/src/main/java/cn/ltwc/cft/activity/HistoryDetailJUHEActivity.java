package cn.ltwc.cft.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.R;
import cn.ltwc.cft.adapter.HistoryOnToadyJUHEDeatilAdapter;
import cn.ltwc.cft.beans.HistoryOnTodayBeanJUHE;
import cn.ltwc.cft.beans.HistoryOnTodayImgBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.net.APIService;
import cn.ltwc.cft.net.ProgressSubscriber;
import cn.ltwc.bitmaputils.BitMapUtil;
import cn.ltwc.cft.utils.FileUtils;
import cn.ltwc.cft.utils.HLUtil;
import cn.ltwc.cft.utils.Utils;
import cn.ltwc.cft.view.TitleView;
import cn.ltwc.cft.weex.WeexActivity;
import rx.functions.Action1;

import static cn.ltwc.cft.data.Constant.DEFAULT_HOST_NAME;
import static cn.ltwc.cft.data.Constant.OPTIONS;
import static cn.ltwc.cft.data.Constant.SHARE_URL;

public class HistoryDetailJUHEActivity extends BaseActivity implements Action1<View> {
    private TitleView title;
    private HistoryOnTodayBeanJUHE bean;
    private List<HistoryOnTodayImgBean> imgUrl;
    public static HistoryDetailJUHEActivity instance;
    private String c = "";// 内容
    private String cachPath;
    private int num = 0;
    private RecyclerView rv;
    private HistoryOnToadyJUHEDeatilAdapter a;
    private Map<String, Object> options;
    private String shareUrl;//分享的URL。

    public HistoryDetailJUHEActivity() {
        super(R.layout.activity_juhe_history_detail);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        instance = this;
        title = (TitleView) findViewById(R.id.title);
        title.setTitletext("历史上的今天");
        title.setRightIcon(R.drawable.title_share);
        title.setRightVisibility(View.VISIBLE);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(instance);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        bean = (HistoryOnTodayBeanJUHE) getIntent()
                .getSerializableExtra("bean");
        options = (Map<String, Object>) getIntent().getSerializableExtra(OPTIONS);
        shareUrl = getIntent().getStringExtra(SHARE_URL);
        imgUrl = new ArrayList<HistoryOnTodayImgBean>();
        a = new HistoryOnToadyJUHEDeatilAdapter(this, bean, imgUrl, c);
        APIService.getInstance(Constant.URL_HISTORY_TODAY_JUHE, 1).getTodayOnHistoryDetail(bean.getE_id(), new ProgressSubscriber<Object>(new Action1<Object>() {
            @Override
            public void call(Object o) {
                httpSuccess((String) o);
            }
        }, true));
    }

    @Override
    public void bindView() {
        // TODO Auto-generated method stub
        rv.setAdapter(a);
        title.getRightIcon().setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Utils.notNull(options)) {
                    shareUrl += "?";
                    Iterator<Map.Entry<String, Object>> entryIterator = options.entrySet().iterator();
                    String key;
                    String value;
                    for (Map.Entry<String, Object> entry : options.entrySet()) {

                        key = entry.getKey();
                        value = (String) entry.getValue();
                        value = URLEncoder.encode(value);
                        if (key.equals("hot-reload_controller") || key.equals("_wx_tpl") || key.equals("bundleUrl")) {
                            continue;
                        }
                        shareUrl += key + "=" + value + "&";
                    }
                    shareUrl = shareUrl.substring(0, shareUrl.length() - 1);
                }
                HLUtil.toMyShare(HistoryDetailJUHEActivity.this, Constant.SHARE_TYPE_WEB,
                        getString(R.string.share_today_onhistory) + "\n" + bean.getTitle() + "\n" + shareUrl,
                        DEFAULT_HOST_NAME+"Ahuangshang/img/image_icon/todayonHistory.png", shareUrl);
            }
        });
        a.setListener(this);
    }

    @SuppressLint("InflateParams")
    @SuppressWarnings("deprecation")
    protected void createMenu(View v) {
        // TODO Auto-generated method stub
//		View popview = LayoutInflater.from(this).inflate(
//				R.layout.pop_share_menu, null);
//		final PopupWindow pop = new PopupWindow(popview,
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				ViewGroup.LayoutParams.WRAP_CONTENT, false);
//		// 需要设置一下此参数，点击外边可消失
//		pop.setBackgroundDrawable(new BitmapDrawable());
//		// 设置点击窗口外边窗口消失
//		pop.setOutsideTouchable(true);
//		// 设置此参数获得焦点，否则无法点击
//		pop.setFocusable(true);
//		pop.showAsDropDown(v, 0, 0);
//		popview.findViewById(R.id.linear_m_one).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						HLUtil.toMyShare(HistoryDetailJUHEActivity.this,
//								Constant.SHARE_TYPE_TEXT,
//								"\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t历史上的今天\n" + c,
//								null);
//						pop.dismiss();
//					}
//				});
//
//		popview.findViewById(R.id.linear_m_two).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						if (num != 0) {
//							if (FileUtils.isExit(cachPath)) {
//								num++;
//								HLUtil.toMyShare(
//										HistoryDetailJUHEActivity.this,
//										Constant.SHARE_TYPE_IMG,
//										"\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t历史上的今天\n"
//												+ c, cachPath);
//							} else {
//								num = 0;
//							}
//
//						}
//						if (num == 0) {
//							cachPath = FileUtils.buildCache(instance)
//									+ "王朝黄历.jpg";
//							final Bitmap bit = BitMapUtil.view2Bitmap(rv);
//							new Thread(new Runnable() {
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									try {
//										BitMapUtil.saveImg(bit, cachPath,
//												new Action1() {
//													@Override
//													public void call(Object o) {
//														num++;
//														HLUtil.toMyShare(HistoryDetailJUHEActivity.this,
//																Constant.SHARE_TYPE_IMG,
//																"\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t历史上的今天\n" + c, cachPath);
//													}
//												});
//									} catch (IOException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//										HistoryDetailJUHEActivity.instance
//												.show("获取资源失败");
//										return;
//									}
//								}
//							}).start();
//
//						}
//						pop.dismiss();
//
//					}
//				});

    }


    public void httpSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.optJSONArray("result");
            imgUrl.clear();
            if (array != null) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.optJSONObject(i);
                    c = obj.optString("content");
                    JSONArray a = obj.optJSONArray("picUrl");
                    for (int j = 0; j < a.length(); j++) {
                        JSONObject o = a.optJSONObject(j);
                        String pic_title = o.optString("pic_title");
                        pic_title = TextUtils.isEmpty(pic_title) ? "王朝黄历--历史上的今天" : pic_title;
                        String url = o.optString("url");
                        HistoryOnTodayImgBean bean = new HistoryOnTodayImgBean(
                                pic_title, url);
                        imgUrl.add(bean);
                    }
                }
                a.setCon(c);
                a.notifyDataSetChanged();
            } else {
                // 提示用户
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // adpter.notifyDataSetChanged();
    }


    public void saveSuccess() {
        num++;
        HLUtil.toMyShare(HistoryDetailJUHEActivity.this,
                Constant.SHARE_TYPE_IMG,
                "\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t历史上的今天\n" + c, cachPath);
    }

    @Override
    public void call(View view) {
        MyApplication.getInstance().showProgressDialog();
        cachPath = FileUtils.buildCache(instance) + "王朝黄历.jpg";
        final Bitmap bit = BitMapUtil.view2Bitmap(view);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    BitMapUtil.saveImg(bit, cachPath, new Action1() {
                        @Override
                        public void call(Object o) {
                            MyApplication.getInstance().disMissProgressDialog();
                            num++;
                            HLUtil.toMyShare(HistoryDetailJUHEActivity.this,
                                    Constant.SHARE_TYPE_IMG,
                                    "\t\t\t\t\t\t\t王朝黄历\n\t\t\t\t\t历史上的今天\n" + c, cachPath);
                        }
                    });
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    MyApplication.getInstance().disMissProgressDialog();
                    HistoryDetailJUHEActivity.instance.show("获取资源失败");
                    return;
                }
            }
        }).start();
    }
}
