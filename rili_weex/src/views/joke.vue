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
                        <wxc-pan-item
                                @wxcPanItemPan="wxcPanItemPan">
                            <div class="item" @click="itemClick(props.item)">
                                <text style="flex: 1" class="title" :style={fontSize:font(18)}
                                      :value="props.item.title"></text>
                                <div style="justify-content:flex-end;flex-direction:row;padding-right:40px;padding-bottom: 25px">
                                    <icon-img imgUrl="share.png" imgw="40"
                                              @onClick="share(index,props.item.title)"></icon-img>
                                </div>
                            </div>
                        </wxc-pan-item>
                    </div>
                </pullrefreshListview>
            </div>
        </wxc-tab-page>
    </div>
</template>
<script>
    import {BindEnv, WxcPanItem, WxcResult, WxcTabPage} from 'weex-ui';
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
            pullrefreshListview: require('./customview/pullrefresh-listview.vue'),
            iconImg: require('./customview/icon-img.vue'),
        },
        data: () => ({
            tabTitles: Config.jokeTabTitles,
            tabStyles: Config.jokeTabStyles,
        }),
        props: {
            show: {
                default: false
            },
            type: {
                default: 'errorPage'
            },
            pageSize: {
                default: 10
            },
            list: {
                default: function () {
                    function getList() {
                        var temp = [[]];
                        for (let i = 0; i < Config.jokeTabTitles.length; i++) {
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
            netUrl: {
                default: 'https://api.bmob.cn/1/classes/funny_iq/'
            },
            currentIndex: {
                default: 0
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
            getData: function (isLoadMore, showLoading, length, callback) {
                var that = this;
                if (mtd.isweb()) {
                    this.pageSize = 20;
                }
                var time = mtd.toDateString();
                var clist = this.list[this.currentIndex];
                if (!(clist === 'undefined' || clist.length === 0) && isLoadMore) {
                    time = clist[clist.length - 1].createdAt;
                }
                this.params = {
                    where: {"updatedAt": {"$lte": {"__type": "Date", "iso": "" + time}}},
                    limit: this.pageSize,
                    skip: 0,
                    order: '-createdAt,-updateAt'
                };
                weex.requireModule('net').requestNetData('get', this.netUrl, '', JSON.stringify(this.params), showLoading, function (ret) {
                    that.dealData(ret, callback);
                });
            },
            dealData(ret, callback) {
                if (ret != null) {
                    var list = JSON.parse(ret).results;
                    if (list != null) {
                        callback(list);
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
                this.currentIndex = e.page;
                this.netUrl = this.tabTitles[this.currentIndex].netUrl;
                this.getDataAgain(this.currentIndex);
            },
            itemClick: function (e) {
                if (this.tabTitles[this.currentIndex].title === '脑筋急转弯') {
                    var answer = JSON.parse(e.extra).as;
                    weex.requireModule('event').showDialogKnow('', answer);
                }
            },
            share: function (index, content) {
                weex.requireModule('event')
                    .openView('className=cn.ltwc.cft.activity.ShareActivity&ltkj&type=text/plain&ltkj&msg=我正在使用王朝黄历的【' + this.tabTitles[index].title + '】，一起围观下吧：\n'
                        + content + '&ltkj&shareUrl=');
            },
            //该方法压根没有触发，不知道为什么
            wxcPanItemPan: function (e) {
                if (BindEnv.supportsEBForAndroid()) {
                    this.$refs['wxc-tab-page'].bindExp(e.element);
                }
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
