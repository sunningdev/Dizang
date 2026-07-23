<template>
  <div class="page">
    <van-nav-bar :title="detail?.title || '经典详情'" left-arrow @click-left="$router.back()" />
    <div class="desc" v-if="detail">{{ detail.description }}</div>
    <van-cell-group inset>
      <van-cell
        v-for="ch in detail?.chapters"
        :key="ch.id"
        :title="ch.title"
        is-link
        @click="$router.push(`/classics/${id}/${ch.id}`)"
      />
    </van-cell-group>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { NavBar as VanNavBar, CellGroup as VanCellGroup, Cell as VanCell } from 'vant'
import { classicApi } from '@/api'
import type { Classic, ClassicChapter } from '@/types'

const route = useRoute()
const id = Number(route.params.id)
const detail = ref<Classic & { chapters: ClassicChapter[] }>()

onMounted(async () => {
  detail.value = await classicApi.getDetail(id)
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.desc { padding: 16px; font-size: 14px; color: $text-light; line-height: 1.6; }
</style>
