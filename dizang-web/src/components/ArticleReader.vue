<template>
  <div class="article-reader" :class="{ dark: isDark }">
    <div class="toolbar">
      <span class="font-btn" @click="changeFontSize(-1)">A-</span>
      <span class="font-btn" @click="changeFontSize(1)">A+</span>
      <span class="font-btn" @click="toggleDark">{{ isDark ? '☀' : '☾' }}</span>
    </div>
    <div class="content" :style="{ fontSize: fontSize + 'px' }" v-html="safeContent"></div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import DOMPurify from 'dompurify'

const props = defineProps<{ content: string }>()

const fontSize = ref(Number(localStorage.getItem('readerFontSize') || 16))
const isDark = ref(localStorage.getItem('readerDark') === '1')

const safeContent = computed(() => DOMPurify.sanitize(props.content || ''))

const changeFontSize = (delta: number) => {
  fontSize.value = Math.max(12, Math.min(24, fontSize.value + delta))
  localStorage.setItem('readerFontSize', String(fontSize.value))
}

const toggleDark = () => {
  isDark.value = !isDark.value
  localStorage.setItem('readerDark', isDark.value ? '1' : '0')
}
</script>

<style lang="scss" scoped>
.article-reader {
  &.dark {
    background: #1a1a1a;
    color: #ccc;
    .toolbar { background: #2a2a2a; }
  }

  .toolbar {
    display: flex;
    justify-content: flex-end;
    gap: 16px;
    padding: 8px 16px;
    background: #f5f0e8;
    border-radius: $border-radius;
    margin-bottom: 16px;

    .font-btn {
      cursor: pointer;
      padding: 4px 8px;
      color: $primary-color;
      font-size: 14px;
    }
  }

  .content {
    line-height: 1.8;
    word-break: break-word;

    :deep(p) { margin-bottom: 12px; }
    :deep(h2), :deep(h3) { margin: 20px 0 12px; color: $text-color; }
    :deep(img) { max-width: 100%; border-radius: $border-radius; }
  }
}
</style>
