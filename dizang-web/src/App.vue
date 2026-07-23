<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'

onMounted(() => {
  // 移动端适配
  const setViewport = () => {
    const width = window.innerWidth
    if (width > 750) {
      document.documentElement.style.fontSize = '50px'
    } else {
      document.documentElement.style.fontSize = width / 7.5 + 'px'
    }
  }
  setViewport()
  window.addEventListener('resize', setViewport)
})
</script>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  width: 100%;
  height: 100%;
  background-color: #faf8f3;
  color: #4a4a4a;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Hiragino Sans GB', sans-serif;
}

#app {
  width: 100%;
  min-height: 100vh;
}

/* PC端居中限宽 */
@media screen and (min-width: 750px) {
  #app {
    max-width: 480px;
    margin: 0 auto;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  }
}
</style>
