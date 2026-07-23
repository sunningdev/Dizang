<template>
  <div class="page">
    <van-nav-bar title="梵音" />
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="loadList">
        <div class="audio-item" v-for="item in list" :key="item.id" @click="$router.push(`/audios/${item.id}`)">
          <img :src="item.coverUrl || '/default-audio.png'" class="cover" />
          <div class="info">
            <h4>{{ item.title }}</h4>
            <p>{{ item.categoryName }} · {{ formatDuration(item.duration) }}</p>
            <span class="play-count">{{ item.playCount }} 次播放</span>
          </div>
          <van-icon name="play-circle-o" size="28" color="#c8102e" />
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { NavBar as VanNavBar, PullRefresh as VanPullRefresh, List as VanList, Icon as VanIcon } from 'vant'
import { audioApi } from '@/api'
import type { Audio } from '@/types'

const list = ref<Audio[]>([])
const loading = ref(false)
const finished = ref(false)
const refreshing = ref(false)
const page = ref(1)

const formatDuration = (s: number) => {
  const m = Math.floor(s / 60)
  const sec = s % 60
  return `${m}:${sec.toString().padStart(2, '0')}`
}

const loadList = async () => {
  if (refreshing.value) { list.value = []; page.value = 1; finished.value = false }
  loading.value = true
  try {
    const res = await audioApi.getList({ page: page.value, size: 10 })
    list.value.push(...res.records)
    if (list.value.length >= res.total) finished.value = true
    else page.value++
  } finally { loading.value = false; refreshing.value = false }
}

const onRefresh = () => { page.value = 1; finished.value = false; loadList() }
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.audio-item {
  display: flex; align-items: center; gap: 12px;
  margin: 12px 16px; padding: 12px; background: #fff;
  border-radius: $border-radius; box-shadow: $shadow-sm; cursor: pointer;
  .cover { width: 56px; height: 56px; border-radius: 8px; object-fit: cover; background: #f0ebe3; }
  .info {
    flex: 1;
    h4 { font-size: 14px; margin-bottom: 4px; color: $text-color; }
    p { font-size: 12px; color: $text-light; }
    .play-count { font-size: 11px; color: $secondary-color; }
  }
}
</style>
