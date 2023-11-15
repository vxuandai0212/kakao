export interface LoginRequest {
  username: string
  password: string
}

export interface RegisterCommentRequest {
  content: string
}

export interface GetAllCommentRequest {
  page: number
  limit: number
}

export interface SearchCommentRequest {
  page: number
  limit: number
  sortDirection: number
  searchKeyword: string
}

export interface GetAllReplyRequest {
  page: number
  limit: number
  commentId?: number | null
}

export interface RegisterReplyRequest {
  content: string
  commentId: number | null
}
