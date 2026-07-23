import request from '@/utils/request'

export function listBlessing(query) { return request({ url: '/dizang/blessing/list', method: 'get', params: query }) }
export function getBlessing(id) { return request({ url: '/dizang/blessing/' + id, method: 'get' }) }
export function delBlessing(id) { return request({ url: '/dizang/blessing/' + id, method: 'delete' }) }
export function approveBlessing(id) { return request({ url: '/dizang/blessing/approve/' + id, method: 'put' }) }
export function rejectBlessing(data) { return request({ url: '/dizang/blessing/reject', method: 'put', data }) }