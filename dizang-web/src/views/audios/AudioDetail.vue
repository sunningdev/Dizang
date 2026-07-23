<template>
  <div class="page">
    <van-nav-bar :title="audio?.title || '梵音'" left-arrow @click-left="$router.back()" />
    <div class="player" v-if="audio">
      <img :src="audio.coverUrl || '/default-audio.png'" class="cover" />
      <h2>{{ audio.title }}</h2>
      <p class="desc">{{ audio.description }}</p>
      <audio ref="audioRef" :src="audio.fileUrl" controls autoplay class="audio-ctrl" @play="onPlay" />
      <p class="meta">{{ formatDuration(audio.duration) }} · {{ audio.playCount }} 次播放</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { NavBar as VanNavBar } from 'vant'
import { audioApi } from '@/api'
import type { Audio } from '@/types'

const route = useRoute()
const audio = ref<Audio>()
const audioRef = ref<HTMLAudioElement>()
const played = ref(false)

const formatDuration = (s: number) => {
  const m = Math.floor(s / 60)
  const sec = s % 60
  return `${m}:${sec.toString().padStart(2, '0')}`
}

const onPlay = async () => {
  if (!played.value && audio.value) {
    played.value = true
    await audioApi.play(audio.value.id)
  }
}

onMounted(async () => {
  audio.value = await audioApi.getDetail(Number(route.params.id))
})
</script>

<style lang="scss" scoped>
.page { min-height: 100vh; background: $bg-color; }
.player {
  padding: 32px 24px; text-align: center;
  .cover { width: 200px; height: 200px; border-radius: 12px; object-fit: cover; margin-bottom: 24px; box-shadow: $shadow-lg; }
  h2 { font-size: 18px; color: $text-color; margin-bottom: 8px; }
  .desc { font-size: 13px; color: $text-light; margin-bottom: 24px; line-height: 1.6; }
  .audio-ctrl { width: 100%; margin-bottom: 12px; }
  .meta { font-size: 12px; color: $secondary-color; }
}
</style>
