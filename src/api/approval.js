import request from '@/utils/request'

//获取审核配置集合
export const appGetApprovalService = () => {
  return request.get('/approvalConfig/list')
}

//获取分类集合
export const appGetCategoryService = () => {
  return request.get('/approvalManager/categoryList')
}

//获取审核人集合
export const appGetReviewerListService = (id) => {
  return request.get('/approvalFlowConfig/reviewerList', {
    params: { id }
  })
}

//修改审批配置
export const appEditApprovalConfigService = ({
  id,
  approvalName,
  submitUser,
  submitUserName,
  reviewerList
}) => {
  return request.put('/approvalFlowConfig/update', {
    id,
    approvalName,
    submitUser,
    submitUserName,
    reviewerList
  })
}

//新增审批配置
export const appAddApprovalConfigService = ({
  id,
  approvalName,
  submitUser,
  submitUserName,
  reviewerList
}) => {
  return request.post('/approvalFlowConfig/add', {
    id,
    approvalName,
    submitUser,
    submitUserName,
    reviewerList
  })
}

//删除审核配置
export const appDeleteApprovalConfigService = (id) => {
  return request.delete(`/approvalFlowConfig/${id}`, id)
}

//查询我的申诉
export const appGetMyApprovalService = () => {
  return request.get('/approvalInfo/getApproval')
}

//查询我的审批列表
export const appGetMyHandleService = () => {
  return request.get('/approvalRecord/pending')
}

//新增审批
export const appAddApprovalService = ({ approvalConfigId, idea, file }) => {
  return request.post('/approvalInfo/addApproval', {
    approvalConfigId,
    idea,
    file
  })
}

//审批
export const appApprovalService = ({ approvalRecordId, opinion, status }) => {
  return request.put('/approvalRecord/approval', {
    approvalRecordId,
    opinion,
    status
  })
}
