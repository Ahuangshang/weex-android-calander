const modal = weex.requireModule('modal');
module.exports = {

  parseQueryString: function (str) {
    if (str.indexOf('?') === -1 || str.indexOf('=') === -1) return null;
    str = str.split("?")[1];
    let reg = /(([^?&=]+)(?:=([^?&=]*))*)/g;
    // let reg = /\s*([\w\-]+?)\s*=\s*([^;]*?)\s*(?:;|$)\s*/g;
    let result = {};
    let match;
    let key;
    let value;
    while (match = reg.exec(str)) {
      key = match[2];
      if (key === 'hot-reload_controller' || key === '_wx_tpl') continue;
      value = match[3] || '';
      result[key] = decodeURIComponent(value);
    }
    return result;
  },
  toDateString: function (value) {
    let date;
    if (this.isNotNull(value)) {
      date = new Date(value);
    } else {
      date = new Date();
    }
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    let s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return (Y + M + D + h + m + s);
  },
  toTimeSpan: function () {
    //let date = new Date(strtime); //传入一个时间格式，如果不传入就是获取现在的时间了，这样做不兼容火狐。
    // 可以这样做
    let date = new Date(strtime.replace(/-/g, '/'));
    // 有三种方式获取，在后面会讲到三种方式的区别
    // return date.getTime();
    // return date.valueOf();
    return Date.parse(date);

    /*
     三种获取的区别：
     第一、第二种：会精确到毫秒
     第三种：只能精确到秒，毫秒将用0来代替
     比如上面代码输出的结果(一眼就能看出区别)：
     1398250549123
     1398250549123
     1398250549000
     */
  },
  compareDate: function (startDate, endDate) {
    let d1 = new Date(startDate.replace(/-/g, "/"));
    let d2 = new Date(endDate.replace(/-/g, "/"));

    return !(startDate !== "" && endDate !== "" && d1 > d2);
  },
  isNotNull: function (str) {
    return str !== undefined && str !== "" && str != null;
  },
  //获取日期 传1代表当月第一天 传其他代表当前日期
  getDate: function (v) {
    let now = new Date();
    let year = now.getFullYear();       //年
    let month = now.getMonth() + 1;     //月
    let day = now.getDate();            //日

    /*let hh = now.getHours();            //时
     let mm = now.getMinutes();          //分
     let ss = now.getSeconds();*/
    let clock = year + "-";        //加""的作用是转成字符串，不然会以整型计算

    if (month < 10)
      clock += "" + "0";
    clock += month + "-";

    if (day < 10)
      clock += "" + "0";
    clock += v === 1 ? '1' : day;

    /*if(hh < 10)
     clock += ""+"0";
     clock += hh;

     if (mm < 10)
     clock += ""+"0";
     clock += mm;

     if (ss < 10)
     clock += ""+"0";
     clock += ss;*/
    return (clock);
  },

  /**
   * 获取图片的高度
   * @param url
   * @param callback
   * @returns {*}
   */
  checkPicurl: function (url, callback) {
    let img = new Image();
    img.src = url;
    let ratio = 0;
    let clientWidth = 750;
    img.onerror = function () {
      ratio = 0;
      return callback(ratio);

    };
    if (img.complete) {
      ratio = img.width / img.height;
      return callback(ratio === 0 ? 0 : clientWidth / ratio);
    } else {
      img.onload = function () {
        ratio = img.width / img.height;
        img.onload = null;//避免重复加载
        return callback(ratio === 0 ? 0 : clientWidth / ratio);
      }
    }
  },
  /**
   *  适配不同情况下的字体大小
   * @param size dp值的大小
   * @returns {*} 适配后的值的大小
   */
  getFontSize: function (size) {
    if (this.isweb()) {
      let clientWith = document.body.clientWidth;
      if (weex.config.env.osName.toLocaleString() === "android") {
        return 2 * size + 'px';
      } else {
        if (clientWith > 900) {
          return size * weex.config.env.scale + 'px'
        } else if (clientWith > 750) {
          return 2 * size * weex.config.env.scale + 'px'
        } else {
          return 4 * size * weex.config.env.scale + 'px'
        }
      }
    } else {
      let clientWith = weex.config.env.deviceWidth;
      const ratio = 750 / clientWith;
      let fontSize = size * ratio * weex.config.env.scale;
      return fontSize.toFixed(0);
    }
  },
  getMatchSize: function (size) {
    if (this.isweb()) {
      return size * weex.config.env.scale + 'px';
    } else {
      return size;
    }
  },
  delHtmlTag: function (str) {
    return str.replace(/<[^>]+>/g, "").replace(/&quot;/g, "\"").replace(/&lt;/g, '<').replace(/&gt;/g, '>');//去掉所有的html标记
  },
  isweb: function () {
    return weex.config.env.platform.toLocaleLowerCase() === "web";
  },
  is_weixn: function () {
    var ua = navigator.userAgent.toLowerCase();
    return ua.match(/MicroMessenger/i) == "micromessenger";
  },
  registerModules: function () {
    if (this.isweb()) {
      weex.registerModule('event', {
        openWeexView(viewName, viewTitle) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openWeexView(viewName, viewTitle);
          }
        },
        openWeexView(viewName, title, shareUrl) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openWeexView(viewName, title, shareUrl);
          }
        },
        openWebView(webUrl, title) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openWebView(webUrl, title);
          }
        },
        openWebView(webUrl, title, shareUrl) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openWebView(webUrl, title, shareUrl);
          }
        },
        openView(uri) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openView(uri);
          }
        },
        getFilePath(name, type, callback) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            window.getFilePathCallback = callback;
            ltwc.getFilePath(name, type, callback);
          }
        },
        showMessage(msg) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.showMessage(msg);
          }
        },
        getVersion(callback) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            window.getVersionCallback = callback;
            ltwc.getVersion(callback);
          }
        },
        update(url) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.update(url);
          }
        },
        setConfig(tabs, adImgUrl, adSchemeUrl) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.setConfig(tabs, adImgUrl, adSchemeUrl);
          }
        },
        playVideo(url, datas, position) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.playVideo(url, datas, position);
          }
        },
      });
      weex.registerModule('net', {
        requestNetData(methodType, url, api, jsonParams, showLoading, callback) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            window.requestNetDataCallback = callback;
            ltwc.requestNetData(methodType, url, api, jsonParams, showLoading);
          }
        }
      });
    }
  },
};
if (module.exports.isweb()) {
  window.getReturnData = function (type, data) {
    if (type === "getFilePath") {
      window.getFilePathCallback(data);
    } else if (type === "requestNetData") {
      window.requestNetDataCallback(data);
    } else if (type === "getVersion") {
      window.getVersionCallback(data);
    }

  };
}
