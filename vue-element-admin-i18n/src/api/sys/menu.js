import request from '@/utils/request'


export function add(data) {
  console.log(data)
  return request({
    url: '/menu/add',
    method: 'post',
    params:{
      roleId:data.roleId,
      menuId:data.s
    }
  })
}

export function deleteMenu(id) {
  return request({
    url: '/menu/delete',
    method: 'get',
    params: { id }
  })
}

export function update(data) {
  return request({
    url: "menu/update",
    method: "post",
    params:{
      roleId:data.roleId,
      menuId:data.s
    }
  })
}

export function list(data) {
  return request({
    url: '/menu/list',
    method: 'post',
    params:{
      name:data.name,
      page: data.page,
      limit: data.limit
    } 
  })
}



export function mlist(data) {
  return request({
    url: '/menu/mlist',
    method: 'post',
    data
  })
}
