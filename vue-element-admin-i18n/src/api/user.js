import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/emp/login',
    method: 'post',
    data
  })
}
export function del(id) {
  return request({
    url:'education/del',
    method:'get',
    params:{ id }
  })
}

export function getInfo(token) {
  return request({
    url: '/emp/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/emp/logout',
    method: 'post'
  })
}
