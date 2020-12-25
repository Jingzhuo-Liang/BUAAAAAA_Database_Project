import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/api/user/list',
    method: 'get',
    params: query
  })
}

export function fetchCommodityList(query) {
  return request({
    url: '/api/commodity/list',
    method: 'get',
    params: query
  })
}

export function fetchLeaveMessageList(query) {
  return request({
    url: '/api/leavemessage/list',
    method: 'get',
    params: query
  })
}

export function fetchReplyMessageList(query) {
  return request({
    url: '/api/replymessage/list',
    method: 'get',
    params: query
  })
}

export function updateMessage(data) {
  return request({
    url: '/api/leavemessage/update',
    method: 'post',
    data
  })
}

export function updateReplyMessage(data) {
  return request({
    url: '/api/replymessage/update',
    method: 'post',
    data
  })
}

export function updateUser(data) {
  return request({
    // url: '/vue-element-admin/article/update',
    url: '/api/user/update',
    method: 'post',
    data
  })
}

export function updateCommodity(data) {
  return request({
    // url: '/vue-element-admin/article/update',
    url: '/api/commodity/update',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/api/user/delete',
    method: 'post',
    data
  })
}

export function deleteMessage(data) {
  return request({
    url: '/api/leavemessage/delete',
    method: 'post',
    data
  })
}

export function deleteReplyMessage(data) {
  return request({
    url: '/api/replymessage/delete',
    method: 'post',
    data
  })
}

export function deleteCommodity(data) {
  return request({
    url: '/api/commodity/delete',
    method: 'post',
    data
  })
}
