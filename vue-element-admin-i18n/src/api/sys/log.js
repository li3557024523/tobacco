import request from '@/utils/request'



export function deletelog(id) {
  return request({
    url: '/log/delete',
    method: 'get',
    params: { id }
  })
}



export function list(data) {
  return request({
    url: 'log/list',
    method: 'post',
    params: {
      name: data.operation,
      page: data.page,
      limit: data.limit
    }
  })
}
