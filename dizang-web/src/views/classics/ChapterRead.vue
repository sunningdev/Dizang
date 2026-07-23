<template>
  <div class="page">
    <van-nav-bar :title="chapter?.title || '阅读'" left-arrow @click-left="$router.back()">
      <template #right>
        <span @click="goPrev" v-if="chapter?.prevId">上一章</span>
        <span style="margin-left:12px" @click="goNext" v-if="chapter?.nextId">下一章</span>
      </template>
    </van-nav-bar>
    <div class="reader-wrap" v-if="chapter">
      <ArticleReader :content="chapter.content" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { NavBar as VanNavBar } from 'vant'
import ArticleReader from '@/components/ArticleReader.vue'
import { classicApi } from '@/api'
import type { ClassicChapter } from '@/types'

const route = useRoute()
const router = useRouter()
const classicId = Number(route.params.id)
const chapterId = Number(route.params.chapterId)
const chapter = ref<ClassicChapter & { prevId?: number; nextId?: number }>()

const goPrev = () => router.replace(`/classics/${classicId}/${chapter.value!.prevId}`)
const goNext = () => router.replace(`/classics/${classicId}/${chapter.value!.nextId}`)

onMounted(async () => {
  chapter.value = await classicApi.getChapter(classicId, chapterId)
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.reader-wrap { padding: 16px; }
:deep(.van-nav-bar__right) { font-size: 13px; color: $primary-color; cursor: pointer; }
</style>
