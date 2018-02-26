import Dongzhi from '../views/dongzhi.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Dongzhi.el = '#root';

new Vue(Dongzhi);