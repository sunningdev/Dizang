<template>
  <div class="page">
    <van-nav-bar title="专题" left-arrow @click-left="$router.back()" />
    <div class="topic-grid">
      <div class="topic-item" v-for="t in topics" :key="t.id" @click="$router.push(`/topics/${t.id}`)">
        <span class="icon">{{ t.icon || '☸' }}</span>
        <span class="name">{{ t.name }}</span>
        <p class="desc">{{ t.description }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { NavBar as VanNavBar } from 'vant'
import { topicApi } from '@/api'
import type { Topic } from '@/types'

const topics = ref<Topic[]>([])

onMounted(async () => {
  topics.value = await topicApi.getList()
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; padding: 16px; }
.topic-grid {
  display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px;
  .topic-item {
    display: flex; flex-direction: column; align-items: center; gap: 6px;
    padding: 16px 8px; background: #fff; border-radius: $border-radius;
    box-shadow: $shadow-sm; cursor: pointer;
    .icon { font-size: 32px; }
    .name { font-size: 13px; color: $text-color; font-weight: 500; }
    .desc { font-size: 11px; color: $text-light; text-align: center; line-height: 1.4; }
  }
}
</style>
