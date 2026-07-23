import request from '@/utils/request'

export function list(query) { return request({ url: '/dizang/chapter/list', method: 'get', params: query }) }
export function getInfo(id) { return request({ url: '/dizang/chapter/' + id, method: 'get' }) }
export function add(data) { return request({ url: '/dizang/chapter', method: 'post', data }) }
export function update(data) { return request({ url: '/dizang/chapter', method: 'put', data }) }
export function del(id) { return request({ url: '/dizang/chapter/' + id, method: 'delete' }) }