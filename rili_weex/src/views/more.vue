<template>
  <div style="background-color: rgba(244,244,244,0.96);position: absolute;top: 0;bottom: 0;left: 0;right: 0">
    <scroller style="background-color: rgba(244,244,244,0.96)">
      <div style="align-items: center;flex-direction: column;margin-top: 70px">
        <icon-img imgUrl="icon.png" imgw="100"></icon-img>
        <text style="margin-top: 10px;color: #979797" :value="versionInfo" :style="{fontSize:font(20)}"></text>
      </div>
      <item top_border="true" imgUrl="message.png" itemName="消息中心" style="margin-top: 60px" show_arrow="true"
            @onClick="jump(1)"></item>
      <item imgUrl="zhainan.png" itemName="宅男天堂" show_arrow="true" @onClick="jump(2)"></item>
      <item imgUrl="todayonHistory.png" itemName="历史上的今天" show_arrow="true" @onClick="jump(3)"></item>

      <item top_border="true" imgUrl="update.png" itemName="版本更新" style="margin-top: 60px" :extraName="extraName"
            show_arrow="true" @onClick="jump(4)"></item>
      <item top_border="true" imgUrl="share.png" itemName="推荐给朋友" style="margin-top: 60px"
            show_arrow="true" @onClick="jump(5)"></item>
      <item top_border="true" imgUrl="live.png" itemName="视频直播" style="margin-top: 60px;margin-bottom: 80px"
            show_arrow="true" @onClick="jump(6)" v-if="oldVersion>310230"></item>
    </scroller>
  </div>
</template>

<script>
  const modal = weex.requireModule('modal');
  import mtd from '../js/methods.js'
  import Config from '../js/config.js'

  export default {
    props: {
      versionInfo: {
        default: 'V 3.1.0'
      },
      extraName: {
        default: ''
      },
      oldVersion: {
        default: 0
      },
      newVersion: {
        default: 0
      }

    },
    components: {
      icon: require('./customview/icon.vue'),
      iconImg: require('./customview/icon-img.vue'),
      item: require('./customview/item.vue'),
    },
    created: function () {
      if (mtd.isweb()) {
        window.temp_this = this;
        mtd.registerModules();
      }
      weex.requireModule('event').setConfig(Config.channels, Config.adImgUrl, Config.adImgSchemeUrl);
      weex.requireModule('event').getVersion((versionInfo) => {
        this.versionInfo = versionInfo;
        this.oldVersion = versionInfo.replace(/\./ig, '').replace('V ', '') * 1;
        this.newVersion = Config.newVersion;
        if (this.newVersion > this.oldVersion) {
          this.extraName = '更新新版本';
        } else {
          this.extraName = '已是最新版本';
        }
      });
    },
    methods: {
      font: function (size) {
        return mtd.getFontSize(size);
      },
      jump: function (e) {
        switch (e) {
          case 1:
            weex.requireModule('event')
              .openView('className=cn.ltwc.cft.weex.WeexActivity&ltkj&jsName=message&ltkj&webTitle=消息记录');
            break;
          case 2:
            weex.requireModule('event')
              .openView('className=cn.ltwc.cft.activity.ZhaiNaniActivity');
            break;
          case 3:
            weex.requireModule('event')
              .openView('className=cn.ltwc.cft.activity.TodayonhistoryActivity');
            break;
          case 4:
            if (this.newVersion > this.oldVersion) {
              weex.requireModule('event').update(Config.updateUrl);
            }
            break;
          case 5:
            weex.requireModule('event')
              .openView('className=cn.ltwc.cft.activity.ShareActivity&ltkj&type=image/*&ltkj&msg=王朝黄历\n我正在使用有趣实用的王朝黄历，快来下载吧！\n' + Config.updateUrl + '&ltkj&shareUrl=' + Config.updateUrl);
            //weex.requireModule('event').playVideo('http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8');
            break;
          case 6:
            weex.requireModule('event')
              .openView('className=cn.ltwc.cft.weex.WeexActivity&ltkj&jsName=zhibo&ltkj&webTitle=视频直播');
            break;
        }
      }
    },
  }
</script>
<style scoped="">

</style>
