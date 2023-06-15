<template>

</template>

<script>

import {computed} from 'vue'
import {useRoute} from 'vue-router'

const router = useRoute()
let list = computed(() => {
  return router.matched
})

</script>

<style>

</style>
