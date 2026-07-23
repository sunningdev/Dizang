<template>
  <div class="page">
    <van-nav-bar :title="detail?.title || '文章详情'" left-arrow @click-left="$router.back()" />
    <div class="article" v-if="detail">
      <h2 class="title">{{ detail.title }}</h2>
      <div class="meta">{{ detail.publishTime?.substring(0, 10) }}</div>
      <ArticleReader :content="detail.content" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { NavBar as VanNavBar } from 'vant'
import ArticleReader from '@/components/ArticleReader.vue'
import { articleApi } from '@/api'
import type { Article } from '@/types'

const route = useRoute()
const detail = ref<Article>()

onMounted(async () => {
  detail.value = await articleApi.getDetail(Number(route.params.id))
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.article { padding: 16px; }
.title { font-size: 18px; margin-bottom: 8px; color: $text-color; line-height: 1.5; }
.meta { font-size: 13px; color: $text-light; margin-bottom: 16px; }
</style>
