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
/******/ 	return __webpack_require__(__webpack_require__.s = 44);
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

/***/ 31:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* script */
__vue_exports__ = __webpack_require__(63)

/* template */
var __vue_template__ = __webpack_require__(92)
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
__vue_options__.__file = "D:\\workSpace\\rili_weex\\src\\views\\guanggao.vue"
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

/***/ 44:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _guanggao = __webpack_require__(31);

var _guanggao2 = _interopRequireDefault(_guanggao);

var _mixins = __webpack_require__(0);

var _mixins2 = _interopRequireDefault(_mixins);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

Vue.mixin(_mixins2.default);

_guanggao2.default.el = '#root';

new Vue(_guanggao2.default);

/***/ }),

/***/ 63:
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

exports.default = {
  props: {
    img_w: {
      default: 718
    },
    img_1_h: {
      default: 479
    },
    img_2_h: {
      default: 431
    },

    img_3_h: {
      default: 596
    },

    img_4_h: {
      default: 300
    },
    img_5_h: {
      default: 362
    },
    img_6_h: {
      default: 250
    },
    contentMargin: {
      default: 16
    },
    title_size: {
      default: 50
    },
    tex_size_1: {
      default: 30
    },
    tex_size_2: {
      default: 35
    },
    tex_size_3: {
      default: 15
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
      default: "视频VIP豪送|庆国庆、迎中秋，万份视频VIP等你来领！"
    },
    content_1: {
      default: "各位老铁好！"

    },
    content_2: {
      default: "在此团圆美满之时，"
    },
    content_3: {
      default: "本帅先祝大家双节快乐！"
    },
    content_4: {
      default: "那么在金秋十月里，"
    },
    content_5: {
      default: "除了赏月、吃月饼，走遍大地神州这些事情外，"
    },
    content_6: {
      default: "还有什么可以做的吗？ "
    },
    content_7: {
      default: "好了，咱们不理老伯爵这个二货了..."
    },
    content_8: {
      default: "其实，双节除了以上可做的事外，"
    },
    content_9: {
      default: "还有..."
    },
    content_10: {
      default: "。"
    },
    content_11: {
      default: "今天要告诉大家的神秘好礼！！！"
    },
    content_12: {
      default: "为了给老铁们一个福利满满的双节，"
    },
    content_13: {
      default: "本帅专门为大家准备了开业大酬宾！"
    },
    content_14: {
      default: "一起庆祝李唐科技开业，领取福利吧~"
    },
    content_15: {
      default: "活动时间"
    },
    content_16: {
      default: "10月1日到10月17日"
    },
    content_17: {
      default: "活动奖励"
    },
    content_18: {
      default: "腾讯视频、爱奇艺、优酷、合一等各大视频网站VIP任你选！"
    },
    content_19: {
      default: "参与方式"
    },
    content_20: {
      default: "微信搜索17620002901，或者扫描下方二维码添加好友并发送你想要的视频网站Vip的名字即可获取。例如发送：腾讯视频。我们会在两个工作日内发放你想要的视频网站的Vip帐号，数量有限，先到先得！"
    },
    content_21: {
      default: "温馨提示：每位用户只能领取一次奖励哦~"
    },
    content_22: {
      default: "特别说明：\n        奖品照片仅供参考，一切以实际发放为准；\n        获得奖品的用户，我们会在两个工作日内联系您的微信，请保持微信正常联系，逾期未回复我们将视为自动放弃；\n       本活动最终解释权归李唐科技所有。"
    }
  },
  created: function created() {}
};

/***/ }),

/***/ 92:
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
      fontSize: _vm.title_size,
      marginTop: _vm.contentMargin,
      marginBottom: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.title
    }
  }), _c('text', {
    style: {
      fontSize: _vm.tex_size_2,
      marginTop: _vm.contentMargin
    }
  }, [_vm._v(" 2017-10-01 李唐科技")]), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_1_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad.jpg",
      "placeholder": ""
    }
  }), _c('div', [_c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_1
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_2
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_3
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_2_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad2.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_4
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
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
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_6
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_3_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad4.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_7
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_8
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_9
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }, [_vm._v("}\n      ")]), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }, [_vm._v("}\n      ")]), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_10
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_11
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
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad5.jpg",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#020402",
      justifyContent: "center",
      textAlign: "center"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
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
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
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
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    },
    attrs: {
      "value": _vm.content_14
    }
  }), _c('image', {
    style: {
      width: _vm.img_w,
      height: _vm.img_5_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad1.jpg",
      "placeholder": ""
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
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
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_16
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_17
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_18
    }
  }), _c('div', {
    staticStyle: {
      justifyContent: "center",
      flexDirection: "row"
    },
    style: {
      width: _vm.img_w
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_6_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad_qq.png",
      "placeholder": ""
    }
  }), _c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_6_h,
      marginLeft: _vm.contentMargin
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad_aqy.png",
      "placeholder": ""
    }
  })]), _c('div', {
    staticStyle: {
      justifyContent: "center",
      flexDirection: "row"
    },
    style: {
      width: _vm.img_w,
      marginTop: _vm.contentMargin
    }
  }, [_c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_6_h
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad_yk.png",
      "placeholder": ""
    }
  }), _c('image', {
    style: {
      width: _vm.img_4_h,
      height: _vm.img_6_h,
      marginLeft: _vm.contentMargin
    },
    attrs: {
      "src": "http://zerosboy.site/Ahuangshang/img/advertisement/ad_hy.png",
      "placeholder": ""
    }
  })]), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_19
    }
  }), _c('text', {
    staticStyle: {
      color: "#f45531"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_20
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
      color: "#1D67F4"
    },
    style: {
      fontSize: _vm.tex_size_1,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.line_height2
    },
    attrs: {
      "value": _vm.content_21
    }
  }), _c('text', {
    staticStyle: {
      color: "#020402"
    },
    style: {
      fontSize: _vm.tex_size_3,
      marginTop: _vm.topMargin,
      margin: _vm.contentMargin,
      lineHeight: _vm.tex_size_2
    },
    attrs: {
      "value": _vm.content_22
    }
  })])])])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });