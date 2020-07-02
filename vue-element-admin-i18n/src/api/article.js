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


export function L_updateArticle(data) {
  return request({
    url: 'literature/upd',
    method: 'post',
    data
  })
}

export function fetchListLit(query) {
  return request({
    url: 'literature/listType',
    method: 'get',
    params: query
  })
}
export function fetchListDau(query) {
  return request({
    url: 'datum/listTypeBy',
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
export function uploadfile(data) {
  return request({
    url: 'datum/upload',
    method: 'post',
    data
  })
}

export function createArticleDau(data) {
  return request({
    url: 'datum/ins',
    method: 'post',
    data
  })
}
export function createArticleSup(data) {
  return request({
    url: 'Supervision/add',
    method: 'post',
    data
  })
}
export function L_createArticle(data) {
  return request({
    url: 'literature/ins',
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
export function updateArticleD(data) {
  return request({
    url: 'datum/upd',
    method: 'post',
    data
  })
}
export function deleteDatum(id) {
  return request({
    url: 'datum/del',
    method: 'get',
    params: { id }
  })
}
export function deleteLit(id) {
  return request({
    url: 'literature/del',
    method: 'get',
    params: { id }
  })
}
export function asd() {
  return request({
    url: 'Supervision/size',
    method: 'post',

  })
}
