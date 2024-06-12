import request from '@/utils/request'

//获取分类集合
export const artGetChannelService = () => {
  return request.get('/articleCate/list')
}

//新增分类
export const artAddArticleCateService = ({ name, alias }) => {
  return request.post('/articleCate/add', { name, alias })
}

//修改分类
export const artEditArticleCateService = ({ id, name, alias }) => {
  return request.put('/articleCate/edit', { id, name, alias })
}

//删除分类
export const artDeleteArticleCateService = (id) => {
  return request.delete('/articleCate/deleter', {
    params: { id }
  })
}
