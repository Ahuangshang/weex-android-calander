<template>
  <div :class="[rootclass()]">
    <wxc-tab-page ref="wxc-tab-page"
                  :tab-titles="tabTitles"
                  :tab-styles="tabStyles"
                  title-type="text"
                  @wxcTabPageCurrentTabSelected="wxcTabPageCurrentTabSelected">
      <div v-for="(v,index) in tabTitles" :key="index">
        <div class="empty_view" v-if="list[index].length===0">
          <wxc-result :type="type"
                      padding-top="232"
                      @wxcResultButtonClicked="resultButtonClick(index)"
                      :show="show"/>
        </div>
        <pullrefreshListview class="slider-item" @getData="getData" @pageSize=pageSize
                             :list="list[index]"
                             v-else>
          <div slot-scope="props" slot="myslot" class="cell">
            <wxc-pan-item @wxcPanItemPan="wxcPanItemPan">
              <div style="flex-direction: row;height: 160px" class="item" @click="itemClick(props.item)"
                   v-if="props.item.pic.length>0">
                <text style="flex: 1;" class="title"
                      :style={fontSize:font(18)} :value="dealMsg(props.item.title)"></text>
                <image style="width: 270px;height: 140px;margin:10px;" resize="cover"
                       :src="props.item.pic"
                       :placeholder="loadImg"></image>
              </div>
              <div class="item" @click="itemClick(props.item)" v-else>
                <text style="flex: 1" class="title" :style={fontSize:font(18)}
                      :value="dealMsg(props.item.title)"></text>
              </div>
            </wxc-pan-item>
          </div>

        </pullrefreshListview>
      </div>
    </wxc-tab-page>
  </div>
</template>
<script>
  import {WxcTabPage, WxcResult, WxcPanItem, BindEnv} from 'weex-ui';
  import mtd from '../js/methods.js'
  import Config from '../js/config.js'

  const modal = weex.requireModule('modal');

  // //在Web环境下供原生调用的方法
  // if (mtd.isweb()) {
  //   window.noticeReceiver = function (name, data) {
  //     //window.temp_this.distributeData(name, {'jsonData': JSON.stringify(data)});
  //   };
  // }
  export default {
    components: {
      WxcTabPage, WxcResult, WxcPanItem,
      pullrefreshListview: require('./customview/pullrefresh-listview.vue')
    },
    data: () => ({
      tabTitles: Config.newsTabTitles,
      tabStyles: Config.newsTabStyles,
    }),
    props: {
      show: {
        default:
          false
      },
      type: {
        default:
          'errorPage'
      },
      // customSet:{
      //       errorPage: {
      //         button: '立即反馈',
      //         content: '亲，出错了',
      //         pic: 'https://gw.alicdn.com/tfs/TB1lgzNfBHH8KJjy0FbXXcqlpXa-320-320.png'
      //       }
      // },
      pageSize: {
        default:
          10
      },
      list: {
        default: function () {
          function getList() {
            var temp = [[]];
            for (let i = 0; i < Config.newsTabTitles.length; i++) {
              temp[i] = [];
            }
            return temp;
          }

          return getList();
        }
      },
      params: {
        default: function () {
          return {}
        }
      },
      loadImg: {
        default: null
      },
      channel: {
        default: ''
      }
    },
    created: function () {
      if (mtd.isweb()) {
        window.temp_this = this;
        mtd.registerModules();
      }
      weex.requireModule('event').setConfig(Config.adImgUrl, Config.adImgSchemeUrl);
      weex.requireModule('event').getFilePath('load_failed', 'png', (url) => {
        this.loadImg = url;
      });
    },
    methods: {
      rootclass: function () {
        return mtd.isweb() ? "rootWeb" : "root";
      },
      font: function (size) {
        return mtd.getFontSize(size)
      },
      getData: function (iaLoadMore, showLoading, length, callback) {
        var that = this;
        if (mtd.isweb()) {
          this.pageSize = 20;
        }
        this.params = {
          channel: this.channel,
          num: this.pageSize,
          start: length * this.pageSize,
          appkey: '66d9a1e50f17e306c18fa3c3cfa01228',
        };
        weex.requireModule('net').requestNetData('get', 'https://way.jd.com/jisuapi/get/', '', JSON.stringify(this.params), showLoading, function (ret) {
          that.dealData(ret, callback);
        });
      },
      dealData(ret, callback) {
        if (ret != null) {
          var result = JSON.parse(ret).result;
          if (result != null) {
            var result2 = result.result;
            if (result2 != null) {
              var list = result2.list;
              callback(list);
            } else {
              callback(null);
            }
          } else {
            callback(null);
          }
        } else {
          callback(null);
        }
      },
      resultButtonClick(e) {
        this.getDataAgain(e);
      },
      getDataAgain: function (index) {
        var that = this;
        if (that.list[index] === 'undefined' || that.list[index].length === 0) {
          this.getData(false, true, 0, function (res) {
            if (mtd.isNotNull(res)) {
              that.show = false;
              that.list[index].splice(0, that.list.length[index]);
              res.map((item => {
                that.list[index].push(item);
              }));
            } else {
              that.show = true;
            }

          });
        }
      },
      wxcTabPageCurrentTabSelected(e) {
        const self = this;
        const index = e.page;
        this.channel = this.tabTitles[index].title;
        this.getDataAgain(index);
      },
      itemClick: function (e) {
        weex.requireModule('event')
          .openView('className=cn.ltwc.cft.activity.NewsDetailActivity&ltkj&title=资讯详情&ltkj&content='
            + Config.getContent(e) + '&ltkj&webUrl=' + e.url + '&ltkj&imgUrl=' + e.pic + '&ltkj&shareUrl=' + e.url + '&ltkj&shareDec=' + e.title);
      },
      //该方法压根没有触发，不知道为什么
      wxcPanItemPan: function (e) {
        if (BindEnv.supportsEBForAndroid()) {
          this.$refs['wxc-tab-page'].bindExp(e.element);
        }
      },
      dealMsg: function (str) {
        return mtd.delHtmlTag(str);
      }
    },
    mounted: function () {
      this.getDataAgain(0);
    }
  }
</script>
<style scoped="">
  .slider-item {
    width: 750px;
    justify-content: center;
    align-items: center;
  }

  .root {

  }

  .rootWeb {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0
  }

  .title {
    padding-top: 40px;
    padding-bottom: 40px;
    padding-left: 15px;
    padding-right: 15px;
    lines: 2;
    max-lines: 2;
    text-overflow: ellipsis;
    justify-content: center;
    justify-items: center;
    color: rgb(11, 11, 11);
  }

  .cell {
    width: 750px;
    border-bottom-width: 1px;
    border-top-width: 0px;
    border-color: rgb(229, 229, 229);
    background-color: rgb(255, 255, 255);
    justify-items: center;
  }

  .item {
    background-color: rgb(255, 255, 255);
  }

  .item:active {
    background-color: rgb(240, 240, 240);
  }

  .empty_view {
    width: 750px;
    height: 1300px;
  }
</style>
