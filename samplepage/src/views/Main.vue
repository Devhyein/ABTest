<template>
  <div>
    <SampleA />
    <SampleB />
  </div>
</template>

<script>
import SampleA from '@/components/SampleA.vue'
import SampleB from '@/components/SampleB.vue'

export default {
  name: 'Main',
  components: {
    SampleA,
    SampleB,
  }
}
</script>

<style>

</style>