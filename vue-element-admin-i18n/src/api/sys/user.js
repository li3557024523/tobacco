import request from '@/utils/request'
import { date } from 'jszip/lib/defaults'
// 添加用户
export function add(data) {
  return request({
    url: '/emp/add',
    method: 'post',
    params: {
      age: data.age,
      education: data.education,
      roleId: data.roleId,
      name: data.name,
      password: data.password,
      phone: data.phone,
      sex: data.sex,
      state: data.state,
      username: data.username,
      politics:data.politics
    }
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
    params: {
      username: data.username,
      page: data.page,
      limit: data.limit
    }
  })
}

// 根据用户对象进行查询用户
export function findroleList(data) {
  return request({
    url: '/emp/findroleList',
    method: 'post',
    data
  })
}