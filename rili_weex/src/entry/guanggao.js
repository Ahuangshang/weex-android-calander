import Ad from '../views/guanggao.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Ad.el = '#root';

new Vue(Ad);