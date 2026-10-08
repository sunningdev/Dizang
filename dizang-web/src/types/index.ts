export interface PageResult<T> {
  records: T[]
  total: number
  current: number
  size: number
}

export interface Banner {
  id: number
  title: string
  imageUrl: string
  linkUrl: string
  sortOrder: number
}

export interface Classic {
  id: number
  title: string
  description: string
  coverUrl: string
  category: string
}

export interface ClassicChapter {
  id: number
  classicId: number
  parentId: number
  title: string
  content: string
  sortOrder: number
  children?: ClassicChapter[]
}

export interface Master {
  id: number
  name: string
  avatar: string
  bio: string
}

export interface Teaching {
  id: number
  masterId: number
  masterName: string
  title: string
  summary: string
  content: string
  coverUrl: string
  publishTime: string
}

export interface Topic {
  id: number
  name: string
  icon: string
  description: string
  sortOrder: number
}

export interface Article {
  id: number
  topicId: number
  title: string
  summary: string
  content: string
  coverUrl: string
  publishTime: string
}

export interface KnowledgeCategory {
  id: number
  name: string
  sortOrder: number
}

export interface Knowledge {
  id: number
  categoryId: number
  categoryName: string
  title: string
  summary: string
  content: string
  coverUrl: string
}

export interface AudioCategory {
  id: number
  name: string
}

export interface Audio {
  id: number
  categoryId: number
  categoryName: string
  title: string
  description: string
  coverUrl: string
  fileUrl: string
  duration: number
  playCount: number
}

export interface Blessing {
  id: number
  nickname: string
  content: string
  likeCount: number
  createTime: string
}

export interface HomeData {
  banners: Banner[]
  topics: Topic[]
  latestClassics: Classic[]
  latestTeachings: Teaching[]
  latestArticles: Article[]
}

export interface SearchResult {
  classics: Classic[]
  teachings: Teaching[]
  articles: Article[]
  knowledge: Knowledge[]
}
