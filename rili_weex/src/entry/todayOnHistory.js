import Today from '../views/todayOnHistory.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Today.el = '#root';

new Vue(Today);