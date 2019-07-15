import dragonBoatFestival from '../views/dragonBoatFestival.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

dragonBoatFestival.el = '#root';

new Vue(dragonBoatFestival);
