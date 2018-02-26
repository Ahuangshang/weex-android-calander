<template>
  <scroller>
    <div :style="{ marginLeft: contentMargin,marginRight: contentMargin}">
      <text
        :style="{ fontSize: font(title_size),marginTop: contentMargin, marginBottom:contentMargin,lineHeight:line_height2}"
        style="color:#000000;justify-content: center;text-align: center;" :value="title"></text>
      <text :style="{ fontSize: font(tex_size_2) ,marginTop:contentMargin}"> 2017-10-23 李唐科技</text>
      <image :style="{ width: img_w, height:img_1_h }"
             src="http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_1.jpg"
             placeholder=""></image>
      <div>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color:#020402;" :value="content_1"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;" :value="content_2"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_2_h }"
                 src="http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_2.jpg"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;" :value="content_3"></text>
        <text :style="{ fontSize: font(title_size), margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_16"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;" :value="content_4"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_3_h }"
                 src="http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_3.jpg"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_5"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_6"></text>
        <text :style="{ fontSize: font(title_size),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;justify-content: center;text-align: center;" :value="content_7"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_5_h }"
                 src="http://zerosboy.site/Ahuangshang/img/shuangshiyi/shuangshiyi_4.jpg"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_8"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;justify-content: center;text-align: center;" :value="content_9"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;justify-content: center;text-align: center;" :value="content_10"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;justify-content: center;text-align: center;" :value="content_11"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;justify-content: center;text-align: center;" :value="content_12"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_13"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_14"></text>
        <text :style="{ fontSize: topMargin,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_15"></text>
        <text :style="{ fontSize: font(tex_size_1),marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #f45531;"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_4_h }"
                 src="http://zerosboy.site/Ahuangshang/img/advertisement/ad3.jpg"
                 placeholder=""></image>
        </div>
        <!--<text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"-->
        <!--style="color: #1D67F4;">{{content_21}}</text>-->
        <text :style="{ fontSize: font(tex_size_3),marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #020402;" :value="content_22"></text>
      </div>
    </div>
  </scroller>

</template>

<script>
  //  var buiweex = require("bui-weex");
  var modal = weex.requireModule('modal');
  import mtd from '../js/methods.js'

  export default {
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
      },
    },
    created: function () {
    },
    methods: {
      font: function (size) {
        return mtd.getFontSize(size)
      },
    }
  };
</script>