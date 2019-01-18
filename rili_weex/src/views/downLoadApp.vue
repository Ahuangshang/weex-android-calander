<template>
    <div style="flex: 1" :class="[div()]">
        <image class="bg" src="image/bg.jpg"></image>
        <div style="align-items: center;flex-direction: column;margin-top: 120px" v-if="!isweixin">
            <icon-img imgUrl="icon.png" imgw="100"></icon-img>
            <text style="margin-top: 20px;color: #fdfdfd;font-weight: bold" value="王朝黄历"
                  :style="{fontSize:font(20)}"></text>
            <text style="margin-top: 50px;color: #fdfdfd" value="有趣实用的王朝黄历"
                  :style="{fontSize:font(16)}"></text>
            <text style="margin-top: 15px;color: #fdfdfd" value="千万人次的选择"
                  :style="{fontSize:font(16)}"></text>
            <text style="margin-top: 15px;color: #fdfdfd" value="你还在等什么？"
                  :style="{fontSize:font(16)}"></text>

            <a href="http://imengu.cn/Ahuangshang/apk/latest.apk">
                <div style="background-color: #31A9A5 ; border-radius: 10px;margin-top: 25px">
                    <text style="margin: 15px;color: #fdfdfd" value="点我下载吧"
                          :style="{fontSize:font(16)}"></text>
                </div>
            </a>
        </div>
        <div class="hint" v-if="isweixin">
            <image class="hintImage" src="image/hint.png"></image>
        </div>
    </div>
</template>

<script>
    var mtd = require('../js/methods.js');
    const modal = weex.requireModule('modal');
    export default {
        name: "downLoadApp",
        props: {
            isweixin: {
                default: true
            }
        },
        components: {
            iconImg: require('./customview/icon-img.vue'),
        },
        methods: {
            div: function () {
                return mtd.is_weixn() ? 'div_weixin' : 'div';
            },
            font: function (size) {
                return mtd.getFontSize(size)
            },
            is_weixin: function (e) {
                this.isweixin = mtd.is_weixn()
            },
        },
        created: function () {
            this.is_weixin();
        }
    }
</script>

<style scoped="">
    .div {
        height: 1300px;
        position: absolute;
        right: 0;
        top: 0;
        left: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .div_weixin {
        position: absolute;
        right: 0;
        top: 0;
        left: 0;
        bottom: 0;
        height: 1300px;
        background-color: rgba(0, 0, 0, 1);
    }

    .hint {
        width: 750px;
        position: absolute;
        right: 0;
        top: 0;
        left: 0;
        bottom: 0;
        align-items: center;
    }

    .hintImage {
        width: 618.75px;
        height: 1100px;
    }

    .bg {
        opacity: 0.3;
        position: absolute;
        right: 0;
        top: 0;
        left: 0;
        bottom: 0;
        width: 750px;
        height: 1300px;
    }
</style>
