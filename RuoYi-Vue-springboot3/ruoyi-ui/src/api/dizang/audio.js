import request from '@/utils/request'

export function list(query) { return request({ url: '/dizang/audio/list', method: 'get', params: query }) }
export function getInfo(id) { return request({ url: '/dizang/audio/' + id, method: 'get' }) }
export function add(data) { return request({ url: '/dizang/audio', method: 'post', data }) }
export function update(data) { return request({ url: '/dizang/audio', method: 'put', data }) }
export function del(id) { return request({ url: '/dizang/audio/' + id, method: 'delete' }) }