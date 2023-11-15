import request from '@/util/request'
import type {
  GetAllCommentRequest,
  RegisterCommentRequest,
  SearchCommentRequest,
} from '@/types/request'
import { SOCIAL_SERVICE } from '@/constants'

export function getAllComment(params: GetAllCommentRequest) {
  return request({
    url: '/' + SOCIAL_SERVICE + '/comments/get-all',
    method: 'GET',
    params,
  })
}

export function registerComment(data: RegisterCommentRequest) {
  return request({
    url: '/' + SOCIAL_SERVICE + '/comments',
    method: 'POST',
    data,
  })
}

export function deleteComment(id: number) {
  return request({
    url: '/' + SOCIAL_SERVICE + `/comments/${id}`,
    method: 'DELETE',
  })
}

export function searchComment(params: SearchCommentRequest) {
  return request({
    url: '/' + SOCIAL_SERVICE + '/comments/search',
    method: 'GET',
    params,
  })
}
