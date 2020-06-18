import request from '@/utils/request'
import { date } from 'jszip/lib/defaults'


//
export function leadership() {
  return request({
    url: '/dept/leadership',
    method: 'get'
  })
}

// 返回分好组的部门集合
export function groupDept() {
  return request({
    url: '/dept/deptlist',
    method: 'get'
  })
}
export function add(data) {
  return request({
    url: '/dept/add',
    method: 'post',
    data
  })
}

export function deleteEmp(id) {
  return request({
    url: '/dept/delete',
    method: 'get',
    params: { id }
  })
}

export function update(data) {
  return request({
    url: '/dept/update',
    method: 'post',
    data:{
      deptName: data.deptName,
      parentId:data.parentId,
      id:data.id,
      leadership:data.leadership,
      principalName:data.principalName,
      state:data.state
    }
  })
}

export function list(data) {
  return request({
    url: '/dept/list',
    method: 'post',
    params:{
      name:data.name,
      page: data.page,
      limit: data.limit
    } 
  })
}
