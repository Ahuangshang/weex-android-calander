import Bailu from '../views/bailu.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Bailu.el = '#root';

new Vue(Bailu);