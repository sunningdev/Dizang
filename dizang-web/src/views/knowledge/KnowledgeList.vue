<template>
  <div class="page">
    <van-nav-bar title="佛教知识" left-arrow @click-left="$router.back()" />
    <van-tabs v-model:active="categoryId" @change="onTabChange">
      <van-tab title="全部" :name="0" />
      <van-tab v-for="c in categories" :key="c.id" :title="c.name" :name="c.id" />
    </van-tabs>
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list v-model:loading="loading" :finished="finished" finished-text="没有更多了" @load="loadList">
        <div class="list-item" v-for="item in list" :key="item.id" @click="$router.push(`/knowledge/${item.id}`)">
          <h4>{{ item.title }}</h4>
          <p>{{ item.summary }}</p>
          <span class="tag">{{ item.categoryName }}</span>
        </div>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { NavBar as VanNavBar, Tabs as VanTabs, Tab as VanTab, PullRefresh as VanPullRefresh, List as VanList } from 'vant'
import { knowledgeApi } from '@/api'
import type { Knowledge, KnowledgeCategory } from '@/types'

const categories = ref<KnowledgeCategory[]>([])
const categoryId = ref(0)
const list = ref<Knowledge[]>([])
const loading = ref(false)
const finished = ref(false)
const refreshing = ref(false)
const page = ref(1)

const loadList = async () => {
  if (refreshing.value) { list.value = []; page.value = 1; finished.value = false }
  loading.value = true
  try {
    const res = await knowledgeApi.getList({
      categoryId: categoryId.value || undefined,
      page: page.value,
      size: 10
    })
    list.value.push(...res.records)
    if (list.value.length >= res.total) finished.value = true
    else page.value++
  } finally { loading.value = false; refreshing.value = false }
}

const onTabChange = () => { list.value = []; page.value = 1; finished.value = false; loadList() }
const onRefresh = () => { page.value = 1; finished.value = false; loadList() }

onMounted(async () => { categories.value = await knowledgeApi.getCategories() })
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.list-item {
  margin: 12px 16px; padding: 14px; background: #fff;
  border-radius: $border-radius; box-shadow: $shadow-sm; cursor: pointer;
  h4 { font-size: 15px; margin-bottom: 8px; color: $text-color; }
  p { font-size: 13px; color: $text-light; line-height: 1.5; }
  .tag { display: inline-block; margin-top: 8px; padding: 2px 8px; font-size: 11px; color: $primary-color; background: rgba(200,16,46,0.08); border-radius: 4px; }
}
</style>
