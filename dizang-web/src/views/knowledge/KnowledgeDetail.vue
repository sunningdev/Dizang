<template>
  <div class="page">
    <van-nav-bar :title="detail?.title || '知识详情'" left-arrow @click-left="$router.back()" />
    <div class="article" v-if="detail">
      <div class="meta">{{ detail.categoryName }}</div>
      <ArticleReader :content="detail.content" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { NavBar as VanNavBar } from 'vant'
import ArticleReader from '@/components/ArticleReader.vue'
import { knowledgeApi } from '@/api'
import type { Knowledge } from '@/types'

const route = useRoute()
const detail = ref<Knowledge>()

onMounted(async () => {
  detail.value = await knowledgeApi.getDetail(Number(route.params.id))
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.article { padding: 16px; }
.meta { font-size: 13px; color: $text-light; margin-bottom: 16px; }
</style>
