<template>
  <div class="home">
    <van-swipe class="banner" :autoplay="4000" indicator-color="#c8102e">
      <van-swipe-item v-for="item in homeData?.banners" :key="item.id">
        <img :src="item.imageUrl" :alt="item.title" @click="goLink(item.linkUrl)" />
      </van-swipe-item>
    </van-swipe>

    <div class="modules">
      <div class="module-item" v-for="mod in modules" :key="mod.path" @click="$router.push(mod.path)">
        <div class="icon">{{ mod.icon }}</div>
        <span>{{ mod.name }}</span>
      </div>
    </div>

    <div class="section">
      <div class="section-header">
        <h3>九大专题</h3>
        <span @click="$router.push('/topics')">更多 ›</span>
      </div>
      <div class="topic-grid">
        <div class="topic-item" v-for="t in homeData?.topics" :key="t.id" @click="$router.push(`/topics/${t.id}`)">
          <span class="topic-icon">{{ t.icon || '☸' }}</span>
          <span class="topic-name">{{ t.name }}</span>
        </div>
      </div>
    </div>

    <div class="section" v-if="homeData?.latestTeachings?.length">
      <div class="section-header">
        <h3>最新开示</h3>
        <span @click="$router.push('/teachings')">更多 ›</span>
      </div>
      <div class="card-list">
        <div class="card" v-for="item in homeData.latestTeachings" :key="item.id" @click="$router.push(`/teachings/${item.id}`)">
          <img v-if="item.coverUrl" :src="item.coverUrl" class="card-cover" />
          <div class="card-body">
            <h4>{{ item.title }}</h4>
            <p>{{ item.masterName }} · {{ formatDate(item.publishTime) }}</p>
          </div>
        </div>
      </div>
    </div>

    <div class="search-bar" @click="$router.push('/search')">
      <van-icon name="search" /> 搜索经典、开示、知识...
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Swipe as VanSwipe, SwipeItem as VanSwipeItem, Icon as VanIcon } from 'vant'
import { homeApi } from '@/api'
import type { HomeData } from '@/types'

const router = useRouter()
const homeData = ref<HomeData>()

const modules = [
  { name: '佛学经典', icon: '📖', path: '/classics' },
  { name: '大德开示', icon: '🙏', path: '/teachings' },
  { name: '专题', icon: '📚', path: '/topics' },
  { name: '佛教知识', icon: '💡', path: '/knowledge' },
  { name: '梵音', icon: '🎵', path: '/audios' },
  { name: '祈福墙', icon: '🪷', path: '/blessings' }
]

const formatDate = (d: string) => d ? d.substring(0, 10) : ''
const goLink = (url: string) => { if (url) router.push(url) }

onMounted(async () => {
  try { homeData.value = await homeApi.getHome() } catch { /* empty */ }
})
</script>

<style lang="scss" scoped>
.home {
  padding-bottom: 16px;
}

.banner {
  height: 180px;
  img { width: 100%; height: 180px; object-fit: cover; }
}

.modules {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
  padding: 16px;

  .module-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 6px;
    padding: 12px;
    background: #fff;
    border-radius: $border-radius;
    box-shadow: $shadow-sm;
    cursor: pointer;

    .icon { font-size: 28px; }
    span { font-size: 13px; color: $text-color; }
  }
}

.section {
  padding: 0 16px;
  margin-bottom: 20px;

  .section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;

    h3 { font-size: 16px; color: $text-color; }
    span { font-size: 13px; color: $text-light; cursor: pointer; }
  }
}

.topic-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;

  .topic-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 4px;
    padding: 10px 4px;
    background: #fff;
    border-radius: $border-radius;
    box-shadow: $shadow-sm;
    cursor: pointer;

    .topic-icon { font-size: 22px; }
    .topic-name { font-size: 12px; color: $text-color; text-align: center; }
  }
}

.card-list {
  .card {
    display: flex;
    gap: 12px;
    padding: 12px;
    background: #fff;
    border-radius: $border-radius;
    box-shadow: $shadow-sm;
    margin-bottom: 10px;
    cursor: pointer;

    .card-cover { width: 80px; height: 60px; object-fit: cover; border-radius: 4px; }
    .card-body {
      flex: 1;
      h4 { font-size: 14px; margin-bottom: 6px; color: $text-color; }
      p { font-size: 12px; color: $text-light; }
    }
  }
}

.search-bar {
  margin: 0 16px;
  padding: 10px 16px;
  background: #fff;
  border-radius: 20px;
  color: $text-light;
  font-size: 14px;
  box-shadow: $shadow-sm;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
}
</style>
