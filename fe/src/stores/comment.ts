import { defineStore } from 'pinia'
import {
  getAllComment,
  registerComment,
  deleteComment,
  searchComment,
} from '@/apis/comment'
import { pick } from 'lodash'
import { COMMENT_DISPLAY_TYPE } from '@/constants'
import { concat } from 'lodash'
import { useReplyStore } from '@/stores/reply'

const DEFAULT_FORM = {
  content: '',
}

const DEFAULT_DETAIL_ITEM = {
  id: null,
  provider: null,
  imageUrl: null,
  name: null,
  createdAt: null,
  content: '',
  totalReply: 0,
}

const DEFAULT_LIST_QUERY = {
  page: 1,
  limit: 10,
  searchKeyword: '',
  sortDirection: 1,
}

export const useCommentStore = defineStore('comment', {
  state: () => ({
    list: {
      query: Object.assign({}, DEFAULT_LIST_QUERY),
      total: 0,
      items: [] as any,
    },
    form: Object.assign({}, DEFAULT_FORM),
    detail: {
      item: Object.assign({}, DEFAULT_DETAIL_ITEM),
    },
    ui: {
      containerLoading: true,
      listLoading: false,
      commandLoading: false,
      visibleFormDialog: false,
      visibleDeleteDialog: false,
      displayType: COMMENT_DISPLAY_TYPE.SCROLL
    },
  }),
  getters: {},
  actions: {
    async scrollGetInit() {
      this.list.query.page = 1
      this.list.query.limit = 10
      const data: any = await this.getAllComment()
      const { total, items } = data.data
      const replyStore = useReplyStore()
      items.map(async(item: any) => {
        const replies: any = await replyStore.getAllReply({ page: 1, limit: 999999, commentId: item.id })
        replyStore.byCommentId[item.id] = {}
        replyStore.byCommentId[item.id].list = {}
        replyStore.byCommentId[item.id].list.items = replies.data.items
        replyStore.byCommentId[item.id].list.total = replies.data.total
      })
      this.list.items = items
      this.list.total = total
    },
    async scrollNext() {
      const currentPage = this.list.query.page 
      this.list.query.page = currentPage + 1
      const data: any = await this.getAllComment()
      const { total, items } = data.data
      const replyStore = useReplyStore()
      items.map(async(item: any) => {
        const replies: any = await replyStore.getAllReply({ page: 1, limit: 999999, commentId: item.id })
        replyStore.byCommentId[item.id] = {}
        replyStore.byCommentId[item.id].list = {}
        replyStore.byCommentId[item.id].list.items = replies.data.items
        replyStore.byCommentId[item.id].list.total = replies.data.total
      })
      const currentItems = this.list.items
      this.list.items = concat(currentItems, items)
      this.list.total = total
    },
    async noticeGetInit() {
      this.list.query.page = 1
      this.list.query.limit = 10
      const data: any = await this.getAllComment()
      const { total, items } = data.data
      this.list.items = items
      this.list.total = total
    },
    async noticePageChange(page: number) {
      this.list.query.page = page
      this.list.query.limit = 10
      const data: any = await this.getAllComment()
      const { total, items } = data.data
      this.list.items = items
      this.list.total = total
    },
    getAllComment() {
      return new Promise((resolve, reject) => {
        getAllComment(pick(this.list.query, ['page', 'limit']))
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    async handleRegisterComment(form: any) {
      await registerComment(form)
      if (this.ui.displayType === COMMENT_DISPLAY_TYPE.SCROLL) {
        await this.scrollGetInit()
      } else {
        await this.noticeGetInit()
      }
    },
    registerComment(form: any) {
      return new Promise((resolve, reject) => {
        registerComment(form)
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    async handleDeleteComment(id: number) {
      await deleteComment(id)
      if (this.ui.displayType === COMMENT_DISPLAY_TYPE.SCROLL) {
        this.list.items = this.list.items.filter((i: any) => i.id !== id)
        this.list.total -= 1
        const replyStore = useReplyStore()
        delete replyStore.byCommentId[id]
      } else {
        const total = this.list.total - 1
        const { page, limit } = this.list.query
        const maxPage = Math.ceil(total / limit)
        const currentPage = page > maxPage ? maxPage : page
        await this.noticePageChange(currentPage)
      }
    },
    deleteComment(id: number) {
      return new Promise((resolve, reject) => {
        deleteComment(id)
          .then((response) => {
            const { data } = response
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    searchComment() {
      return new Promise((resolve, reject) => {
        searchComment(this.list.query)
          .then((response) => {
            const { data } = response
            const { total, items } = data.data
            this.list.items = items
            this.list.total = total
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
  },
})
