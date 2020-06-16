import request from '@/utils/request'
// 添加用户
export function add(data) {
  return request({
    url: '/emp/add',
    method: 'post',
    data
  })
}

// 根据id删除用户
export function deleteUser(id) {
  return request({
    url: '/emp/delete',
    method: 'get',
    params: { id }
  })
}

// 修改用户
export function update(data) {
  return request({
    url: '/emp/update',
    method: 'post',
    data
  })
}

// 根据用户对象进行查询用户
export function list(data) {
  return request({
    url: '/emp/list',
    method: 'post',
    data
  })
}