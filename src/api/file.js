import request from '@/utils/request'

//上传文件前检查
export const fileGetFileService = (fileMd5) => {
  return request.get('/bigFile/upload/checkfile', {
    params: { fileMd5 }
  })
}

//上传分块前检查
export const fileGetChunkService = (fileMd5, chunkIndex) => {
  return request.get('/bigFile/upload/checkchunk', {
    params: { fileMd5, chunkIndex }
  })
}

//上传分块
export const fileUploadChunkService = (file, fileMd5, chunkIndex) => {
  const formData = new FormData()
  formData.append('file', file)
  formData.append('fileMd5', fileMd5)
  formData.append('chunkIndex', chunkIndex)

  return request.post('/bigFile/upload/uploadchunk', formData)
}

//合并分块
export const fileMergeChunkService = ({ fileMd5, fileName, chunkTotal }) => {
  return request.post('/bigFile/upload/mergechunks', {
    fileMd5,
    fileName,
    chunkTotal
  })
}

//查询文件列表
export const fileGetFileListService = () => {
  return request.get('/file/list')
}
