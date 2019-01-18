import otherLayout from '../views/otherLayout.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

otherLayout.el = '#root';

new Vue(otherLayout);