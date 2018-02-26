<template>
    <list>
        <cell v-for="(item,i) in datas " :key="i" append="tree">
            <div :style="{ backgroundColor: bgColor }" class="item" v-on:click="click(i)">
                <text class="item-title">{{item.title}}</text>
                <text class="item-content">{{item.content}}</text>
                <text class="item-time">{{item.time}}</text>
            </div>

        </cell>


    </list>


</template>

<style scoped="">
    .item {
        padding-top: 25px;
        padding-bottom: 25px;
        padding-left: 35px;
        padding-right: 35px;
        min-height: 200px;
        justify-content: center;
        /*margin-bottom: 1px; FUTURE */
        border-bottom-width: 1px;
        border-color: #dddddd;
    }

    .item:active {
        background-color: rgb(240, 240, 240);
    }

    .item-title {
        font-size: 40px;
        color: #303030;
    }

    .item-content {
        margin-top: 5px;
        font-size: 32px;
        color: #000000;
    }

    .item-time {
        font-size: 32px;
        color: #6c6c6c;
        text-align: right;

    }
</style>
<script>
    import mtd from '../js/methods.js'

    export default {
        data() {
            return {
                datas: [
                    {
                        viewName: "springFestival",
                        title: "春节",
                        content: "春节是指汉字文化圈传统上的农历新年，俗称“年节”，传统名称为新年、大年、新岁，但口头上又称度岁、庆新岁、过年。",
                        time: "2018-02-15",
                        shareUrl: "http://zerosboy.site/Ahuangshang/html/springFestival.html",
                    },
                    {
                        viewName: "dongzhi",
                        title: "冬至",
                        content: "冬至是农历的重要节气，也是中华民族的传统节日。早在春秋时代，中国就已经用土圭观测出冬至，古人认为自冬至起阳气回升，代表着下一个循环的开始。冬至是重要的养生时期，应注意防寒保暖、保肝护肝。",
                        time: "2017-12-22",
                        shareUrl: "http://zerosboy.site/Ahuangshang/html/dongzhi.html",
                    },
                    {
                        viewName: "yieryisan",
                        title: "国家公祭日",
                        content: "南京大屠杀死难者国家公祭仪式于12月13日上午10时举行。",
                        time: "2017-12-12",
                        shareUrl: "http://zerosboy.site/Ahuangshang/html/yieryisan.html",
                    },
                  {
                    viewName: "shuangshiyi",
                    title: "双十一攻略",
                    content: "双十一红包大派送|更多省钱秘籍等你来取！",
                    time: "2017-11-10",
                    shareUrl: "http://zerosboy.site/Ahuangshang/html/shuangshiyi.html",
                  },
                    {
                        viewName: "mid-autumn-festival",
                        title: "中秋节",
                        content: "中秋节自古便有祭月、赏月、拜月、吃月饼、赏桂花、饮桂花酒等习俗，流传至今，久经不息。中秋节以月之圆兆人之团圆，为寄托思念故乡，思念亲人之情，祈盼丰收、幸福，成为丰富多彩、弥足珍贵的文化遗产。中秋节与端午节、春节、清明节并称为中国四大传统节日。",
                        time: "2017-10-04",
                        shareUrl: "http://zerosboy.site/Ahuangshang/html/mid-autumn-festival.html",
                    },
                  {
                    viewName: "guanggao",
                    title: "开业大吉",
                    content: "视频VIP豪送|庆国庆、迎中秋，万份视频VIP等你来领！",
                    time: "2017-10-01",
                    shareUrl: "http://zerosboy.site/Ahuangshang/html/guanggao.html",
                  },
                  {
                        viewName: "bailu",
                        title: "白露",
                        content: "白露是农历二十四节气中的第十五个节气，当太阳到达黄经165度时为白露。",
                        time: "2017-09-07",
                        shareUrl: "http://zerosboy.site/Ahuangshang/html/bailu.html",
                    },
                ],
                bgColor: "#ffffff",
                title: "",
                content: "",
                time: "",

            }
        },
        methods: {
            click: function (i) {
                //weex.requireModule('event').openWeexView(this.datas[i].viewName, this.datas[i].title, this.datas[i].shareUrl);
                weex.requireModule('event')
                    .openView('className=cn.ltwc.cft.weex.WeexActivity&ltkj&jsName=' + this.datas[i].viewName + '&ltkj&webTitle=' + this.datas[i].title + '&ltkj&shareUrl=' + this.datas[i].shareUrl);
            },
        },
        created: function () {
            if (mtd.isweb()) {
                window.temp_this = this;
                mtd.registerModules();
            }
        },
    };

</script>