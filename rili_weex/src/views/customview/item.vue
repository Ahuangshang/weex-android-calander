<template>
    <div :class="[clickStyle(clickable)]" @click="onClick" @longpress="onLongpress"
         :style="{borderTopWidth: top_border ? '1px' : '0px',borderBottomWidth: bottom_border ? '1px' : '0px'}">
        <div style=" flex-direction: row;align-items: center;flex: 1;">
            <icon-img :imgUrl="imgUrl" :imgw="iconSize" :imgFilePath="imgFilePath" v-if="imgUrl.length>0"></icon-img>
            <text :value="itemName"
                  :style="{fontSize:font(itemNameSize),color:itemNameColor,marginLeft:itemNameLeftMargin}"></text>
        </div>
        <text style="text-align: right;" :style="{fontSize:font(extraNameSize),color:extraNameColor}"
              :value="extraName"></text>
        <icon-img imgUrl="jiantou.png" :imgw="iconSize" v-if="show_arrow"></icon-img>
    </div>
</template>

<script>
    import mtd from '../../js/methods.js'

    export default {
        components: {
            iconImg: require('./icon-img.vue'),
            icon: require('./icon.vue'),
        },
        name: "item",
        props: {
            itemName: {
                default: ''
            },
            top_border: {
                default: false
            },
            bottom_border: {
                default: true
            },
            imgUrl: {
                default: ''
            },
            itemNameColor: {
                default: "#1d1d1d"
            },
            itemNameLeftMargin: {
                default: '25px'
            },
            clickable: {
                default: true
            },
            iconSize: {
                default: 60
            },
            itemNameSize: {
                default: 18
            },
            extraNameSize: {
                default: 14
            },
            extraNameColor: {
                default: "#909090"
            },
            extraName: {
                default: ""
            },
            show_arrow: {
                default: false
            },
            imgFilePath: {
                default: 'image_icon/'
            }
        }, methods: {
            onClick: function () {
                this.$emit('onClick');
            },
            onLongpress:function () {
                this.$emit('onLongpress');
            },
            font: function (size) {
                return mtd.getFontSize(size);
            },
            clickStyle: function (isClick) {
                if (isClick) {
                    return 'item'
                } else {
                    return 'unitem'
                }
            }
        }
    }
</script>

<style scoped="">
    .item {
        flex-direction: row;
        align-items: center;
        padding-left: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-right: 10px;
        border-color: rgb(229, 229, 229);
        background-color: rgb(255, 255, 255);
    }

    .item:active {
        background-color: rgb(240, 240, 240);
    }

    .unitem {
        flex-direction: row;
        align-items: center;
        padding-left: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-right: 10px;
        border-color: rgb(229, 229, 229);
        background-color: rgb(255, 255, 255);
    }
</style>
