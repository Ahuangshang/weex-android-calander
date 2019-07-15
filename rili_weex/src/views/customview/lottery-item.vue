<!--彩票的每个球-->
<template>
    <div class="ssq" :style="{width:font(w),height:font(w),borderRadius:font(w/2),backgroundColor:bg}">
        <text style="color: white" :style="{fontSize:font(14)}"
              :value="value"></text>
    </div>
</template>

<script>
    import mtd from '../../js/methods.js'

    export default {
        name: "lottery-item",
        props: {
            value: {
                default: ''
            },
            bg: {
                default: '#f45531'
            },
            w: {
                default: 30
            }
        },
        methods: {
            font: function (size) {
                return mtd.getFontSize(size);
            },
        }
    }
</script>

<style scoped="">
    .ssq {
        border-radius: 25px;
        width: 50px;
        height: 50px;
        justify-content: center;
        align-items: center;
    }
</style>
