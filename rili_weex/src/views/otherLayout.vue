<template>
  <div>
    <div style="background-color: white" v-if="showLottery">
      <div class="left_and_right_sides_layout">
        <text style="color: black;"
              :style="{fontSize:font(16),marginLeft:font(17),paddingTop:font(9),paddingBottom:font(9)}"
              value="彩票开奖"></text>
        <div class="moreLottery"
             :style="{paddingTop:font(9),paddingBottom:font(9),paddingLeft:font(7)}"
             @click="moreLotteryClick()">
          <text style="color: rgba(0,0,0,0.5);" :style="{fontSize:font(15)}"
                value="更多开奖"></text>
          <icon-img imgUrl="jiantou.png" imgw="50"></icon-img>
        </div>
      </div>
      <div style="background-color:rgba(0,0,0,0.2);height: 0.5px"></div>
      <div :style="{padding:font(17)}" class="ssq" @click="ssqClick()">
        <div style="flex-direction: row">
          <text style="color: rgba(0,0,0,0.8);" :style="{fontSize:font(14)}"
                value="双色球"></text>
          <text style="color: rgba(0,0,0,0.5);" :style="{fontSize:font(14),marginLeft: font(3)}"
                :value="qh"></text>
        </div>
        <div style="flex-direction: row" :style="{marginTop:font(7)}">
          <lotteryItem :value="red1"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="red2"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="red3"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="red4"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="red5"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="red6"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="blue" bg="#37a1ff"></lotteryItem>
        </div>
      </div>
      <div style="background-color:rgba(0,0,0,0.2);height: 0.5px"></div>
      <div :style="{padding:font(17)}" class="ssq" @click="dltClick()">
        <div style="flex-direction: row">
          <text style="color: rgba(0,0,0,0.8);" :style="{fontSize:font(14)}"
                value="大乐透"></text>
          <text style="color: rgba(0,0,0,0.5);" :style="{fontSize:font(14),marginLeft: font(3)}"
                :value="dltqh"></text>
        </div>
        <div style="flex-direction: row" :style="{marginTop:font(7)}">
          <lotteryItem :value="dred1" bg="#ed8431"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dred2" bg="#ed8431"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dred3" bg="#ed8431"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dred4" bg="#ed8431"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dred5" bg="#ed8431"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dblue1" bg="#696bc9"></lotteryItem>
          <lotteryItem :style="{marginLeft: font(6)}"
                       :value="dblue2" bg="#696bc9"></lotteryItem>
        </div>
      </div>


    </div>
    <div :style="{height:font(12)}" v-if="showLottery"></div>
  </div>
</template>

<script>
  const modal = weex.requireModule('modal');
  import mtd from '../js/methods.js';

  export default {
    props: {
      showLottery: {
        default: true
      },
      qh: {
        default: '-正在努力在加中....'
      },
      dltqh: {
        default: '-正在努力在加中....'
      },
      dred1: {
        default: '正'
      },
      dred2: {
        default: '在'
      },
      dred3: {
        default: '努'
      },
      dred4: {
        default: '力'
      },
      dred5: {
        default: '加'
      },
      dblue1: {
        default: '载'
      },
      dblue2: {
        default: '中'
      },
      red1: {
        default: '正'
      },
      red2: {
        default: '在'
      },
      red3: {
        default: '努'
      },
      red4: {
        default: '力'
      },
      red5: {
        default: '加'
      },
      red6: {
        default: '载'
      },
      blue: {
        default: '中'
      },
      lotterParams: {
        default:
          function () {
            return {}
          }
      },
    },
    components: {
      iconImg: require('./customview/icon-img.vue'),
      lotteryItem:
        require('./customview/lottery-item.vue'),
    },
    methods: {
      getLot: function () {
        if (this.showLottery) {
          var that = this;
          weex.requireModule('net').requestNetData('get', 'http://f.apiplus.net/ssq-1.json/', '', "", false, function (ret) {
            that.dealSSq(ret);
          });
        }
      },
      getDlt: function () {
        if (this.showLottery) {
          var that = this;
          weex.requireModule('net').requestNetData('get', 'http://f.apiplus.net/dlt-1.json/', '', "", false, function (ret) {
            that.dealDLt(ret);
          });
        }
      },
      font: function (size) {
        return mtd.getFontSize(size)
      },
      dealSSq: function (ret) {
        const kaijiang = JSON.parse(ret).data;
        if (mtd.isNotNull(kaijiang)) {
          if (kaijiang.length > 0) {
            var data = kaijiang[0];
            this.qh = '-第' + data.expect + '期';
            var lot = data.opencode;
            var s = lot.split("+");
            this.blue = s[1];
            var reds = s[0].split(',');
            this.red1 = reds[0];
            this.red2 = reds[1];
            this.red3 = reds[2];
            this.red4 = reds[3];
            this.red5 = reds[4];
            this.red6 = reds[5];
          }
        }
      },
      dealDLt: function (ret) {
        const kaijiang = JSON.parse(ret).data;
        if (mtd.isNotNull(kaijiang)) {
          if (kaijiang.length > 0) {
            var data = kaijiang[0];
            this.dltqh = '-第' + data.expect + '期';
            var lot = data.opencode;
            var s = lot.split("+");
            var blues = s[1].split(',');
            this.dblue1 = blues[0];
            this.dblue2 = blues[1];
            var reds = s[0].split(',');
            this.dred1 = reds[0];
            this.dred2 = reds[1];
            this.dred3 = reds[2];
            this.dred4 = reds[3];
            this.dred5 = reds[4];

          }
        }
      },
      moreLotteryClick: function () {
        weex.requireModule('event')
          .openView('className=cn.ltwc.cft.activity.MyX5WebView&ltkj&webUrl=https://m.500.com/info/kaijiang/#h5&ltkj&webTitle=开奖公告');
      },
      ssqClick: function () {
        weex.requireModule('event')
          .openView('className=cn.ltwc.cft.activity.MyX5WebView&ltkj&webUrl=https://m.500.com/info/kaijiang/moreexpect/ssq/?from=index&ltkj&webTitle=双色球开奖详情');
      },
      dltClick: function () {
        weex.requireModule('event')
          .openView('className=cn.ltwc.cft.activity.MyX5WebView&ltkj&webUrl=https://m.500.com/info/kaijiang/moreexpect/dlt/?from=index&ltkj&webTitle=大乐透开奖详情');
      },
    },
    created: function () {
      this.getLot();
      this.getDlt();
    },
  }
</script>

<style scoped="">
  .moreLottery {
    flex-direction: row;
    align-items: center
  }

  .moreLottery:active {
    flex-direction: row;
    align-items: center;
    background-color: rgb(240, 240, 240);
  }

  .ssq {

  }

  .ssq:active {
    background-color: rgb(240, 240, 240);
  }

  .left_and_right_sides_layout {
    flex-direction: row;
    justify-content: space-between;
    align-items: center
  }
</style>
