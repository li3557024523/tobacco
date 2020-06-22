
// 根据用户对象进行查询用户
export function list(data) {
    return request({
      url: 'Flow/list',
      method: 'post',
      data
    })
 }

 //添加
 export function add(data) {
  return request({
    url: 'Flow/add',
    method: 'post',
    data
  })
}