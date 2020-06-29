import request from '@/utils/request'
import { date } from 'jszip/lib/defaults'


// 根据用户对象进行查询用户
export function list(data) {
  return request({
    url: 'flow/list',
    method: 'post',
    params:{
      qyear: data.qyear,
      pgname: data.pgname
    }
  })
}


 //添加
 export function add(data) {
  return request({
    url: 'flow/add',
    method: 'post',
    data
  })
}


// 根据id删除用户
export function deleteFlow(id) {
  return request({
    url: 'flow/delete',
    method: 'get',
    params: { id }
  })
}
