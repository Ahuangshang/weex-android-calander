/**
 * Created by Tw93 on 2016/11/4.
 */

export default {
    channels: '头条&新闻&财经&体育&娱乐&军事&教育&科技&NBA&股票&星座&女性&健康&育儿',
    adImgUrl: 'http://imengu.cn/Ahuangshang/img/newYear.jpg',//图片尺寸1080*1800
    adImgSchemeUrl: 'className=cn.ltwc.cft.weex.WeexActivity&ltkj&jsName=springFestival&ltkj&webTitle=春节&ltkj&shareUrl=http://imengu.cn/Ahuangshang/html/springFestival.html',
    newVersion: 313301,
    updateUrl: 'http://imengu.cn/Ahuangshang/html/downLoadApp.html',
    HostImgUrl: 'http://imengu.cn/Ahuangshang/img/',
    defaultHost: 'http://imengu.cn/',
    getContent: function (e) {
        var head = "<head>" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
            "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
            "<style type='text/css'>" +
            "body{color:rgba(28,28,28,0.95);font-size: 16px}" +
            "</style>" +
            "</head>";
        var style = "<style>" +
            "  body{" +
            "    -webkit-user-select: none;" +
            "    -webkit-tap-highlight-color: transparent;" +
            "  }" +
            "</style>";
        var result = "<html>" + head + style + "<body>" + e + "</body></html>";
        result = encodeURI(result);
        return result;
    },
    getWeatherTypeImg(currentType) {
        if (this.contains(currentType, '晴')) {
            return 'qing.jpg';
        } else if (this.contains(currentType, '阴')) {
            return 'yin.jpg';
        } else if (this.contains(currentType, '多云')) {
            return 'duoyun.gif';
        } else if (this.contains(currentType, '小雨') || this.contains(currentType, '中雨')) {
            return 'xiaoyu.gif';
        } else if (this.contains(currentType, '大雨') || this.contains(currentType, '暴雨')) {
            return 'dayu.gif';
        } else if (this.contains(currentType, '小雪') || this.contains(currentType, '中雪')) {
            return 'xiaoxue.gif';
        } else if (this.contains(currentType, '大雪') || this.contains(currentType, '暴雪')) {
            return 'daxue.gif';
        } else if (this.contains(currentType, '雪')) {
            return 'xiaoxue.gif'
        } else if (this.contains(currentType, '雨')) {
            return 'xiaoyu.gif';
        }
    },
    contains: function (str, s) {
        return str.indexOf(s) > -1;
    },
    getWeatherDec: function (high, low) {
        let nhigh = high.replace("高温", "");
        nhigh = nhigh.replace('℃', '');
        let nlow = low.replace('低温', '');
        return nhigh + " ~" + nlow;
    },
    newsTabTitles: [
        {title: '头条',}, {title: '新闻',}, {title: '财经',}, {title: '体育',},
        {title: '娱乐',}, {title: '军事',}, {title: '教育',}, {title: '科技',},
        {title: 'NBA',}, {title: '股票',}, {title: '星座',}, {title: '女性',},
        {title: '健康',}, {title: '育儿',},
    ],
    newsTabStyles: {
        bgColor: '#ffffff',
        titleColor: '#dd000000',
        activeTitleColor: '#31A9A5',
        activeBgColor: '#ffffff',
        isActiveTitleBold: true,
        iconWidth: 70,
        iconHeight: 70,
        width: 160,
        height: 75,
        fontSize: 28,
        hasActiveBottom: true,
        activeBottomColor: '#31A9A5',
        activeBottomHeight: 1,
        activeBottomWidth: 160,
        textPaddingLeft: 10,
        textPaddingRight: 10,
        normalBottomColor: 'rgba(0,0,0,0.4)',
        normalBottomHeight: 1,
        hasRightIcon: true,
        rightOffset: 100
    },
    jokeTabTitles: [
        {title: '脑筋急转弯', netUrl: 'https://api.bmob.cn/1/classes/funny_iq/'},
        {title: '时尚物语', netUrl: 'https://api.bmob.cn/1/classes/funny_ganwu/'},
        {title: '节日祝福', netUrl: 'https://api.bmob.cn/1/classes/funny_zhufu/'},
    ],
    jokeTabStyles: {
        bgColor: '#ffffff',
        titleColor: '#dd000000',
        activeTitleColor: '#31A9A5',
        activeBgColor: '#ffffff',
        isActiveTitleBold: true,
        iconWidth: 70,
        iconHeight: 70,
        width: 250,
        height: 75,
        fontSize: 28,
        hasActiveBottom: true,
        activeBottomColor: '#31A9A5',
        activeBottomHeight: 1,
        activeBottomWidth: 250,
        textPaddingLeft: 10,
        textPaddingRight: 10,
        normalBottomColor: 'rgba(0,0,0,0.4)',
        normalBottomHeight: 1,
        hasRightIcon: true,
        rightOffset: 100
    },
}
