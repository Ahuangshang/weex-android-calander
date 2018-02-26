<template>
  <div style="background-color: rgba(244,244,244,0.96);position: absolute;top: 0;bottom: 0;left: 0;right: 0">
    <list style="background-color: rgba(244,244,244,0.96)">
      <cell v-for="(item,i) in datas " :key="i" append="tree">
        <item :imgUrl="item.channelImg" :itemName="item.channelName" @onClick="jump(item.url)" imgFilePath="taibiao/" iconSize="80"></item>
      </cell>
    </list>
  </div>
</template>

<script>
  import mtd from '../js/methods.js'
  import videoconfig from '../js/videoconfig.js'

  export default {
    props: {
      datas: videoconfig.videoInfo
    },
    components: {
      item: require('./customview/item.vue'),
    },
    created: function () {
      if (mtd.isweb()) {
        window.temp_this = this;
        mtd.registerModules();
      }
    },
    methods: {
      jump: function (e) {
        weex.requireModule('event').playVideo(e);
      }
    },

  }
</script>

<style scoped>

</style>