import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/api/visit/list',
    method: 'get',
    params: query
  })
}
