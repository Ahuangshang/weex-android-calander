import More from '../views/more.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

More.el = '#root';

new Vue(More);