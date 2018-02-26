<template>
    <div class="div" @click="onClick">
        <text class="icon"
              :style="{ color: icon_color, fontSize: font(icon_size), marginLeft: padding_left, marginRight: padding_right }">
            {{getIconID}}
        </text>
    </div>
</template>
<style scoped="">
    .div {
        align-items: center;
        flex-direction: column;
    }

    .icon {
        font-family: iconfont;
        flex: 1;
        text-align: center;
    }
</style>
<script>
    var he = require('he');
    import mtd from '../../js/methods.js'

    module.exports = {
        props: {
            icon_id: {
                default: "&#xe61b;"
            },
            icon_size: {
                default: 46
            },
            icon_color: {
                default: "#fff"
            },
            padding_left: {
                default: 0
            },
            padding_right: {
                default: 0
            }
        },
        computed: {
            getIconID: function () {
                var code = he.decode(this.icon_id);
                return code;
            }
        },
        created: function () {
            var domModule = weex.requireModule('dom');
            domModule.addRule('fontFace', {
                'fontFamily': "iconfont",
                'src': "url('//at.alicdn.com/t/font_257146_zovjzck54wr35wmi.ttf')"
            });
        },
        methods: {
            onClick: function () {
                this.$emit('onClick');
            },
            font: function (size) {
                return mtd.getFontSize(size);
            }
        }
    };</script>