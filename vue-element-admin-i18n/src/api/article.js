import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-element-admin/article/list',
    method: 'get',
    params: query
  })
}

export function fetchListUser(query) {
  return request({
    url: 'user/list',
    method: 'get',
    params: query
  })
}
export function fetchListEdu(query) {
  return request({
    url: 'education/listType',
    method: 'get',
    params: query
  })
}
export function fetchArticle(id) {
  return request({
    url: '/vue-element-admin/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-element-admin/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createArticle(data) {
  return request({
    url: 'education/ins',
    method: 'post',
    data
  })
}

export function updateArticle(data) {
  return request({
    url: 'education/upd',
    method: 'post',
    data
  })
}
