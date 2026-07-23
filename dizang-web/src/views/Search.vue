<template>
  <div class="page">
    <van-nav-bar title="搜索" left-arrow @click-left="$router.back()" />
    <div class="search-wrap">
      <van-search
        v-model="keyword"
        placeholder="搜索经典、开示、知识..."
        @search="doSearch"
        @clear="results = null"
        autofocus
      />
    </div>

    <div class="results" v-if="results">
      <div v-if="!hasResult" class="empty">未找到相关内容</div>

      <div v-if="results.classics?.length" class="group">
        <div class="group-title">佛学经典</div>
        <div class="item" v-for="item in results.classics" :key="item.id"
             @click="$router.push(`/classics/${item.id}`)">{{ item.title }}</div>
      </div>

      <div v-if="results.teachings?.length" class="group">
        <div class="group-title">大德开示</div>
        <div class="item" v-for="item in results.teachings" :key="item.id"
             @click="$router.push(`/teachings/${item.id}`)">{{ item.title }}</div>
      </div>

      <div v-if="results.knowledge?.length" class="group">
        <div class="group-title">佛教知识</div>
        <div class="item" v-for="item in results.knowledge" :key="item.id"
             @click="$router.push(`/knowledge/${item.id}`)">{{ item.title }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { NavBar as VanNavBar, Search as VanSearch } from 'vant'
import { searchApi } from '@/api'
import type { SearchResult } from '@/types'

const keyword = ref('')
const results = ref<SearchResult | null>(null)

const hasResult = computed(() =>
  (results.value?.classics?.length ?? 0) +
  (results.value?.teachings?.length ?? 0) +
  (results.value?.knowledge?.length ?? 0) > 0
)

const doSearch = async () => {
  if (!keyword.value.trim()) return
  results.value = await searchApi.search(keyword.value.trim())
}
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.search-wrap { padding: 8px 0; background: #fff; }
.results { padding: 12px 16px; }
.empty { text-align: center; padding: 40px 0; color: $text-light; }
.group {
  margin-bottom: 16px;
  .group-title { font-size: 13px; color: $secondary-color; margin-bottom: 8px; font-weight: 500; }
  .item {
    padding: 10px 12px; background: #fff; border-radius: $border-radius;
    margin-bottom: 8px; font-size: 14px; color: $text-color; cursor: pointer;
    box-shadow: $shadow-sm;
    &:active { opacity: 0.7; }
  }
}
</style>