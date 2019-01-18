<template>
  <div>
    <text value="我加载出来了"></text>
  </div>
</template>

<script>
  const modal = weex.requireModule('modal');
  export default {
    props: {
      otherLayoutInfo: {
        default: function () {
          return {}
        }
      }
    },
    created: function () {
      this.getOptions();
    },
    methods: {
      getOptions: function () {
        this.otherLayoutInfo = this.$getConfig().otherLayoutInfo;
        modal.alert({message: '****' + JSON.stringify(this.otherLayoutInfo)});
        //this.$getConfig().otherLayoutInfo;
        console.log({message: '****' + this.$getConfig().otherLayoutInfo})
      },
    }
  }
</script>

<style scoped>

</style>