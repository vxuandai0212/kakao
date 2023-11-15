import { defineStore } from 'pinia'
import { getAllReply, registerReply, deleteReply } from '@/apis/reply'
import { concat, assign, pick } from 'lodash'
import { useAuthStore } from '@/stores/auth'
import { useCommentStore } from '@/stores/comment'

export const useReplyStore = defineStore('reply', {
  state: () => ({
    byCommentId: {} as any,
  }),
  getters: {},
  actions: {
    async loadMore(commentId: number) {
      this.byCommentId[commentId] = this.byCommentId[commentId]
        ? this.byCommentId[commentId]
        : {}
      this.byCommentId[commentId].list = this.byCommentId[commentId].list
        ? this.byCommentId[commentId].list
        : {}
      this.byCommentId[commentId].list.items = this.byCommentId[commentId].list
        .items
        ? this.byCommentId[commentId].list.items
        : []
      this.byCommentId[commentId].list.query = this.byCommentId[commentId].list
        .query
        ? this.byCommentId[commentId].list.query
        : {}
      this.byCommentId[commentId].list.query.page = this.byCommentId[commentId]
        .list.query.page
        ? this.byCommentId[commentId].list.query.page
        : 0
      const currentPage = this.byCommentId[commentId].list.query.page
      this.byCommentId[commentId].list.query.page = currentPage + 1
      this.byCommentId[commentId].list.query.limit = 10
      const data: any = await this.getAllReply({
        commentId,
        page: currentPage + 1,
        limit: 10,
      })
      const { total, items } = data.data
      const existReplyId = this.byCommentId[commentId].list.items.map((i: any) => i.id)
      items.map((newReply: any) => {
        const newReplyId = newReply.id
        if (!existReplyId.includes(newReplyId)) {
          this.byCommentId[commentId].list.items.push(newReply)
        }
      })
      this.byCommentId[commentId].list.total = total
    },
    getAllReply(query: any) {
      return new Promise((resolve, reject) => {
        getAllReply(query)
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    async handleRegisterReply(form: any) {
      const reply: any = await this.registerReply(form)
      const authStore = useAuthStore()
      const { currentUser } = authStore
      let newReply = assign(
        reply.data,
        pick(currentUser, ['name', 'imageUrl', 'provider'])
      )
      const { commentId } = form
      this.byCommentId[commentId] = this.byCommentId[commentId]
        ? this.byCommentId[commentId]
        : {}
      this.byCommentId[commentId].list = this.byCommentId[commentId].list
        ? this.byCommentId[commentId].list
        : {}
      this.byCommentId[commentId].list.items = this.byCommentId[commentId].list
        .items
        ? this.byCommentId[commentId].list.items
        : []
      this.byCommentId[commentId].list.items.unshift(newReply)
      this.byCommentId[commentId].list.total += 1
      const commentStore = useCommentStore()
      commentStore.list.items = commentStore.list.items.map((item: any) => {
        if (item.id === commentId) {
          item.totalReply += 1
        }
        return item
      })
      this.byCommentId[commentId].list.query.page = 0
    },
    registerReply(form: any) {
      return new Promise((resolve, reject) => {
        registerReply(form)
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    async handleDeleteReply(replyId: number, commentId: number) {
      await this.deleteReply(replyId)
      this.byCommentId[commentId].list.items = this.byCommentId[
        commentId
      ].list.items.filter((i: any) => i.id !== replyId)
      this.byCommentId[commentId].list.total -= 1
      const commentStore = useCommentStore()
      commentStore.list.items = commentStore.list.items.map((item: any) => {
        if (item.id === commentId && item.totalReply > 0) {
          item.totalReply -= 1
        }
        return item
      })
      this.byCommentId[commentId].list.query.page = 0
    },
    deleteReply(id: number) {
      return new Promise((resolve, reject) => {
        deleteReply(id)
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
  },
})
