import Weather from '../views/weather.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Weather.el = '#root';

new Vue(Weather);