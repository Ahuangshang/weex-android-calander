<template>
  <!--<scroller>-->

  <div>
    <div :style="{ marginLeft: font(contentMargin),marginRight:  font(contentMargin)}">
      <text
        :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin: font(contentMargin),lineHeight:font(line_height)}"
        style="color: #000000; text-align: center;" :value="title"></text>
      <text :style="{  fontSize: font(tex_size_2) ,marginTop: font(contentMargin)}" style="color:  #1d1d1d;"
            value=" 来源：江苏新闻广播"></text>
    </div>
    <!--<web ref="webview" :src="url" style="height: 1000px"></web>-->
    <scroller show-scrollbar="false">
      <div :style="{ marginLeft:  font(contentMargin),marginRight:  font(contentMargin)}">
        <text
          :style="{  fontSize: font(tex_size_3),marginTop: font(topMargin), margin: font(contentMargin),lineHeight:font(line_height)}"
          style="color: #161616;" :value="content_1"></text>
        <a href="http://news.jstv.com/wap/tvlive/20171212/1513059469134.shtml" v-if="isweb">
          <text
            :style="{  fontSize: font(tex_size_2) ,marginTop: font(contentMargin),marginBottom:font(topMargin),margin: font(contentMargin)}"
            style="color: #255fff;padding-top: 20px;padding-bottom: 20px"
            value="点击查看纪念活动视频 >>"></text>
        </a>

        <text v-else
              :style="{  fontSize: font(tex_size_2) ,marginTop: font(contentMargin),marginBottom:font(topMargin),margin: font(contentMargin)}"
              style="color: #255fff;padding-top: 20px;padding-bottom: 20px"
              value="点击查看纪念活动视频 >>" @click="jump"></text>

      </div>
    </scroller>

  </div>

  <!--</scroller>-->

</template>

<script>
  //  var buiweex = require("bui-weex");
  var modal = weex.requireModule('modal');
  import mtd from '../js/methods.js'

  export default {
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
        default: "        今年是南京大屠杀惨案发生80周年。12月13日上午10时，中央按照逢10周年规格，将在侵华日军南京大屠杀遇难同胞纪念馆集会广场举行南京大屠杀死难者国家公祭仪式，届时周边道路将实施交通管制。\n" +
        "\n" +
        "　　12月13日早晨7点，在侵华日军南京大屠杀遇难同胞纪念馆的集会广场举行升国旗和降半旗仪式。\n" +
        "\n" +
        "　　上午10点，在南京17处南京大屠杀遇难同胞丛葬地、12个社区和6家反映抗战主题的爱国主义教育基地，与国家公祭仪式同步举行悼念南京大屠杀死难者活动。\n" +
        "\n" +
        "　　公祭日当天还将开展多项悼念活动，上午10点，由中国博物馆协会纪念馆专业委员会组织中国人民抗日战争胜利纪念馆、沈阳“九一八”历史博物馆、上海淞沪抗战纪念馆等国内20家反映抗战主题的纪念馆同步举行悼念活动。\n" +
        "\n" +
        "　　12月13日国家公祭日早上7点到下午1点期间，侵华日军南京大屠杀遇难同胞纪念馆周边道路将实施交通管制，届时在相关路段上运行的21条公交线路将采取临时调整措施，地铁2号线“云锦路”站也将临时封闭，市交通局副局长郑春发提醒：相关路段公交线路在管制解除后，公交线路恢复运营路段，特别提醒地铁2号线当天上午下午1点之前，站临时关闭，跟以前不一样，上午云锦路站整个上午是关闭的，提醒广大市民提前做好出行计划和线路安排，乘坐公共交通出行的要提早出门。\n" +
        "\n" +
        "　　今年7月到国家公祭日期间，南京组织开展了以“勿忘国耻、圆梦中华”为主题的4大类28项主题教育活动，其中，共六场的“抗战家书”征集暨诵读活动，最后一场定于12月9号下午，在市档案馆举行；12月，在侵华日军南京大屠杀遇难同胞纪念馆遇难者名单墙前，举行为期一个月的南京大屠杀死难者遗属家庭祭告仪式，同时开展家祭微传播活动。此外，学术研讨活动、相关出版物首发式、系列文化活动于近期陆续举办。"
      },
    },
    created: function () {
      if (mtd.isweb()) {
        window.temp_this = this;
        mtd.registerModules();
        this.isweb = true;
      }
    },
    methods: {
      font: function (size) {
        return mtd.getFontSize(size);
      },
      jump: function (e) {
        weex.requireModule('event')
          .openView('className=cn.ltwc.cft.activity.MyX5WebView&ltkj&webTitle=国家公祭日&ltkj&webUrl=' + this.url + '&ltkj&shareUrl=' + this.url + '&ltkj&barShow=false');


      },
    }
  };
</script>