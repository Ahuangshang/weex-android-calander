import Message from '../views/message.vue'
import mixins from '../js/mixins.js'

Vue.mixin(mixins);

Message.el = '#root';

new Vue(Message);