<template>
  <div class="page">
    <van-nav-bar title="大德开示" left-arrow @click-left="$router.back()" />
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="loadList">
        <div class="list-item" v-for="item in list" :key="item.id" @click="$router.push(`/teachings/${item.id}`)">
          <img v-if="item.coverUrl" :src="item.coverUrl" class="cover" />
          <div class="info">
            <h4>{{ item.title }}</h4>
            <p>{{ item.summary }}</p>
            <span class="meta">{{ item.masterName }} · {{ item.publishTime?.substring(0, 10) }}</span>
          </div>
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { NavBar as VanNavBar, PullRefresh as VanPullRefresh, List as VanList } from 'vant'
import { teachingApi } from '@/api'
import type { Teaching } from '@/types'

const list = ref<Teaching[]>([])
const loading = ref(false)
const finished = ref(false)
const refreshing = ref(false)
const page = ref(1)

const loadList = async () => {
  if (refreshing.value) { list.value = []; page.value = 1; finished.value = false }
  loading.value = true
  try {
    const res = await teachingApi.getList({ page: page.value, size: 10 })
    list.value.push(...res.records)
    if (list.value.length >= res.total) finished.value = true
    else page.value++
  } finally { loading.value = false; refreshing.value = false }
}

const onRefresh = () => { page.value = 1; finished.value = false; loadList() }
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.list-item {
  display: flex; gap: 12px; margin: 12px 16px; padding: 12px;
  background: #fff; border-radius: $border-radius; box-shadow: $shadow-sm; cursor: pointer;
  .cover { width: 80px; height: 60px; object-fit: cover; border-radius: 4px; }
  .info {
    flex: 1;
    h4 { font-size: 15px; margin-bottom: 6px; color: $text-color; }
    p { font-size: 12px; color: $text-light; line-height: 1.5; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; }
    .meta { font-size: 11px; color: $secondary-color; margin-top: 6px; display: block; }
  }
}
</style>
