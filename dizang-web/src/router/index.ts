import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('@/layouts/TabLayout.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页', showTab: true }
      },
      {
        path: 'classics',
        name: 'Classics',
        component: () => import('@/views/classics/ClassicList.vue'),
        meta: { title: '佛学经典', showTab: true }
      },
      {
        path: 'audios',
        name: 'Audios',
        component: () => import('@/views/audios/AudioList.vue'),
        meta: { title: '梵音', showTab: true }
      },
      {
        path: 'blessings',
        name: 'Blessings',
        component: () => import('@/views/blessings/BlessingWall.vue'),
        meta: { title: '祈福墙', showTab: true }
      }
    ]
  },
  {
    path: '/classics/:id',
    name: 'ClassicDetail',
    component: () => import('@/views/classics/ClassicDetail.vue'),
    meta: { title: '经典详情' }
  },
  {
    path: '/classics/:id/:chapterId',
    name: 'ChapterRead',
    component: () => import('@/views/classics/ChapterRead.vue'),
    meta: { title: '阅读' }
  },
  {
    path: '/teachings',
    name: 'Teachings',
    component: () => import('@/views/teachings/TeachingList.vue'),
    meta: { title: '大德开示' }
  },
  {
    path: '/teachings/:id',
    name: 'TeachingDetail',
    component: () => import('@/views/teachings/TeachingDetail.vue'),
    meta: { title: '开示详情' }
  },
  {
    path: '/topics',
    name: 'Topics',
    component: () => import('@/views/topics/TopicList.vue'),
    meta: { title: '专题' }
  },
  {
    path: '/topics/:id',
    name: 'TopicDetail',
    component: () => import('@/views/topics/TopicDetail.vue'),
    meta: { title: '专题详情' }
  },
  {
    path: '/articles/:id',
    name: 'ArticleDetail',
    component: () => import('@/views/articles/ArticleDetail.vue'),
    meta: { title: '文章详情' }
  },
  {
    path: '/knowledge',
    name: 'Knowledge',
    component: () => import('@/views/knowledge/KnowledgeList.vue'),
    meta: { title: '佛教知识' }
  },
  {
    path: '/knowledge/:id',
    name: 'KnowledgeDetail',
    component: () => import('@/views/knowledge/KnowledgeDetail.vue'),
    meta: { title: '知识详情' }
  },
  {
    path: '/audios/:id',
    name: 'AudioDetail',
    component: () => import('@/views/audios/AudioDetail.vue'),
    meta: { title: '梵音播放' }
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import('@/views/Search.vue'),
    meta: { title: '搜索' }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = (to.meta.title as string) || '学佛网'
  next()
})

export default router
