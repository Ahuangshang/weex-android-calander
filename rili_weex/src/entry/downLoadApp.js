import downLoad from '../views/downLoadApp.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

downLoad.el = '#root';

new Vue(downLoad);
