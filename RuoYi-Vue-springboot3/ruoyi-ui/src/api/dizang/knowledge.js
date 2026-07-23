import request from '@/utils/request'

export function list(query) { return request({ url: '/dizang/knowledge/list', method: 'get', params: query }) }
export function getInfo(id) { return request({ url: '/dizang/knowledge/' + id, method: 'get' }) }
export function add(data) { return request({ url: '/dizang/knowledge', method: 'post', data }) }
export function update(data) { return request({ url: '/dizang/knowledge', method: 'put', data }) }
export function del(id) { return request({ url: '/dizang/knowledge/' + id, method: 'delete' }) }