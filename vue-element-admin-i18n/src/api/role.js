import request from '@/utils/request'

export function add(data) {
  console.log(data)
  return request({
    url: '/role/add',
    method: 'post',
    params: {
      id: data.id,
      name: data.name,
      remark: data.remark,
      state: data.state,
      did : data.did,
      menuId:data.s
    }
  })
}

export function deleteRole(id) {
  return request({
    url: '/role/delete',
    method: 'get',
    params: { id }
  })
}

export function update(data) {
  return request({
    url: "role/update",
    method: "post",
    params: {
      id: data.id,
      name: data.name,
      remark: data.remark,
      state: data.state,
      did : data.did
    }
  })
}

export function list(data) {
  return request({
    url: 'role/list',
    method: 'post',
    params: {
      name: data.username,
      page: data.page,
      limit: data.limit
    }
  })
}
