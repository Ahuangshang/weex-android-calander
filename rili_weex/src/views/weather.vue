<template>
    <div :class="[rootclass()]">
        <div class="empty_view" v-if="forecast.length===0&&!refresh">
            <wxc-result
                    padding-top="232"
                    @wxcResultButtonClicked="resultButtonClick"
                    :show="show"/>
        </div>
        <scroller show-scrollbar="false" v-if="forecast.length>0||refresh">
            <div style="padding-bottom: 10px" v-if="forecast.length>0||refresh">
                <icon-img imgw="750px" imgh="700px" :imgUrl="getWeatherImgUrl()" imgFilePath="weather/" resize="stretch"
                          :background-color="bg"></icon-img>
                <div class="weatherTop">
                    <div style="flex-direction: row;margin-top: 70px;">
                        <text class="textWeight" :style="{fontSize:font(60)}" :value="currentTemperature"></text>
                        <text class="textWeight" style="margin-top: 14px;" :style="{fontSize:font(20)}"
                              value="℃"></text>
                    </div>
                    <text class="textWeight" style="margin-top: 10px;" :style="{fontSize:font(22)}"
                          :value="params.city"></text>
                    <text class="textWeight" style="margin-top: 10px;" :style="{fontSize:font(16)}"
                          :value="currentType"></text>
                    <text class="textWeight" style="position: absolute;bottom: 20px;" :value="quality"
                          :style="{fontSize:font(16)}"></text>
                </div>
                <div class="item" v-for="(item,index) in forecast">
                    <text class="itemDec" :value="index===0?'今天':index===1?'明天':item.date"
                          :style="{fontSize:font(16)}"></text>

                    <text class="itemDec" :value="item.type"
                          :style="{fontSize:font(16)}"></text>
                    <text class="itemDec" :value="item.dec"
                          :style="{fontSize:font(16)}"></text>
                </div>
            </div>
        </scroller>
    </div>
</template>

<script>
    import mtd from '../js/methods.js';
    import Config from '../js/config.js'
    import {WxcResult} from 'weex-ui';

    const modal = weex.requireModule('modal');
    var stream = weex.requireModule('stream');
    if (mtd.isweb()) {
        window.noticeReceiver = function (name, data) {
            window.temp_this.distributeData(name, {'jsonData': JSON.stringify(data)});
        };
    }
    export default {
        components: {
            WxcResult,
            iconImg: require('./customview/icon-img.vue'),
        },
        props: {
            show: {
                default:
                    false
            },
            refresh: {
                default: false
            },
            params: {
                default: function () {
                    return {}
                }
            },
            bg: {
                default: '#42d7d1'
            },
            currentTemperature: {
                default: ''
            },
            quality: {
                default: ''
            },
            forecast: {
                default: function () {
                    return []
                }
            },
            currentType: {
                default: ''
            }
        },
        methods: {
            rootclass: function () {
                return mtd.isweb() ? "rootWeb" : "root";
            },
            font: function (size) {
                return mtd.getFontSize(size)
            },
            resultButtonClick(e) {
                this.getWeatherInfo(true);
            },
            getWeatherInfo: function (showLoading) {
                var that = this;
                weex.requireModule('net').requestNetData('get', 'http://www.sojson.com/open/api/weather/json.shtml/', '', JSON.stringify(that.params), showLoading, function (ret) {
                    that.dealBack(ret);
                    that.show = true;
                });
            },
            getOptions: function () {
                var env = this.$getConfig().env;
                if (env.platform.toLocaleLowerCase() === 'web') {
                    var bundleUrl = this.$getConfig().bundleUrl;
                    var urlParams = mtd.parseQueryString(bundleUrl);
                    this.params = {
                        city: urlParams.city,
                    };
                } else {
                    this.params = {
                        city: this.$getConfig().city,
                    };
                }
            },
            dealBack: function (ret) {
                if (ret != null) {
                    var data = JSON.parse(ret).data;
                    if (data != null) {
                        this.currentTemperature = data.wendu;
                        this.quality = '空气质量  ' + data.quality;
                        this.currentType = data.forecast[0].type;
                        this.forecast.splice(0, this.forecast.length);
                        data.forecast.map((item) => {
                            this.forecast.push({
                                date: '周' + item.date.substring(item.date.length - 1, item.date.length),
                                type: item.type,
                                dec: Config.getWeatherDec(item.high, item.low)
                            });
                        })
                    }
                }
            },
            getWeatherImgUrl: function () {
                return Config.getWeatherTypeImg(this.currentType);
            },
            distributeData: function (type, data) {
                this.params = {
                    city: JSON.parse(data).jsonData,
                };
                this.refresh = false;
                this.getWeatherInfo(false);
            },
        },
        created: function () {
            if (mtd.isweb()) {
                window.temp_this = this;
                mtd.registerModules();
            }
            this.getOptions();
        },
        mounted: function () {
            this.getWeatherInfo(true);
            weex.requireModule('globalEvent').addEventListener('locationInfo', (data) => {
                this.distributeData("locationInfo", JSON.stringify(data));
            });
        },
    }
</script>

<style scoped="">
    .root {

    }

    .rootWeb {
        overflow-y: auto;
        overflow-x: hidden;
    }

    .textWeight {
        color: white;
    }

    .weatherTop {
        height: 700px;
        flex-direction: column;
        padding-left: 40px;
        padding-right: 40px;
        position: absolute;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0
    }

    .item {
        flex-direction: row;
        align-items: center;
        padding: 40px;
        border-color: rgb(229, 229, 229);
        background-color: rgb(255, 255, 255);
        border-bottom-width: 1px;
        justify-content: space-between;
    }

    .itemDec {
        color: rgba(54, 67, 78, 0.67);
        align-items: center
    }

    .empty_view {
        position: absolute;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0;
        background-color: rgb(255, 255, 255);
    }
</style>