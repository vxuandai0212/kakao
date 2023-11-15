import request from '@/util/request'
import type {
  GetAllReplyRequest,
  RegisterReplyRequest,
} from '@/types/request'
import { SOCIAL_SERVICE } from '@/constants'

export function getAllReply(params: GetAllReplyRequest) {
  return request({
    url: '/' + SOCIAL_SERVICE + '/replies/get-all',
    method: 'GET',
    params,
  })
}

export function registerReply(data: RegisterReplyRequest) {
  return request({
    url: '/' + SOCIAL_SERVICE + '/replies',
    method: 'POST',
    data,
  })
}

export function deleteReply(id: number) {
  return '/' + SOCIAL_SERVICE + request({
    url: `/replies/${id}`,
    method: 'DELETE',
  })
}
