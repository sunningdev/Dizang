import { get, post } from '@/utils/request'
import type {
  HomeData, PageResult, Classic, ClassicChapter, Master, Teaching,
  Topic, Article, KnowledgeCategory, Knowledge, Audio, Blessing, SearchResult
} from '@/types'

export const homeApi = {
  getHome: () => get<HomeData>('/home')
}

export const classicApi = {
  getList: (params?: { category?: string; page?: number; size?: number }) =>
    get<PageResult<Classic>>('/classics', { params }),
  getDetail: async (id: number) => {
    const res = await get<{ classic?: Classic; chapters?: ClassicChapter[] } & Classic>(`/classics/${id}`)
    if (res.classic) return { ...res.classic, chapters: res.chapters || [] }
    return res as Classic & { chapters: ClassicChapter[] }
  },
  getChapter: async (classicId: number, chapterId: number) => {
    const res = await get<{ chapter?: ClassicChapter; prevId?: number; nextId?: number } & ClassicChapter>(
      `/classics/${classicId}/chapters/${chapterId}`
    )
    if (res.chapter) return { ...res.chapter, prevId: res.prevId, nextId: res.nextId }
    return res as ClassicChapter & { prevId?: number; nextId?: number }
  }
}

export const teachingApi = {
  getList: (params?: { masterId?: number; page?: number; size?: number }) =>
    get<PageResult<Teaching>>('/teachings', { params }),
  getDetail: (id: number) => get<Teaching>(`/teachings/${id}`),
  getMasters: () => get<Master[]>('/masters')
}

export const topicApi = {
  getList: () => get<Topic[]>('/topics'),
  getDetail: (id: number) => get<Topic>(`/topics/${id}`),
  getArticles: (topicId: number, params?: { page?: number; size?: number }) =>
    get<PageResult<Article>>(`/topics/${topicId}/articles`, { params })
}

export const articleApi = {
  getDetail: (id: number) => get<Article>(`/articles/${id}`)
}

export const knowledgeApi = {
  getCategories: () => get<KnowledgeCategory[]>('/knowledge/categories'),
  getList: (params?: { categoryId?: number; page?: number; size?: number }) =>
    get<PageResult<Knowledge>>('/knowledge', { params }),
  getDetail: (id: number) => get<Knowledge>(`/knowledge/${id}`)
}

export const audioApi = {
  getList: (params?: { categoryId?: number; page?: number; size?: number }) =>
    get<PageResult<Audio>>('/audios', { params }),
  getDetail: (id: number) => get<Audio>(`/audios/${id}`),
  play: (id: number) => post<void>(`/audios/${id}/play`)
}

export const blessingApi = {
  getList: (params?: { page?: number; size?: number }) =>
    get<PageResult<Blessing>>('/blessings', { params }),
  submit: (data: { nickname?: string; content: string }) =>
    post<{ message: string }>('/blessings', data),
  like: (id: number) => post<void>(`/blessings/${id}/like`)
}

export const searchApi = {
  search: (q: string) => get<SearchResult>('/search', { params: { q } })
}
