<template>
    <div>
        <text :style="{ fontSize: font(tex_size_1),marginTop: font(contentMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
              style="color:#555555" :value="year"></text>
        <text :style="{ fontSize: font(title_size), marginTop: font(contentMargin),marginBottom:font(contentMargin),lineHeight:font(line_height)}"
              style="color:#000000;justify-content: center;text-align: center;" :value="title"></text>
        <list>
            <cell v-for="item in dealPicUrl">
                <image :style="{ width: img_w, height:item.height}" :src=item.url placeholder=""></image>
                <text :style="{ fontSize: font(tex_size_1),lineHeight:font(line_height)}"
                      style="color:#555555;justify-content: center;text-align: center;" :value="item.title"></text>
            </cell>
        </list>
        <text :style="{ fontSize: font(tex_size_2),marginTop: font(topMargin),lineHeight:font(line_height), margin:font(contentMargin)}"
              style="color:#0a0a0a" :value="content"></text>
    </div>
</template>

<script>
    var stream = weex.requireModule('stream');
    var mtd = require('../js/methods.js');
    export default {
        data() {
            return {
                ss: "测试页面",
                title: "",
                content: "",
                year: "",
                picUrl: [],
                dealPicUrl: [],
                img_w: 750,
                contentMargin: 7,
                tex_size_1: 12,
                tex_size_2: 16,
                title_size: 20,
                topMargin: 18,
                line_height: 30,
            }
        },
        methods: {
            testWeb: function (eid, type, callback) {
                return stream.fetch({
                    method: 'GET',
                    type: type,
                    url: 'http://v.juhe.cn/todayOnhistory/queryDetail.php?e_id=' + eid + '&key=4b38076dc77166f1d610d1697315c07d'
                }, callback);
            },
            getOptions: function () {
                if (mtd.isweb()) {
                    var bundleUrl = this.$getConfig().bundleUrl;
                    var urlParams = mtd.parseQueryString(bundleUrl);
                    this.year = urlParams.year;
                    this.testWeb(urlParams.e_id, 'jsonp', res => {
                        this.dealBack(JSON.stringify(res));
                    })
                } else {
                    this.year = this.$getConfig().year;
                    this.testWeb(this.$getConfig().e_id, 'json', res => {
                        this.dealBack(JSON.stringify(res));
                    })
                }
            },
            dealBack: function (e) {
                var data = JSON.parse(e).data;
                var result = data.result;
                this.title = result[0].title;
                this.content = result[0].content;
                this.picUrl = result[0].picUrl;
                var that = this;
                this.getDealList(res => {
                    that.dealPicUrl = res;
                });
            },
            getDealList: function (callback) {
                var that = this;
                if (mtd.isweb()) {
                    var dealPicUrl = [];
                    this.picUrl.map((item) => {
                        //console.log(item.url)
                        mtd.checkPicurl(item.url, res => {
                            dealPicUrl.push({
                                height: res + "px",
                                title: res == 0 ? "" : (item.pic_title || "王朝黄历--历史上的今天"),
                                url: item.url,
                            })
                        });
                    });
                    return callback(dealPicUrl);
                }
            },
            font: function (size) {
                return mtd.getFontSize(size)
            },
        },

        created: function () {
            this.getOptions();
        },
    };


</script>