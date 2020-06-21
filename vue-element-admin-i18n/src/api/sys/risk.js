import request from '@/utils/request'
import { date } from 'jszip/lib/defaults'

// 添加用户
export function add(data) {
  return request({
    url: 'risk/add',
    method: 'post',
    data
  })
}

// 根据id删除用户
export function deleteRisk(id) {
  return request({
    url: 'risk/delete',
    method: 'get',
    params: { id }
  })
}

// 根据用户对象进行查询用户
export function list(data) {
  return request({
    url: 'risk/list',
    method: 'post',
    data
  })
}
