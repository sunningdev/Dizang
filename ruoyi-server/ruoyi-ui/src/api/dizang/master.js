import request from '@/utils/request'

export function list(query) { return request({ url: '/dizang/master/list', method: 'get', params: query }) }
export function getInfo(id) { return request({ url: '/dizang/master/' + id, method: 'get' }) }
export function add(data) { return request({ url: '/dizang/master', method: 'post', data }) }
export function update(data) { return request({ url: '/dizang/master', method: 'put', data }) }
export function del(id) { return request({ url: '/dizang/master/' + id, method: 'delete' }) }