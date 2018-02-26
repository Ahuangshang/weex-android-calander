<template>
    <div class="div" @click="onClick" :style="{width:imgw,height:getImgHeight(imgh),backgroundColor:bgColor}">
        <image class="icon" :resize="resize"
               :style="{width:imgw,height:getImgHeight(imgh), marginLeft: padding_left, marginRight: padding_right }"
               :src="getSrc(imgUrl)">
        </image>
    </div>
</template>
<style scoped="">
    .div {
        align-items: center;
        flex-direction: column;
    }

    .icon {
        width: 50px;
        height: 50px;
    }
</style>

<script>
    import mtd from '../../js/methods.js'
    import Config from '../../js/Config.js'

    export default {
        props: {
            imgw: {
                default: 50,
            },
            imgh: {
                default: 0,
            },
            imgUrl: {
                default: '',
            },
            padding_left: {
                default: 0
            },
            padding_right: {
                default: 0
            },
            bgColor: {
                default: '#00000000',
            },
            resize: {
                default: 'contain'
            },
            imgFilePath: {
                default: 'image_icon/'
            }
        },
        methods: {
            onClick: function () {
                this.$emit('onClick');
            },
            font: function (size) {
                return mtd.getFontSize(size);
            },
            getSrc: function (imgUrl) {
                return Config.HostImgUrl + this.imgFilePath + imgUrl;
            },
            getImgHeight: function (imgh) {
                return imgh != 0 ? imgh : this.imgw;
            },
        }
    }
</script>

