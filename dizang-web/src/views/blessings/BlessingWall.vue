<template>
  <div class="page">
    <van-nav-bar title="祈福墙" />
    <div class="submit-bar">
      <van-button type="primary" block round color="#c8102e" @click="showForm = true">写下祈福</van-button>
    </div>
    <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
      <van-list v-model:loading="loading" :finished="finished" finished-text="愿众生离苦得乐" @load="loadList">
        <div class="blessing-card" v-for="item in list" :key="item.id">
          <div class="header">
            <span class="nickname">{{ item.nickname || '匿名善信' }}</span>
            <span class="time">{{ item.createTime?.substring(0, 10) }}</span>
          </div>
          <p class="content">{{ item.content }}</p>
          <div class="footer">
            <span @click="handleLike(item)">🪷 {{ item.likeCount }}</span>
          </div>
        </div>
      </van-list>
    </van-pull-refresh>

    <van-popup v-model:show="showForm" position="bottom" round :style="{ padding: '24px' }">
      <h3 class="form-title">写下您的祈福</h3>
      <van-field v-model="form.nickname" label="昵称" placeholder="匿名善信（可选）" />
      <van-field v-model="form.content" type="textarea" label="祈福语" placeholder="愿..." rows="3" maxlength="200" show-word-limit />
      <van-button type="primary" block round color="#c8102e" :loading="submitting" @click="handleSubmit" style="margin-top:16px">提交祈福</van-button>
    </van-popup>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { showToast } from 'vant'
import { NavBar as VanNavBar, PullRefresh as VanPullRefresh, List as VanList, Button as VanButton, Popup as VanPopup, Field as VanField } from 'vant'
import { blessingApi } from '@/api'
import type { Blessing } from '@/types'

const list = ref<Blessing[]>([])
const loading = ref(false)
const finished = ref(false)
const refreshing = ref(false)
const page = ref(1)
const showForm = ref(false)
const submitting = ref(false)
const form = ref({ nickname: '', content: '' })

const loadList = async () => {
  if (refreshing.value) { list.value = []; page.value = 1; finished.value = false }
  loading.value = true
  try {
    const res = await blessingApi.getList({ page: page.value, size: 10 })
    list.value.push(...res.records)
    if (list.value.length >= res.total) finished.value = true
    else page.value++
  } finally { loading.value = false; refreshing.value = false }
}

const onRefresh = () => { page.value = 1; finished.value = false; loadList() }

const handleSubmit = async () => {
  if (!form.value.content.trim()) { showToast('请填写祈福内容'); return }
  submitting.value = true
  try {
    const res = await blessingApi.submit(form.value)
    showToast(res.message || '祈福已送达，待审核通过后展示')
    showForm.value = false
    form.value = { nickname: '', content: '' }
  } finally { submitting.value = false }
}

const handleLike = async (item: Blessing) => {
  await blessingApi.like(item.id)
  item.likeCount++
  showToast('已点赞')
}
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.submit-bar { padding: 12px 16px; }
.blessing-card {
  margin: 0 16px 12px; padding: 16px; background: #fff;
  border-radius: $border-radius; box-shadow: $shadow-sm;
  border-left: 3px solid $primary-color;
  .header {
    display: flex; justify-content: space-between; margin-bottom: 8px;
    .nickname { font-size: 14px; color: $text-color; font-weight: 500; }
    .time { font-size: 12px; color: $text-light; }
  }
  .content { font-size: 14px; line-height: 1.8; color: $text-color; margin-bottom: 12px; }
  .footer span { font-size: 13px; color: $primary-color; cursor: pointer; }
}
.form-title { text-align: center; margin-bottom: 16px; color: $text-color; }
</style>
