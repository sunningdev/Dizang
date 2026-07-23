import request from '@/utils/request'

export function list(query) { return request({ url: '/dizang/audioCategory/list', method: 'get', params: query }) }
export function getInfo(id) { return request({ url: '/dizang/audioCategory/' + id, method: 'get' }) }
export function add(data) { return request({ url: '/dizang/audioCategory', method: 'post', data }) }
export function update(data) { return request({ url: '/dizang/audioCategory', method: 'put', data }) }
export function del(id) { return request({ url: '/dizang/audioCategory/' + id, method: 'delete' }) }