/**
 * Created by Tw93 on 2016/11/4.
 */

export default {
  channels: '头条&新闻&财经&体育&娱乐&军事&教育&科技&NBA&股票&星座&女性&健康&育儿',
  adImgUrl: 'http://zerosboy.site/Ahuangshang/img/newYear.jpg',//图片尺寸1080*1800
  adImgSchemeUrl: '',
  newVersion: 312280,
  updateUrl: 'http://zerosboy.site/Ahuangshang/apk/latest.apk',
  HostImgUrl: 'http://zerosboy.site/Ahuangshang/img/',
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
    return nhigh + " / " + nlow;
  }
}