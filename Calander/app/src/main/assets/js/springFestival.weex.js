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
/******/ 	return __webpack_require__(__webpack_require__.s = 50);
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

/***/ 37:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(75)
)

/* script */
__vue_exports__ = __webpack_require__(69)

/* template */
var __vue_template__ = __webpack_require__(86)
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
__vue_options__.__file = "D:\\workSpace\\rili_weex\\src\\views\\springFestival.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-9d298eec"
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

/***/ 50:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _springFestival = __webpack_require__(37);

var _springFestival2 = _interopRequireDefault(_springFestival);

var _mixins = __webpack_require__(0);

var _mixins2 = _interopRequireDefault(_mixins);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

Vue.mixin(_mixins2.default);

_springFestival2.default.el = '#root';

new Vue(_springFestival2.default);

/***/ }),

/***/ 69:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _methods = __webpack_require__(1);

var _methods2 = _interopRequireDefault(_methods);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

exports.default = {
    name: "spring-festival",
    methods: {
        font: function font(size) {
            return _methods2.default.getFontSize(size);
        }

    },
    props: {
        line_height: {
            default: 28
        },
        contentMargin: {
            default: 7
        },
        title_pingying: {
            default: '            [chūn jié] '
        },
        title_name: {
            default: '      春节 （中国传统节日）'
        },
        title: {
            default: '        春节是指汉字文化圈传统上的农历新年，俗称“年节”，传统名称为新年、大年、新岁，但口头上又称度岁、庆新岁、过年。 中国人过春节已有4000多年的历史。在现代，人们把春节定于农历正月初一，但一般至少要到正月十五（上元节）新年才算结束，在民间，传统意义上的春节是指从腊月的腊祭或腊月二十三或二十四的祭灶，一直到正月十九。\n' + '        在春节期间，中国的汉族和一些少数民族都要举行各种庆祝活动。这些活动均以祭祀祖神、祭奠祖先、除旧布新、迎禧接福、祈求丰年为主要内容，形式丰富多彩，带有浓郁的各民族特色。受到中华文化的影响，属于汉字文化圈的一些国家和民族也有庆祝春节的习俗。人们在春节这一天都尽可能地回到家里和亲人团聚，表达对未来一年的热切期盼和对新一年生活的美好祝福。\n' + '        春节是中华民族最隆重的传统佳节，同时也是中国人情感得以释放、心理诉求得以满足的重要载体，是中华民族一年一度的狂欢节和永远的精神支柱。春节与清明节、端午节、中秋节并称为中国四大传统节日。'
        },
        content_1: {
            default: '        关于春节的起源有说法诸多，其中有几种较具代表性的说法，春节源于腊祭、源于巫术仪式说、源于鬼节说等，其中最被普遍接受的说法是春节由虞舜时期兴起。\n' + '公元前2000多年的一天，舜继天子位，带领着部下人员，祭拜天地。从此，人们就把这一天当作岁首。据说这就是农历新年的由来，后来叫春节。'
        },
        content_2: {
            default: '        春节是除旧布新的日子，春节虽定在农历正月初一，但春节的活动却并不止于正月初一这一天。从腊月二十三（或二十四日）小年节起，人们便开始“忙年”：扫房屋、洗头沐浴、准备年节器具等等，所有这些活动，有一个共同的主题，即“辞旧迎新”。\n' + '        春节也是祭祝祈年的日子，古人谓谷子一熟为一“年”，五谷丰收为“大有年”。西周初年，即已出现了一年一度的庆祝丰收的活动。后来，祭天祈年成了年俗的主要内容之一；而且，诸如灶神、门神、财神、喜神、井神等诸路神明，在春节期间，都备享人间香火。人们借此酬谢诸神过去的关照，并祈愿在新的一年中能得到更多的福佑。\n' + '        春节还是合家团圆、敦亲祀祖的日子。除夕，全家欢聚一堂，吃罢“团年饭”，长辈给孩子们分发“压岁钱”，一家人团坐“守岁”。元日子时交年时刻，鞭炮齐响，辞旧岁、迎新年的活动达于高潮。各家焚香致礼，敬天地、祭列祖，然后依次给尊长拜年，继而同族亲友互致祝贺。\n' + '        春节更是民众娱乐狂欢的节日。元日以后，各种丰富多彩的娱乐活动竞相开展：耍狮子、舞龙灯、扭秧歌、踩高跷、杂耍诸戏等，为新春佳节增添了浓郁的喜庆气氛。\n' + '        因此，集祈年、庆贺、娱乐为一体的盛典春节就成了中华民族最隆重的佳节。而时至今日，除祀神祭祖等活动比以往有所淡化以外，春节的主要习俗，都完好地得以继承与发展。\n' + '扫尘\n' + '        “腊月二十四，掸尘扫房子” ，据《吕氏春秋》记载，中国在尧舜时代就有春节扫尘的风俗。按民间的说法：因“尘”与“陈”谐音，新春扫尘有“除陈布新”的涵义，其用意是要把一切穷运、晦气统统扫出门。每逢春节来临，家家户户都要打扫环境，清洗各种器具，拆洗被褥窗帘，洒扫六闾庭院，掸拂尘垢蛛网，疏浚明渠暗沟。\n' + '        到处洋溢着欢欢喜喜搞卫生、干干净净迎新春的欢乐气氛。\n' + '守岁\n' + '        除夕守岁是最重要的年俗活动之一，守岁之俗由来已久。最早记载见于西晋周处的《风土志》：除夕之夜，各相与赠送，称为“馈岁”；酒食相邀，称为“别岁”；长幼聚饮，祝颂完备，称为“分岁”；大家终夜不眠，以待天明，称曰“守岁”。\n' + '        自汉代以来，新旧年交替的时刻一般为夜半时分。\n' + '拜年\n' + '        现代社会通行的贺年卡在中国古代已经实行。早在宋代，皇亲贵族士大夫的家族与亲族之间已使用专门拜年的贺年片，叫做“名刺”或“名贴”。它是把梅花笺纸裁成约二寸宽、三寸长的卡片，上面写上自己的姓名、地址。各家门上粘一红纸袋，称为“门簿”，其上写着主人姓名，用以接收名刺（名贴）。拜者投名刺（名贴）于门簿，即表示拜年，其意义与现代贺年卡一样。\n' + '贴春联\n' + '        贴春联的习俗，大约始于一千多年前的后蜀时期，是有史为证的。春联也叫门对、春贴、对联、对子、桃符等，它以工整、对偶、简洁、精巧的文字描绘时代背景，抒发美好愿望，是中国特有的文学形式。每逢春节，无论城市还是农村，家家户户都要精选一幅大红春联贴于门上，为节日增加喜庆气氛。这一习俗起于宋代，在明代开始盛行，到了清代，春联的思想性和艺术性都有了很大的提高，梁章矩编写的春联专著《槛联丛话》对楹联的起源及各类作品的特色都作了论述。春联的种类比较多，依其使用场所，可分为门心、框对、横披、春条、斗方等。“门心”贴于门板上端中心部位；“框对”贴于左右两个门框上；“横披”贴于门媚的横木上；“春条”根据不同的内容，贴于相应的地方；“斗斤”也叫“门叶”，为正方菱形，多贴在家俱、影壁中。\n' + '窗花与“福”字\n' + '        在民间人们还喜欢在窗户上贴上各种剪纸——窗花。窗花不仅烘托了喜庆的节日气氛，也集装饰性、欣赏性和实用性于一体。剪纸在中国是一种很普及的民间艺术，千百年来深受人们的喜爱，因它大多是贴在窗户上的，所以也被称其为“窗花”。窗花以其特有的概括和夸张手法将吉事祥物、美好愿望表现得淋漓尽致，将节日装点得红火富丽。在贴春联的同时，一些人家要在屋门上、墙壁上、门楣上贴上大大小小的“福”字。春节贴“福”字，是中国民间由来已久的风俗。“福”字指福气、福运，寄托了人们对幸福生活的向往，对美好未来的祝愿。为了更充分地体现这种向往和祝愿，有的人干脆将“福”字倒过来贴，表示“幸福已到”、“福气已到”。民间还有将“福”字精描细做成各种图案的，图案有寿星、寿桃、鲤鱼跳龙门、五谷丰登、龙凤呈祥等。\n' + '贴年画\n' + '        春节挂贴年画在城乡也很普遍，浓黑重彩的年画给千家万户平添了许多兴旺欢乐的喜庆气氛。年画是中国的一种古老的民间艺术，反映了人民朴素的风俗和信仰，寄托着他们对未来的希望。年画，也和春联一样，起源于“门神”。 随着木板印刷术的兴起，年画的内容已不仅限于门神之类单调的主题，变得丰富多彩，在一些年画作坊中产生了《福禄寿三星图》、《天官赐福》、《五谷丰登》、《六畜兴旺》、《迎春接福》等精典的彩色年画、以满足人们喜庆祈年的美好愿望。 中国出现了年画三个重要产地：苏州桃花坞，天津杨柳青和山东潍坊；形成了中国年画的三大流派，各具特色。\n' + '福禄寿\n' + '        中国收藏最早的年画是南宋《随朝窈窕呈倾国之芳容》的木刻年画，画的是王昭君、赵飞燕、班姬和绿珠四位古代美人。民间流传最广的是一幅《老鼠娶亲》的年画。描绘了老鼠依照人间的风俗迎娶新娘的有趣场面。民国初年，上海郑曼陀将月历和年画二者结合起来。这是年画的一种新形式。这种合二而一的年画，以后发展成挂历，至今风靡全国。\n' + '燃爆竹\n' + '        中国民间有“开门爆竹”一说。即在新的一年到来之际，家家户户开门的第一件事就是燃放爆竹，以哔哔叭叭的爆竹声除旧迎新。爆竹是中国特产，亦称“爆仗”、“炮仗”、“鞭炮”。其起源很早，至今已有两千多年的历史。\n' + '        放爆竹可以创造出喜庆热闹的气氛，是节日的一种娱乐活动，可以给人们带来欢愉和吉利。随着时间的推移，爆竹的应用越来越广泛，品种花色也日见繁多，每逢重大节日及喜事庆典，及婚嫁、建房、开业等，都要燃放爆竹以示庆贺，图个吉利。湖南浏阳，广东佛山和东尧，江西的宜春和萍乡、浙江温州等地区是中国的花炮之乡,生产的爆竹花色多，品质高，不仅畅销全国，而且还远销世界。\n' + '办年货\n' + '        中国的家庭过年前要购买大量的“年货”，春联，福字，新衣服，过年期间的食品（过年市场多不开门）。办年货是中国人过春节的一项重要活动。与过去相比，中国人办年货的方式变得更加现代，不拘泥于传统。\n' + '        山西地方有个特别的年货：刷子和筷子每年必买，谐音‘快发’之意。'
        }
    }

}; //
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

/***/ }),

/***/ 75:
/***/ (function(module, exports) {

module.exports = {}

/***/ }),

/***/ 86:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('scroller', [_c('image', {
    staticStyle: {
      width: "750px",
      height: "1125px"
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/springFestival/img_1.jpg",
      "placeholder": "http://zerosboy.site/Ahuangshang/img/image_icon/default.png"
    }
  }), _c('div', [_c('div', {
    style: {
      marginLeft: _vm.font(_vm.contentMargin),
      marginRight: _vm.font(_vm.contentMargin)
    }
  }, [_c('text', {
    style: {
      fontSize: _vm.font(12),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.title_pingying
    }
  }), _c('text', {
    staticStyle: {
      color: "red",
      fontWeight: "bold"
    },
    style: {
      fontSize: _vm.font(24),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.title_name
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "12px"
    },
    style: {
      fontSize: _vm.font(16),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.title
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(16)
    }
  }, [_vm._v(" 节日起源")]), _c('text', {
    staticStyle: {
      marginTop: "12px"
    },
    style: {
      fontSize: _vm.font(14),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_1
    }
  }), _c('text', {
    staticStyle: {
      marginTop: "15px"
    },
    style: {
      fontSize: _vm.font(16)
    }
  }, [_vm._v(" 风俗习惯")]), _c('text', {
    staticStyle: {
      marginTop: "12px"
    },
    style: {
      fontSize: _vm.font(14),
      lineHeight: _vm.font(_vm.line_height)
    },
    attrs: {
      "value": _vm.content_2
    }
  })])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });