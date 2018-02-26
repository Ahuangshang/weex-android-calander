package cn.ltwc.cft.data;

import android.Manifest;

import java.io.File;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.utils.FileUtils;

/**
 * TODO:本应用的常量类
 *
 * @author huangshang 2015-11-10 下午2:22:42
 * @Modified_By:
 */
public class Constant {
    public static long exitTime = 2000;// 程序退出时间
    public static boolean DEBUG = false;
    // 农历部分假日
    public final static String[] lunarHoliday = new String[]{"0101 春节",
            "0115 元宵节", "0202 龙抬头", "0504 皇上生辰", "0505 端午", "0707 乞巧节", "0715 中元节",
            "0815 中秋节", "0909 重阳节", "1208 腊八", "1224 小年", "1230 除夕"};
    // 公历部分节假日
    public final static String[] solarHoliday = new String[]{"0101 元旦",
            "0214 情人节", "0308 妇女节", "0312 植树节", "0401 愚人节", "0501 劳动节",
            "0504 青年节", "0512 护士节", "0601 儿童节", "0701 建党节", "0801 建军节",
            "0903 抗战胜利", "0910 教师节", "0918 九一八", "0928 孔子诞辰", "1001 国庆节",
            "1006 老人", "1024 联合国日", "1213 国家公祭", "1220 澳门回归", "1225 圣诞",};
    // 公历放假的日子
    public final static String[] solarFajia = new String[]{"0101 元旦", "0308 妇女节",
            "0501 劳动节", "0601 儿童节", "1001 国庆节"};

    // 农历放假的日子
    public final static String[] lunarFajia = new String[]{"1230 除夕",
            "0101 春节", "0115 元宵节", "0504 皇上生辰", "0505 端午", "0815 中秋节",
            "1208 腊八", "1224 小年",

    };
    // 所有的节日和二十四节气
    public final static String[] holiday = new String[]{"春节", "元宵节", "龙抬头", "皇上生辰",
            "端午", "乞巧节", "中元节", "中秋节", "重阳节", "腊八", "小年", "除夕", "元旦", "情人节",
            "妇女节", "植树节", "愚人节", "劳动节", "青年节", "护士节", "儿童节", "建党节、香港回归纪念日",
            "建军节", "抗战胜利纪念日", "教师节", "九一八事变", "孔子诞辰", "国庆节", "老人", "联合国日",
            "南京大屠杀国家公祭日", "澳门回归纪念日", "圣诞", "大寒", "雨水", "春分", "谷雨", "小满", "夏至",
            "大暑", "处暑", "秋分", "霜降", "小雪", "冬至", "小寒", "立春", "惊蛰", "清明", "立夏",
            "芒种", "小暑", "立秋", "白露", "寒露", "立冬", "大雪"};
    // 所有放假的节日
    public final static String[] FANGJIAHOLIDAY = new String[]{"春节", "皇上生辰",
            "端午", "中秋节", "除夕", "元旦", "妇女节", "劳动节", "儿童节", "国庆节", "清明"};

    public final static String[] YUEEN = new String[]{"Jan.", "Feb.", "Mar.",
            "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sept.", "Oct.", "Nov.",
            "Dec."};
    public final static String[] NEWS_DEFAULT_TABS = new String[]{
            "头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"
    };
    public static final String NOTE_BEAN = "noteBean";
    public static final String FLAG = "flag";
    public static final String RILIINFO = "riliInfo";
    public static final String APPPACKAGENAME = "cn.ltwc.cft";
    public static final String WEBURL = "webUrl";
    public static final String WEBTITLE = "webTitle";
    public static final String IMGURL_LIST = "imgList";
    public static final String POSITION = "position";
    public static final String SHARE_TYPE_TEXT = "text/plain";
    public static final String SHARE_TYPE_IMG = "image/*";
    public static final String SHARE_TYPE_AUDIO = "audio/*";
    public static final String SHARE_TYPE_WEB = "image/*";
    public static final String TYPE = "type";
    public static final String SHARE_MESSAGE = "share_msg";
    public static final String SHARE_IMG = "share_img";
    public static final int LOADING_VIEW_DELAYED = 1000;
    public static final String LOT_URL = "http://3g.iletou.com/";
    public static final String GET_XIAO_MI_LAYOUT = "http://adv.sec.miui.com/info/";//获取小米的布局
    public static final String GET_XIAO_MI_LAYOUT2 = "https://wtradv.market.xiaomi.com/v1/";//获取小米的布局
    public static final String GET_WEATHER = "http://www.sojson.com/open/api/weather/json.shtml/";//获取天气
    public static final String URL_HISTORY_TODAY_JUHE = "http://v.juhe.cn/todayOnhistory/";// 历史上的今天聚合数据接口地址
    public static final String URL_GET_GIRL_IMG = "http://image.baidu.com/search/";//获取美女图片的地址
    public static final String URL_GET_JOKE = "http://xiaobaxiaoba.com/";//获取笑话的接口
    public static final int VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT = 5;//viewPager缓存页数
    public static final int PAGE_SIZE = 20;//数据加载页数
    public static final File directory = FileUtils.getCacheDirectory(MyApplication.getInstance(), "DownLoad");
    public static final String ACCESS_LOCATION_EXTRA_COMMANDS = Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS;//定位的权限
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;//定位的权限
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;//定位的权限
    public static final String JS_NAME = "jsName";//加载的js文件名
    public static final String OPTIONS = "options";//传递的参数
    public static final String SHARE_URL = "shareUrl";//分享的URL。
    public static final int START_ACTIVITY_FINISH = 0x9999;
    public static final int LOCATION_SUCCESS = 0x9998;
    public static final String CAN_GO_BACK = "canGoBack";
    public static final String BAR_SHOW = "barShow";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String IMG_URL = "imgUrl";
    public static final String TABS = "tabs";
    public static final String MSG = "msg";
    public static final String IMAGE_PATH = "imgPath";
    public static final String AD_IMG_URL = "adUrl";
    public static final String AD_SCHEME_URL = "adSchemeUrl";
    public static final String SHARE_DEC = "shareDec";
}
