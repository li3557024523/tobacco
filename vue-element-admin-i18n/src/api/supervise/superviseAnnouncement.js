
export function list(query) {
  return request({
    url: 'Supervision/list',
    method: 'get',
    params: query
  })
}
export function deleteZTR() {
  return request({
    url: '/Supervision/del',
    method: 'post'
  })
}
export function updateZTR(data) {
  return request({
    url: 'Supervision/upload',
    method: 'post',
    data
  })
}
export function updateZT(data) {
  return request({
    url: 'Supervision/updateZT',
    method: 'post',
    data
  })
}
export function add(data) {
  console.log(data)
  return request({
    url: '/Supervision/add',
    method: 'post',
    data
  })
}
