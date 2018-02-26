import YierYisan from '../views/yieryisan.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

YierYisan.el = '#root';

new Vue(YierYisan);