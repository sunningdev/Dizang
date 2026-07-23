<template>
  <div class="tab-layout">
    <div class="content">
      <router-view v-slot="{ Component }">
        <keep-alive>
          <component :is="Component" v-if="$route.meta.keepAlive" />
        </keep-alive>
        <component :is="Component" v-if="!$route.meta.keepAlive" />
      </router-view>
    </div>
    
    <van-tabbar v-model="active" route fixed>
      <van-tabbar-item to="/" icon="wap-home-o">首页</van-tabbar-item>
      <van-tabbar-item to="/classics" icon="orders-o">经典</van-tabbar-item>
      <van-tabbar-item to="/audios" icon="music-o">梵音</van-tabbar-item>
      <van-tabbar-item to="/blessings" icon="chat-o">祈福</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'
import { Tabbar as VanTabbar, TabbarItem as VanTabbarItem } from 'vant'

const route = useRoute()
const active = ref(0)

// 根据路由设置激活的 tab
watch(() => route.path, (path) => {
  if (path === '/' || path.startsWith('/topics') || path.startsWith('/teachings') || path.startsWith('/knowledge')) active.value = 0
  else if (path.startsWith('/classics')) active.value = 1
  else if (path.startsWith('/audios')) active.value = 2
  else if (path.startsWith('/blessings')) active.value = 3
}, { immediate: true })
</script>

<style lang="scss" scoped>
.tab-layout {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  
  .content {
    flex: 1;
    overflow-y: auto;
    padding-bottom: 50px;
  }
}

:deep(.van-tabbar) {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
}

:deep(.van-tabbar-item--active) {
  color: #c8102e;
}
</style>
