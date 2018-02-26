import SpringFestival from '../views/springFestival.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

SpringFestival.el = '#root';

new Vue(SpringFestival);