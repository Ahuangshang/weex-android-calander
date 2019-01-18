<template>
  <scroller>
    <div :style="{ marginLeft: contentMargin,marginRight: contentMargin}">
      <text
        :style="{ fontSize: title_size,marginTop: contentMargin, marginBottom:contentMargin,lineHeight:line_height2}"
        style="color:#000000;justify-content: center;text-align: center;" :value="title"></text>
      <text :style="{ fontSize: tex_size_2 ,marginTop:contentMargin}"> 2017-10-01 李唐科技</text>
      <image :style="{ width: img_w, height:img_1_h }"
             src="http://imengu.cn/Ahuangshang/img/advertisement/ad.jpg"
             placeholder=""></image>
      <div>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color:#020402;justify-content: center;text-align: center;" :value="content_1"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_2"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_3"></text>
        <image :style="{ width: img_w, height:img_2_h }"
               src="http://imengu.cn/Ahuangshang/img/advertisement/ad2.jpg"
               placeholder=""></image>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_4"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_5"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_6"></text>
        <image :style="{ width: img_w, height:img_3_h }"
               src="http://imengu.cn/Ahuangshang/img/advertisement/ad4.jpg"
               placeholder=""></image>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_7"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_8"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_9"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10">}
        </text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10">}
        </text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_10"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_11"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_4_h }"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad5.jpg"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_12"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_13"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #020402;justify-content: center;text-align: center;" :value="content_14"></text>
        <image :style="{ width: img_w, height:img_5_h }"
               src="http://imengu.cn/Ahuangshang/img/advertisement/ad1.jpg"
               placeholder=""></image>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_15"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_16"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #f45531;"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_17"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_18"></text>
        <div :style="{ width: img_w }" style="justify-content: center;flex-direction: row">
          <image :style="{ width: img_4_h, height:img_6_h }"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad_qq.png"
                 placeholder=""></image>
          <image :style="{ width: img_4_h, height:img_6_h ,marginLeft:contentMargin}"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad_aqy.png"
                 placeholder=""></image>
        </div>
        <div :style="{ width: img_w ,marginTop:contentMargin}"
             style="justify-content: center;flex-direction: row">
          <image :style="{ width: img_4_h, height:img_6_h }"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad_yk.png"
                 placeholder=""></image>
          <image :style="{ width: img_4_h, height:img_6_h ,marginLeft:contentMargin}"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad_hy.png"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height}"
              style="color: #f45531;"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_19"></text>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #f45531;" :value="content_20"></text>
        <div :style="{ width: img_w }"
             style="align-items: center;">
          <image :style="{ width: img_4_h, height:img_4_h }"
                 src="http://imengu.cn/Ahuangshang/img/advertisement/ad3.jpg"
                 placeholder=""></image>
        </div>
        <text :style="{ fontSize: tex_size_1,marginTop: topMargin, margin:contentMargin,lineHeight:line_height2}"
              style="color: #1D67F4;" :value="content_21"></text>
        <text :style="{ fontSize: tex_size_3,marginTop: topMargin, margin:contentMargin,lineHeight:tex_size_2}"
              style="color: #020402;" :value="content_22"></text>
      </div>
    </div>
  </scroller>

</template>

<script>
  export default {
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
      },
    },
    created: function () {
    },
  };
</script>
