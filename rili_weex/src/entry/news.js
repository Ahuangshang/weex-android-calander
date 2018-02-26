import News from '../views/news.vue'
import mixins from '../js/mixins.js'
Vue.mixin(mixins);
News.el = '#root';

new Vue(News);