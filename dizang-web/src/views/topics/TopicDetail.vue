<template>
  <div class="page">
    <van-nav-bar :title="topic?.name || '专题'" left-arrow @click-left="$router.back()" />
    <div class="desc" v-if="topic">{{ topic.description }}</div>
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="loadList">
        <div class="list-item" v-for="item in list" :key="item.id" @click="$router.push(`/articles/${item.id}`)">
          <img v-if="item.coverUrl" :src="item.coverUrl" class="cover" />
          <div class="info">
            <h4>{{ item.title }}</h4>
            <p>{{ item.summary }}</p>
            <span class="time">{{ item.publishTime?.substring(0, 10) }}</span>
          </div>
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { NavBar as VanNavBar, PullRefresh as VanPullRefresh, List as VanList } from 'vant'
import { topicApi } from '@/api'
import type { Topic, Article } from '@/types'

const route = useRoute()
const topicId = Number(route.params.id)
const topic = ref<Topic>()
const list = ref<Article[]>([])
const loading = ref(false)
const finished = ref(false)
const refreshing = ref(false)
const page = ref(1)

const loadList = async () => {
  if (refreshing.value) { list.value = []; page.value = 1; finished.value = false }
  loading.value = true
  try {
    const res = await topicApi.getArticles(topicId, { page: page.value, size: 10 })
    list.value.push(...res.records)
    if (list.value.length >= res.total) finished.value = true
    else page.value++
  } finally { loading.value = false; refreshing.value = false }
}

const onRefresh = () => { page.value = 1; finished.value = false; loadList() }

onMounted(async () => {
  topic.value = await topicApi.getDetail(topicId)
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.desc { padding: 16px; font-size: 14px; color: $text-light; line-height: 1.6; }
.list-item {
  display: flex; gap: 12px; margin: 12px 16px; padding: 12px;
  background: #fff; border-radius: $border-radius; box-shadow: $shadow-sm; cursor: pointer;
  .cover { width: 80px; height: 60px; object-fit: cover; border-radius: 4px; }
  .info {
    flex: 1;
    h4 { font-size: 15px; margin-bottom: 6px; color: $text-color; }
    p { font-size: 12px; color: $text-light; line-height: 1.5; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; }
    .time { font-size: 11px; color: $secondary-color; margin-top: 6px; display: block; }
  }
}
</style>
