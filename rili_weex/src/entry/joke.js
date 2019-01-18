import Joke from '../views/joke.vue'
import mixins from '../js/mixins.js'
Vue.mixin(mixins);
Joke.el = '#root';

new Vue(Joke);
