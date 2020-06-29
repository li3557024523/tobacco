import request from '@/utils/request'
import { date } from 'jszip/lib/defaults'


// 根据用户对象进行查询用户
export function list(data) {
  return request({
    url: 'Point/list',
    method: 'post',
    data
  })
}
