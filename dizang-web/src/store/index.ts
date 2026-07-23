import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  // 阅读设置
  const fontSize = ref<number>(16)
  const isDarkMode = ref<boolean>(false)
  
  // 初始化应用
  const initApp = () => {
    // 从 localStorage 读取设置
    const savedFontSize = localStorage.getItem('fontSize')
    const savedDarkMode = localStorage.getItem('darkMode')
    
    if (savedFontSize) {
      fontSize.value = parseInt(savedFontSize)
    }
    if (savedDarkMode) {
      isDarkMode.value = savedDarkMode === 'true'
    }
  }
  
  // 设置字号
  const setFontSize = (size: number) => {
    fontSize.value = size
    localStorage.setItem('fontSize', size.toString())
  }
  
  // 切换深色模式
  const toggleDarkMode = () => {
    isDarkMode.value = !isDarkMode.value
    localStorage.setItem('darkMode', isDarkMode.value.toString())
  }
  
  return {
    fontSize,
    isDarkMode,
    initApp,
    setFontSize,
    toggleDarkMode
  }
})

export const useAudioStore = defineStore('audio', () => {
  const currentAudio = ref<any>(null)
  const isPlaying = ref<boolean>(false)
  const currentTime = ref<number>(0)
  const duration = ref<number>(0)
  
  const playAudio = (audio: any) => {
    currentAudio.value = audio
    isPlaying.value = true
  }
  
  const pauseAudio = () => {
    isPlaying.value = false
  }
  
  const updateProgress = (time: number, total: number) => {
    currentTime.value = time
    duration.value = total
  }
  
  return {
    currentAudio,
    isPlaying,
    currentTime,
    duration,
    playAudio,
    pauseAudio,
    updateProgress
  }
})
