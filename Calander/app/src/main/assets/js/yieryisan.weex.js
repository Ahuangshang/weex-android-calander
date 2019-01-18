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
/******/ 	return __webpack_require__(__webpack_require__.s = 53);
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

/***/ 40:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* script */
__vue_exports__ = __webpack_require__(72)

/* template */
var __vue_template__ = __webpack_require__(84)
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
__vue_options__.__file = "D:\\workSpace\\rili_weex\\src\\views\\yieryisan.vue"
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

/***/ 53:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _yieryisan = __webpack_require__(40);

var _yieryisan2 = _interopRequireDefault(_yieryisan);

var _mixins = __webpack_require__(0);

var _mixins2 = _interopRequireDefault(_mixins);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

Vue.mixin(_mixins2.default);

_yieryisan2.default.el = '#root';

new Vue(_yieryisan2.default);

/***/ }),

/***/ 72:
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

//  var buiweex = require("bui-weex");
var modal = weex.requireModule('modal');
exports.default = {
  props: {
    img_w: {
      default: 750
    },
    img_1_h: {
      default: 469
    },
    img_2_h: {
      default: 520
    },

    img_3_h: {
      default: 489
    },

    img_4_h: {
      default: 490
    },
    img_5_h: {
      default: 370
    },
    contentMargin: {
      default: 7
    },
    tex_size_1: {
      default: 22
    },
    tex_size_2: {
      default: 16
    },
    tex_size_3: {
      default: 18
    },
    topMargin: {
      default: 18
    },
    line_height: {
      default: 30
    },
    isweb: {
      default: false
    },
    url: {
      default: "http://news.jstv.com/wap/tvlive/20171212/1513059469134.shtml"
    },
    title: {
      default: "南京大屠杀死难者国家公祭仪式于12月13日上午10时举行"
    },
    content_1: {
      default: "        今年是南京大屠杀惨案发生80周年。12月13日上午10时，中央按照逢10周年规格，将在侵华日军南京大屠杀遇难同胞纪念馆集会广场举行南京大屠杀死难者国家公祭仪式，届时周边道路将实施交通管制。\n" + "\n" + "　　12月13日早晨7点，在侵华日军南京大屠杀遇难同胞纪念馆的集会广场举行升国旗和降半旗仪式。\n" + "\n" + "　　上午10点，在南京17处南京大屠杀遇难同胞丛葬地、12个社区和6家反映抗战主题的爱国主义教育基地，与国家公祭仪式同步举行悼念南京大屠杀死难者活动。\n" + "\n" + "　　公祭日当天还将开展多项悼念活动，上午10点，由中国博物馆协会纪念馆专业委员会组织中国人民抗日战争胜利纪念馆、沈阳“九一八”历史博物馆、上海淞沪抗战纪念馆等国内20家反映抗战主题的纪念馆同步举行悼念活动。\n" + "\n" + "　　12月13日国家公祭日早上7点到下午1点期间，侵华日军南京大屠杀遇难同胞纪念馆周边道路将实施交通管制，届时在相关路段上运行的21条公交线路将采取临时调整措施，地铁2号线“云锦路”站也将临时封闭，市交通局副局长郑春发提醒：相关路段公交线路在管制解除后，公交线路恢复运营路段，特别提醒地铁2号线当天上午下午1点之前，站临时关闭，跟以前不一样，上午云锦路站整个上午是关闭的，提醒广大市民提前做好出行计划和线路安排，乘坐公共交通出行的要提早出门。\n" + "\n" + "　　今年7月到国家公祭日期间，南京组织开展了以“勿忘国耻、圆梦中华”为主题的4大类28项主题教育活动，其中，共六场的“抗战家书”征集暨诵读活动，最后一场定于12月9号下午，在市档案馆举行；12月，在侵华日军南京大屠杀遇难同胞纪念馆遇难者名单墙前，举行为期一个月的南京大屠杀死难者遗属家庭祭告仪式，同时开展家祭微传播活动。此外，学术研讨活动、相关出版物首发式、系列文化活动于近期陆续举办。"
    }
  },
  created: function created() {
    if (_methods2.default.isweb()) {
      window.temp_this = this;
      _methods2.default.registerModules();
      this.isweb = true;
    }
  },
  methods: {
    font: function font(size) {
      return _methods2.default.getFontSize(size);
    },
    jump: function jump(e) {
      weex.requireModule('event').openView('className=cn.ltwc.cft.activity.MyX5WebView&ltkj&webTitle=国家公祭日&ltkj&webUrl=' + this.url + '&ltkj&shareUrl=' + this.url + '&ltkj&barShow=false');
    }
  }
};

/***/ }),

/***/ 84:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    style: {
      marginLeft: _vm.font(_vm.contentMargin),
      marginRight: _vm.font(_vm.contentMargin)
    }
  }, [_c('text', {
    staticStyle: {
      color: "#000000",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.title
    }
  }), _c('text', {
    staticStyle: {
      color: "#1d1d1d"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2),
      marginTop: _vm.font(_vm.contentMargin)
    },
    attrs: {
      "value": " 来源：江苏新闻广播"
    }
  })]), _c('scroller', {
    attrs: {
      "showScrollbar": "false"
    }
  }, [_c('div', {
    style: {
      marginLeft: _vm.font(_vm.contentMargin),
      marginRight: _vm.font(_vm.contentMargin)
    }
  }, [_c('text', {
    staticStyle: {
      color: "#161616"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_3),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_1
    }
  }), (_vm.isweb) ? _c('a', {
    attrs: {
      "href": "http://news.jstv.com/wap/tvlive/20171212/1513059469134.shtml"
    }
  }, [_c('text', {
    staticStyle: {
      color: "#255fff",
      paddingTop: "20px",
      paddingBottom: "20px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2),
      marginTop: _vm.font(_vm.contentMargin),
      marginBottom: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin)
    },
    attrs: {
      "value": "点击查看纪念活动视频 >>"
    }
  })]) : _c('text', {
    staticStyle: {
      color: "#255fff",
      paddingTop: "20px",
      paddingBottom: "20px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2),
      marginTop: _vm.font(_vm.contentMargin),
      marginBottom: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin)
    },
    attrs: {
      "value": "点击查看纪念活动视频 >>"
    },
    on: {
      "click": _vm.jump
    }
  })])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });