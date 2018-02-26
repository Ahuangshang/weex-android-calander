import ssy from '../views/shuangshiyi.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

ssy.el = '#root';

new Vue(ssy);