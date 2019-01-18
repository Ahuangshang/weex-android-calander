// { "framework": "Vue" }

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// identity function for calling harmony imports with the correct context
/******/ 	__webpack_require__.i = function(value) { return value; };
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 49);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
var navigator = weex.requireModule('navigator');
var mixins = {
    data: function data() {
        return {};
    },
    methods: {}
};

exports.default = mixins;

/***/ }),

/***/ 1:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var modal = weex.requireModule('modal');
module.exports = {

  parseQueryString: function parseQueryString(str) {
    if (str.indexOf('?') === -1 || str.indexOf('=') === -1) return null;
    str = str.split("?")[1];
    var reg = /(([^?&=]+)(?:=([^?&=]*))*)/g;
    // let reg = /\s*([\w\-]+?)\s*=\s*([^;]*?)\s*(?:;|$)\s*/g;
    var result = {};
    var match = void 0;
    var key = void 0;
    var value = void 0;
    while (match = reg.exec(str)) {
      key = match[2];
      if (key === 'hot-reload_controller' || key === '_wx_tpl') continue;
      value = match[3] || '';
      result[key] = decodeURIComponent(value);
    }
    return result;
  },
  toDateString: function toDateString(value) {
    var date = new Date(value);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
    return Y + M + D + h + m + s;
  },
  toTimeSpan: function toTimeSpan() {
    //let date = new Date(strtime); //传入一个时间格式，如果不传入就是获取现在的时间了，这样做不兼容火狐。
    // 可以这样做
    var date = new Date(strtime.replace(/-/g, '/'));
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
  compareDate: function compareDate(startDate, endDate) {
    var d1 = new Date(startDate.replace(/-/g, "/"));
    var d2 = new Date(endDate.replace(/-/g, "/"));

    return !(startDate !== "" && endDate !== "" && d1 > d2);
  },
  isNotNull: function isNotNull(str) {
    return str !== undefined && str !== "" && str != null;
  },
  //获取日期 传1代表当月第一天 传其他代表当前日期
  getDate: function getDate(v) {
    var now = new Date();
    var year = now.getFullYear(); //年
    var month = now.getMonth() + 1; //月
    var day = now.getDate(); //日

    /*let hh = now.getHours();            //时
     let mm = now.getMinutes();          //分
     let ss = now.getSeconds();*/
    var clock = year + "-"; //加""的作用是转成字符串，不然会以整型计算

    if (month < 10) clock += "" + "0";
    clock += month + "-";

    if (day < 10) clock += "" + "0";
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
    return clock;
  },

  /**
   * 获取图片的高度
   * @param url
   * @param callback
   * @returns {*}
   */
  checkPicurl: function checkPicurl(url, callback) {
    var img = new Image();
    img.src = url;
    var ratio = 0;
    var clientWidth = 750;
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
        img.onload = null; //避免重复加载
        return callback(ratio === 0 ? 0 : clientWidth / ratio);
      };
    }
  },
  /**
   *  适配不同情况下的字体大小
   * @param size dp值的大小
   * @returns {*} 适配后的值的大小
   */
  getFontSize: function getFontSize(size) {
    if (this.isweb()) {
      var clientWith = document.body.clientWidth;
      if (weex.config.env.osName.toLocaleString() === "android") {
        return 2 * size + 'px';
      } else {
        if (clientWith > 750) {
          return 2 * size * weex.config.env.scale + 'px';
        } else {
          return 4 * size * weex.config.env.scale + 'px';
        }
      }
    } else {
      var _clientWith = weex.config.env.deviceWidth;
      var ratio = 750 / _clientWith;
      var fontSize = size * ratio * weex.config.env.scale;
      return fontSize.toFixed(0);
    }
  },
  getMatchSize: function getMatchSize(size) {
    if (this.isweb()) {
      return size * weex.config.env.scale + 'px';
    } else {
      return size;
    }
  },
  isweb: function isweb() {
    return weex.config.env.platform.toLocaleLowerCase() === "web";
  },
  registerModules: function registerModules() {
    if (this.isweb()) {
      var _weex$registerModule;

      weex.registerModule('event', (_weex$registerModule = {
        openWeexView: function openWeexView(viewName, viewTitle) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            ltwc.openWeexView(viewName, viewTitle);
          }
        }
      }, _defineProperty(_weex$registerModule, 'openWeexView', function openWeexView(viewName, title, shareUrl) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.openWeexView(viewName, title, shareUrl);
        }
      }), _defineProperty(_weex$registerModule, 'openWebView', function openWebView(webUrl, title) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.openWebView(webUrl, title);
        }
      }), _defineProperty(_weex$registerModule, 'openWebView', function openWebView(webUrl, title, shareUrl) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.openWebView(webUrl, title, shareUrl);
        }
      }), _defineProperty(_weex$registerModule, 'openView', function openView(uri) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.openView(uri);
        }
      }), _defineProperty(_weex$registerModule, 'getFilePath', function getFilePath(name, type, callback) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          window.getFilePathCallback = callback;
          ltwc.getFilePath(name, type, callback);
        }
      }), _defineProperty(_weex$registerModule, 'showMessage', function showMessage(msg) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.showMessage(msg);
        }
      }), _defineProperty(_weex$registerModule, 'getVersion', function getVersion(callback) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          window.getVersionCallback = callback;
          ltwc.getVersion(callback);
        }
      }), _defineProperty(_weex$registerModule, 'update', function update(url) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.update(url);
        }
      }), _defineProperty(_weex$registerModule, 'setConfig', function setConfig(tabs, adImgUrl, adSchemeUrl) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.setConfig(tabs, adImgUrl, adSchemeUrl);
        }
      }), _defineProperty(_weex$registerModule, 'playVideo', function playVideo(url) {
        if (weex.config.env.osName.toLowerCase() === "android") {
          ltwc.playVideo(url);
        }
      }), _weex$registerModule));
      weex.registerModule('net', {
        requestNetData: function requestNetData(methodType, url, api, jsonParams, showLoading, callback) {
          if (weex.config.env.osName.toLowerCase() === "android") {
            window.requestNetDataCallback = callback;
            ltwc.requestNetData(methodType, url, api, jsonParams, showLoading);
          }
        }
      });
    }
  }
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

/***/ }),

/***/ 36:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* script */
__vue_exports__ = __webpack_require__(68)

/* template */
var __vue_template__ = __webpack_require__(88)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "D:\\workSpace\\rili_weex\\src\\views\\shuangshiyi.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__


/***/ }),

/***/ 49:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _shuangshiyi = __webpack_require__(36);

var _shuangshiyi2 = _interopRequireDefault(_shuangshiyi);

var _mixins = __webpack_require__(0);

var _mixins2 = _interopRequireDefault(_mixins);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

Vue.mixin(_mixins2.default);

_shuangshiyi2.default.el = '#root';

new Vue(_shuangshiyi2.default);

/***/ }),

/***/ 68:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
  value: true
});

var _methods = __webpack_require__(1);

var _methods2 = _interopRequireDefault(_methods);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//  var buiweex = require("bui-weex");
var modal = weex.requireModule('modal');
exports.default = {
  props: {
    img_w: {
      default: 718
    },
    img_1_h: {
      default: 505
    },
    img_2_h: {
      default: 285
    },

    img_3_h: {
      default: 460
    },

    img_4_h: {
      default: 300
    },
    img_5_h: {
      default: 325
    },
    img_6_h: {
      default: 250
    },
    contentMargin: {
      default: 16
    },
    title_size: {
      default: 22
    },
    tex_size_1: {
      default: 16
    },
    tex_size_2: {
      default: 18
    },
    tex_size_3: {
      default: 10
    },
    topMargin: {
      default: 40
    },
    line_height: {
      default: 90
    },
    line_height2: {
      default: 55
    },
    title: {
      default: "双十一红包大派送|更多省钱秘籍等你来取！"
    },
    content_1: {
      default: "不知道从什么时候开始，双11变成了国际爱狗日（光棍节），也不知道从什么时候开始，双11变成了全民网购日（剁手节）。总之，平平淡淡的一个星期六，它变得不一样了。"

    },
    content_2: {
      default: "然而剁手的时候是很开心，被衣服化妆品进口小零食奥利奥曲奇奶茶冲昏了头脑，但是等回过神的时候，才发现钱包已经瘪了一半55555~幸好，离发工资的日子也不是很久，也就那么30天吧。"
    },
    content_3: {
      default: "做牛做马一整年，一夜回到解放前。相信很多妹子都跟堡堡一样，都是控制不住寄几的剁手党。"
    },
    content_4: {
      default: "堡堡是个实在的堡堡，今天就来教大家怎么在这个特殊的日子里省钱吧！"
    },
    content_5: {
      default: "双十一红包来啦！！！"
    },
    content_6: {
      default: "复制下面的红包口令打开手机淘宝，就有2.5亿红包等你来抢！(如果打开手机淘宝没有出现任何界面，请将复制的此口令发送至我的微信17620002901即可领取，活动真实可靠！或者选择使用UC浏览器打开本文，复制口令，打开手机淘宝即可领取！)"
    },
    content_7: {
      default: "￥1atA05FIXWk￥"
    },
    content_8: {
      default: "红包最高金额1111元"
    },
    content_9: {
      default: "双十一当天可以直接抵用"
    },
    content_10: {
      default: "每人每天可抽3次"
    },
    content_11: {
      default: "即日起到11月11日，记得每天到我微信复制淘口令抽取双十一红包！"
    },
    content_12: {
      default: "把这篇文章转发出去，可以提高中奖率。"
    },
    content_13: {
      default: "据说，隔壁老王去年转发后"
    },
    content_14: {
      default: "就抽中了1111大红包呢！"
    },
    content_15: {
      default: "没有抽到大红包的童鞋们不要气馁，微信搜索17620002901，或者扫描下方二维码添加好友并发送1111，堡堡每天教大家如何获得更多的双十一红包！"
    },
    content_16: {
      default: "所以，对我们来说，学会怎么剁手更省钱，真滴很重要！"
    },
    content_17: {
      default: ""
    },
    content_18: {
      default: ""
    },
    content_19: {
      default: ""
    },
    content_20: {
      default: ""
    },
    content_21: {
      default: ""
    },
    content_22: {
      default: "特别说明：\n        我们是为了给大家提供更多的获取双十一红包的机会，没有抽中大红包的童鞋，只能说亲们今年运气不好了；\n        抽中红包的童鞋们转发此文，并截图给堡堡，将会有神秘大礼等着你（神秘大礼将在双十一晚会中直播揭晓）；\n       感谢本活动合作伙伴的大力支持，活动最终解释权归李唐科技所有。"
    }
  },
  created: function created() {},
  methods: {
    font: function font(size) {
      return _methods2.default.getFontSize(size);
    }
  }
};

/***/ }),

/***/ 88:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('scroller', [_c('div', {
    style: {
      marginLeft: _vm.contentMargin,
      marginRight: _vm.contentMargin
    }
  }, [_c('text', {
    staticStyle: {
      color: "#000000",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.title_size),
      marginTop: _vm.contentMargin,
      marginBottom: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.title
    }
  }), _c('text', {
    style: {
      fontSize: _vm.font(_vm.tex_size_2),
      marginTop: _vm.contentMargin
    }
  }, [_vm._v(" 2017-10-23 李唐科技")]), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_1_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_1.jpg",
      "placeholder": ""
    }
  }), _c('div', [_c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_1
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_2
    }
  }), _c('div', {
    staticStyle: {
      alignItems: "center"
    },
    style: {
      width: _vm.img_w
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_2_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_2.jpg",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_3
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.font(_vm.title_size),
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_16
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_4
    }
  }), _c('div', {
    staticStyle: {
      alignItems: "center"
    },
    style: {
      width: _vm.img_w
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_3_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_3.jpg",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_5
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_6
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.title_size),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_7
    }
  }), _c('div', {
    staticStyle: {
      alignItems: "center"
    },
    style: {
      width: _vm.img_w
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_5_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_4.jpg",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_8
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_9
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_10
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_11
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_12
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_13
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_14
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.topMargin,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_15
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    }
  }), _c('div', {
    staticStyle: {
      alignItems: "center"
    },
    style: {
      width: _vm.img_w
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_4_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad3.jpg",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_3),
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_22
    }
  })])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });