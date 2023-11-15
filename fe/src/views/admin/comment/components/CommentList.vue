<script lang="ts">
import { defineComponent } from 'vue'
import UserComment from '@/components/UserComment.vue'
import ShowMoreComments from '@/components/ShowMoreComments.vue'
import WriteComment from '@/views/user/post/components/register-comment/WriteComment.vue'
import { mapState } from 'pinia'
import { useCommentStore } from '@/stores/comment'
import { useReplyStore } from '@/stores/reply'
import { COMMENT_TYPE } from '@/constants'
export default defineComponent({
  components: {
    UserComment,
    ShowMoreComments,
    WriteComment,
  },
  computed: {
    ...mapState(useCommentStore, {
      total: (store) => store.list.total,
      items: (store) => store.list.items,
    }),
    ...mapState(useReplyStore, {
      replyByCommentId: (store) => store.byCommentId,
    }),
  },
  data() {
    return {
      COMMENT_TYPE: COMMENT_TYPE,
      showReply: {} as any,
    }
  },
  methods: {
    async handlePageChange(value: number) {
      const commentStore = useCommentStore()
      commentStore.list.query.page = value
      await commentStore.searchComment()
    },
  },
})
</script>

<template>
  <div class="comment-group scroll-comment-list">
    <div class="comment-container">
      <template v-for="comment in items">
        <user-comment :comment="comment" :type="COMMENT_TYPE.COMMENT" />
        <show-more-comments :show-reply-btn="false" :comment-id="comment.id" :total="comment.totalReply"
          @show-reply-form="showReply[comment.id] = true" :enable-show-more="true" style="margin-left: 70px" />
        <write-comment v-show="showReply[comment.id]" type="REPLY" :comment-id="comment.id"
          @close-reply-form="showReply[comment.id] = false"
          style="margin-left: 52px; margin-right: 52px; margin-bottom: 15px" />
        <template v-for="reply in replyByCommentId[comment.id]?.list?.items">
          <user-comment :comment="reply" :type="COMMENT_TYPE.REPLY" />
        </template>
      </template>
    </div>
    <div class="comment-pagination">
      <span class="total_comment_txt">Total: {{ total }}</span>
      <el-pagination background layout="prev, pager, next" :page-size="10" :total="total"
        @current-change="handlePageChange" />
    </div>
  </div>
</template>

<style lang="scss">
.total_comment_txt {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 19px;
  letter-spacing: 0.1px;
  color: #92929d;
}

.comment-pagination {
  border-top: 1px solid #f1f1f5;
  padding-top: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 20px;
  margin-bottom: 20px;
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  text-align: center;
  letter-spacing: 0.1px;
  color: #92929d;
}
</style>
