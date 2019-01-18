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
/******/ 	return __webpack_require__(__webpack_require__.s = 43);
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

/***/ 30:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* script */
__vue_exports__ = __webpack_require__(62)

/* template */
var __vue_template__ = __webpack_require__(96)
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
__vue_options__.__file = "D:\\workSpace\\rili_weex\\src\\views\\dongzhi.vue"
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

/***/ 43:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _dongzhi = __webpack_require__(30);

var _dongzhi2 = _interopRequireDefault(_dongzhi);

var _mixins = __webpack_require__(0);

var _mixins2 = _interopRequireDefault(_mixins);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

Vue.mixin(_mixins2.default);

_dongzhi2.default.el = '#root';

new Vue(_dongzhi2.default);

/***/ }),

/***/ 62:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
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
var util = __webpack_require__(1);
exports.default = {
    props: {
        img_w_top: {
            default: 750
        },
        img_w: {
            default: 718
        },
        img_1_h: {
            default: 468.75
        },
        img_2_h: {
            default: 432.56
        },

        img_3_h: {
            default: 538.50
        },

        img_4_h: {
            default: 563.43
        },
        img_5_h: {
            default: 513.02
        },
        contentMargin: {
            default: 7
        },
        tex_size_1: {
            default: 16
        },
        tex_size_2: {
            default: 22
        },
        topMargin: {
            default: 18
        },
        line_height: {
            default: 30
        },
        title: {
            default: "       冬至是农历的重要节气，也是中华民族的传统节日。早在春秋时代，中国就已经用土圭观测出冬至，古人认为自冬至起阳气回升，代表着下一个循环的开始。冬至是重要的养生时期，应注意防寒保暖、保肝护肝。"
        },
        content_1: {
            default: "       冬至这天，太阳运行至黄经270°（冬至点），太阳直射地面的位置到达一年的最南端，太阳几乎直射南回归线（又称为冬至线），阳光对北半球最为倾斜。因此，冬至日是北半球各地一年中白昼最短的一天，并且越往北白昼越短。在北极圈以北，这一天太阳整日都在地平线之下，成为北半球一年中极夜范围最广的一天。对北半球各地而言，冬至也是全年正午太阳高度最低的一天。就北京市区来说，冬至这天白昼仅有9小时20分，而正午太阳高度也仅有26°42'。冬至这天，北半球得到的太阳辐射最少，比南半球少了约50%。冬至节届即一阳生，新岁实始。《载敬堂集》载“夏尽秋分日，春生冬至时”，冬至节，春之先声也。冬至过后，夜空星象则完全换成冬季星空，并且从今天起开始“进九”。而此时的南半球正值酷热的盛夏。\n" + "冬至是24节气中最早被制订的一个，然而多数人并不知道，冬至的起源居然是来自于一次国家层面的都城规划。早在3000多年前，周公始用土圭法测影，在洛邑测得天下之中的位置，定此为土中，这在当时有着政治意义的举动，却成了影响后世几千年的节日之一。\n" + "       周公到洛阳，用土圭法测得洛阳所处的地方即为“天下之中”，然后开始占卜国家社稷的吉地。《尚书·洛诰》记载：周公“朝至于洛师”，对洛阳周边的几个地方做了考察，最后确定涧水东、瀍水西、瀍水东皆“惟洛食”（都是兴建宗庙社稷的好地方）。周公通过“土圭测景”选定洛邑基址的史实，被载入了古代典籍，也被后人奉为封邦建国的成法。\n" + "       “土圭测景”的目的是找出“土中”（中国的中心）。这种方法的要义是“树八尺之表，夏至日，景长尺有五寸；冬至日，景长一丈三尺五寸”（即竖起高为8尺的标杆，在夏至日观测，中午的日影是1.5尺，冬至日中午的日影是13.5尺）， “测土深，正日影，求地中，验四时”。 用这种方法测到的就是“土中”洛阳、“洛邑”的理论位置。\n" + "       依周公测影所定的天下之中，周人详细规划了灭商后的第一座国家都城，《逸周书·作雒》载：“乃作大邑成周于土中，……南系于洛水，北因于邙山，以为天下之大凑。” 。“定天保，依天室”，国家社稷（都城、宗庙）完成之后，周公在成周明堂制礼作乐，详细制订了国家礼仪制度， 据记载，周代以冬十一月为正月，以冬至为岁首过新年，也就是说，周公选取的是经土圭法测得的一年中“日影”最长的一天，为新的一年开始的日子。\n" + "       由周到秦，以冬至日当作岁首一直不变。至汉代依然如此，《汉书》有云：“冬至阳气起，君道长，故贺……”也就是说，人们最初过冬至节是为了庆祝新的一年的到来。古人认为自冬至起，天地阳气开始兴作渐强，代表下一个循环开始，是大吉之日。因此，后来一般春节期间的祭祖、家庭聚餐等习俗，也往往出现在冬至。冬至又被称为“小年”，一是说明年关将近，余日不多；二是表示冬至的重要性。把冬至作为节日来过源于汉代，盛于唐宋，并相沿至今。周历的正月为夏历的十一月，因此，周代的正月等于如今的十一月，所以拜岁和贺冬并没有分别。直到汉武帝采用夏历后，才把正月和冬至分开。因此，也可以说专门过“冬至节”是自汉代以后才有，盛于唐宋，相沿至今。"
        },
        content_2: {
            default: "       现代天文科学测定，冬至这天，太阳运行至黄经270°（冬至点），太阳直射地面的位置到达一年的最南端，太阳几乎直射南回归线（又称为冬至线），阳光对北半球最为倾斜。因此，冬至日是北半球各地一年中白昼最短，黑夜最长的一日，并且越往北白昼越短，黑夜越长。在北极圈以北，这一天太阳整日都在地平线之下，成为北半球一年中极夜范围最广的一天。对北半球各地而言，冬至也是全年正午太阳高度最低的一日。就北京市区来说，冬至这天白昼仅有9小时20分，而正午太阳高度也仅有26°42'。冬至这天，北半球得到的太阳辐射最少，比南半球少了约50%。冬至过后，夜空星象则完全换成冬季星空，并且从今天起开始“进九”。而此时的南半球正值酷热的盛夏。\n" + "       值得注意的是，在冬至前后，地球位于近日点附近，运行的速度稍快，这造成了在一年中太阳在南半球的时间比在北半球约短8天，因此北半球的冬季比夏季要略微短一些。\n" + "       注：地极轴通过地心，连结南、北两极，与地球公转轨道面的夹角为66°34′，和地球自转轨道面——赤道面垂直"
        },
        content_3: {
            default: "        据记载，周秦时代以冬十一月为正月，以冬至为岁首过新年。《汉书》有云：“冬至阳气起，君道长，故贺……”也就是说，人们开始过冬至节是为了庆祝新的一年的到来。古人认为自冬至开始，天地阳气开始兴作渐强，代表下一个循环开始，大吉之日。所以，后来一般春节期间的祭祖、家庭聚餐等习俗，往往选在在冬至。冬至又被称做“小年”，一是说明年关将近，余日不多；二是表示冬至的重要性。\n" + "       把冬至作为节日来过源于汉代，盛于唐宋，相沿至今。周历正月为夏历的十一月，因此，周代正月等于如今公历的十一月，所以拜岁和贺冬并没有分别。直到汉朝汉武帝采用夏历后，把正月和冬至分开。也可以说单纯的过“冬至节”是自汉代以后才有，盛于唐宋，相沿至今。\n" + "       汉代以冬至为“冬节”，官府要举行祝贺仪式称为“贺冬”，官方例行放假，官场流行互贺的“拜冬”礼俗。《后汉书》中有这样的记载：“冬至前后，君子安身静体，百官绝事，不听政，择吉辰而后省事。”所以这天朝廷上下要放假休息，军队待命，边塞闭关，商旅停业，亲朋各以美食相赠，相互拜访，欢乐地过一个“安身静体”的节日。魏晋六朝时，冬至称为“亚岁”，民众要向父母长辈拜节；宋朝以后，冬至逐渐成为祭祀祖先和神灵的节庆活动。\n" + "       唐、宋时期，冬至是祭天祀祖的日子，皇帝在这天要到郊外举行祭天大典，百姓在这一天要向父母尊长祭拜。明、清两代，皇帝均有祭天大典，谓之“冬至郊天”。宫内有百官向皇帝呈递贺表的仪式，而且还要互相投刺祝贺，就像元旦一样。 而今只有潮汕、浙江部分地区仍延续祭祖旧习。"
        },
        content_4: {
            default: "        冬至节亦称冬节、交冬。它既是二十四节气之一，是中国的一个传统节日，曾有“冬至大如年”的说法，宫廷和民间历来十分重视，从周代起就有祭祀活动。\n" + "       《周礼春官·神仕》：“以冬日至，致天神人鬼。”目的在于祈求与消除国中的疫疾，减少荒年与人民的饥饿与死亡。《史记·孝武本纪》：“其后二岁，十一月甲子朔旦冬至，推历者以本统。天子亲至泰山，以十一月甲子朔旦冬至日祠上帝明堂，每修封禅。”\n" + "       《后汉书礼仪》：“冬至前后，君子安身静体，百官绝事。”还要挑选“能之士”，鼓瑟吹笙，奏“黄钟之律”，以示庆贺。\n" + "       唐宋时，以冬至和岁首并重。南宋孟元老《东京梦华录》：“十一月冬至。京师最重此节，虽至贫者，一年之间，积累假借，至此日更易新衣，备办饮食，享祀先祖。官放关扑，庆祝往来，一如年节。”\n" + "       《清嘉录》则直言：“冬至大如年”，汉人自古冬至需拜天祭祖，国内部分地区一直延续着此习俗。"
        },

        content_5: {
            default: "        冬至是养生的大好时机，主要是因为“气始于冬至”。因为从冬季开始，生命活动开始由衰转盛，由静转动。此时科学养生有助于保证旺盛的精力而防早衰，达到延年益寿的目的。冬至时节饮食宜多样，谷、果、肉、蔬合理搭配，适当选用高钙食品。\n" + "       各地在冬至时有不同的风俗，中国北方多数地方有冬至吃饺子的习俗。冬至经过数千年发展，形成了独特的节令食文化。吃饺子成为多数中国人冬至的风俗。当然也有例外如在山东省滕州市流行冬至当天喝羊肉汤的习俗，寓意驱除寒冷之意。\n" + "       古人喜贺冬至，今人虽多不以为节，但冬节再怎么说也是“年时八节”之一，吃货们还是不会放过这有着各种冬至特色美食的节日的：如北方水饺、潮汕汤圆、东南麻糍、台州擂圆、合肥南瓜饼、宁波番薯汤果、滕州羊肉汤、江南米饭、苏州酿酒等。\n" + "北方普遍吃水饺\n" + "       每年农历冬至这天，不论贫富，饺子是必不可少的节日饭。谚云：“十月一，冬至到，家家户户吃水饺。”这种习俗，是因纪念“医圣”张仲景冬至舍药留下的。\n" + "合肥南瓜饼\n" + "       冬至过了眼看年，合肥人到了冬至都要吃南瓜饼，大街小巷弥漫着南瓜饼的香味，并且还有一句谚语叫做“吃了冬至面，一天长一线”，就是说过了冬至，就会夜短日长了。\n" + "1.准备好所有食材。把糯米粉和粘米粉混合拌匀。\n" + "2.把南瓜削皮去籽切成薄片放在碗中。\n" + "3.盖上盖子，把南瓜碗放入微波炉，分次转8分钟左右，直至南瓜软烂。\n" + "4.南瓜碗从微波炉中拿出，趁热加入白砂糖，搅拌均匀。\n" + "5.用汤匙把南瓜压成南瓜泥。\n" + "6.把南瓜泥倒入糯米和粘米粉盆中。\n" + "7.用筷子把南瓜和粉搅拌成雪花状，如太干可以稍微加入一点牛奶或者清水。\n" + "8.用手把面糊揉成光滑的面团，然后搓成长条，分成大小相当的坯子。\n" + "9.把坯子放在手中压扁。\n" + "10.放入适量的红豆沙。\n" + "11.把放有红豆沙的面团收口，轻轻地在砧板上按成饼状。\n" + "12.把南瓜饼两面沾上面包糠。\n" + "13.锅中倒入适量的植物油，放入南瓜饼，用小火煎至两面金黄色即可。\n" + "       出锅后的南瓜饼放在厨房餐巾纸上吸走多余的油即可装盘食用。"
        }
    },
    methods: {
        font: function font(size) {
            return util.getFontSize(size);
        }
    }
};

/***/ }),

/***/ 96:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('scroller', [_c('div', [_c('image', {
    style: {
      width: _vm.img_w_top,
      height: _vm.img_1_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_1.jpg",
      "placeholder": ""
    }
  }), _c('div', {
    style: {
      marginLeft: _vm.font(_vm.contentMargin),
      marginRight: _vm.font(_vm.contentMargin)
    }
  }, [_c('text', {
    staticStyle: {
      color: "#334f16"
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
    style: {
      fontSize: _vm.font(_vm.tex_size_2),
      marginTop: _vm.font(_vm.contentMargin)
    }
  }, [_vm._v(" 起源")]), _c('text', {
    staticStyle: {
      color: "#334f16"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_1
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_3_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_3.jpg"
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2)
    }
  }, [_vm._v(" 天文意义")]), _c('text', {
    staticStyle: {
      color: "#334f16"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_2
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_2_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_2.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2)
    }
  }, [_vm._v(" 历史渊源")]), _c('text', {
    staticStyle: {
      color: "#334f16"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_3
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_4_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_4.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2)
    }
  }, [_vm._v(" 民俗活动")]), _c('text', {
    staticStyle: {
      color: "#334f16"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_4
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_5_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_5.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_2)
    }
  }, [_vm._v("传统饮食")]), _c('text', {
    staticStyle: {
      color: "#334f16"
    },
    style: {
      fontSize: _vm.font(_vm.tex_size_1),
      marginTop: _vm.font(_vm.topMargin),
      margin: _vm.font(_vm.contentMargin),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_5
    }
  })])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });