import Autumn from '../views/mid-autumn-festival.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Autumn.el = '#root';

new Vue(Autumn);