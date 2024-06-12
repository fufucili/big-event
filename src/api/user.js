import request from '@/utils/request'

//用户注册
export const userRegisterService = ({ username, password, repassword }) => {
  return request.post('/user/register', { username, password, repassword })
}

//用户登录
export const userLoginService = ({ username, password }) => {
  return request.post('/user/login', { username, password })
}

//注销用户
export const userDeleterService = (id) => {
  return request.put(`/user/deleter/${id}`, id)
}

//获取所有用户
export const userAllUserService = () => {
  return request.get('/user/getAll')
}

//退出登录
export const userLogoutService = (id) => {
  return request.post(`/user/logout/${id}`, id)
}
