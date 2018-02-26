<template>
    <div>
        <list class="list_view" loadmoreoffset="2" else>
            <!--下拉刷新组件-->
            <refresh ref="refresh" class="refresh" @refresh="onRefresh" @pullingdown="onPullingdown"
                     :display="refreshing ? 'show' : 'hide'">
                <loading-indicator class="indicator"></loading-indicator>
                <text class="refresh-indicator">{{refreshText}}</text>
            </refresh>
            <cell v-for="(v,index) in list">
            <slot name="myslot" :item="v" :i="index"></slot>
            </cell>
            <!--加载更多-->
            <loading ref="loading" class="loading" @loading="onLoading" :display="showLoading ? 'show' : 'hide'">
                <text class="loading-indicator" v-if="showLoading">{{loadingText}}</text>
                <loading-indicator class="indicator"></loading-indicator>
            </loading>
        </list>
    </div>
</template>

<script>
    import mtd from '../../js/methods.js'

    const modal = weex.requireModule('modal');
    export default {
        props: {
            list: {
                default: function () {
                    return []
                }
            },
            refreshing: {
                default: false
            },
            showLoading: {
                default: false
            },
            refreshText: {
                default: "下拉刷新..."
            },
            loadingText: {
                default: "加载更多数据..."
            },
            pageSize: {
                default: 10
            },
        },
        methods: {
            onRefresh: function (e) {
                var that = this;
                that.refreshing = true;
                that.refreshText = "正在刷新...";
                that.$emit('getData', false, 0, res => {
                    that.refreshing = false;
                    // modal.alert({
                    //   message: '***' + that.refreshing,
                    // }, function (e) {
                    //
                    // });
                    that.refreshText = "刷新成功";
                    if (mtd.isNotNull(res)) {
                        that.list.splice(0, that.list.length);
                        if (res.length < that.pageSize) {
                            that.loadingText = '没有更多数据了';
                        } else {
                            that.loadingText = '加载更多数据...';
                        }
                        res.map((item => {
                            that.list.push(item);
                        }));
                    }
                    that.refreshing = false;
                });

            },
            onPullingdown: function (e) {
                this.refreshText = "下拉可以刷新...";
                //下拉到一定距离时改变文字
                if (Math.abs(e.pullingDistance) > 50) {
                    this.refreshText = "松开即可刷新...";
                }
            },
            onLoading: function (e) {
                var that = this;
                that.showLoading = true;
                that.$emit('getData', false, that.list.length / that.pageSize, res => {
                    that.showLoading = false;
                    if (mtd.isNotNull(res)) {
                        if (res.length < that.pageSize) {
                            that.loadingText = '没有更多数据了';
                        } else {
                            that.loadingText = '加载更多数据...';
                            res.map((item => {
                                that.list.push(item);
                            }));
                        }
                    }
                });
            },
        },
        mounted: function () {
        }
    }
</script>

<style scoped="">
    .list_view {
      flex: 1;
    }

    .refresh {
        justify-content: center;
        flex-direction: row;
        width: 750px;
        height: 100px;
        display: -ms-flex;
        display: -webkit-flex;
        display: flex;
        -ms-flex-align: center;
        -webkit-align-items: center;
        -webkit-box-align: center;
        align-items: center;
        /*padding-top: 10px;*/
        /*padding-bottom: 10px;*/
    }

    .loading {
        width: 750px;
        height: 150px;
        flex-direction: column;
        display: -ms-flex;
        display: -webkit-flex;
        display: flex;
        -ms-flex-align: center;
        -webkit-align-items: center;
        -webkit-box-align: center;
        align-items: center;
        justify-content: center;
        /*padding-top: 10px;*/
        /*padding-bottom: 10px;*/
    }

  .refresh-indicator {
    margin-left: 10px;
    font-size: 30px;
    text-align: center;
    color: rgb(158, 167, 180);
  }

  .loading-indicator {
    font-size: 30px;
    text-align: center;
    color: rgb(158, 167, 180);
  }

  .indicator {
    height: 60px;
    width: 60px;
    color: rgb(158, 167, 180);
  }
</style>