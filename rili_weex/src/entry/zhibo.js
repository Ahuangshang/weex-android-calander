import Zhibo from '../views/zhibo.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Zhibo.el = '#root';

new Vue(Zhibo);