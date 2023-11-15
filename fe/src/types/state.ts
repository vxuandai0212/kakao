export interface CurrentUser {
  id: number
  name: string
  email: string
  imageUrl: string
  providerId: string
  provider: string
  username: string
}

export interface Reply {
  id: number
  content: string
  createdAt: Date
  userId: number
  name: string
  imageUrl: string
  commentId: number
  provider: string
}

export interface Comment {
  id: number
  content: string
  createdAt: Date
  userId: number
  name: string
  imageUrl: string
  totalReply: number
  provider: string
}
