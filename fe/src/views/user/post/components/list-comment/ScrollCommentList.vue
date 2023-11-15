<script lang="ts">
import { defineComponent } from 'vue'
import UserComment from '@/components/UserComment.vue'
import { mapState, mapActions } from 'pinia'
import { useCommentStore } from '@/stores/comment'
import ShowMoreComments from '@/components/ShowMoreComments.vue'
import WriteComment from '@/views/user/post/components/register-comment/WriteComment.vue'
import { useReplyStore } from '@/stores/reply'
import { useAuthStore } from '@/stores/auth'
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
    ...mapState(useAuthStore, {
      showCommentAction: (store) => store.authenticated,
    }),
    showScrollNext(): boolean {
      return this.items.length < this.total
    },
  },
  data() {
    return {
      showReply: {} as any,
      COMMENT_TYPE: COMMENT_TYPE,
    }
  },
  mounted() {
  },
  methods: {
    ...mapActions(useCommentStore, ['scrollNext']),
    handleNext() {
      this.scrollNext()
    }
  }
})
</script>

<template>
  <div class="comment-group scroll-comment-list">
    <div class="comment-container">
      <template v-for="comment in items">
        <user-comment :comment="comment" :type="COMMENT_TYPE.COMMENT" />
        <show-more-comments :comment-id="comment.id"
          v-show="showCommentAction"
          @show-reply-form="showReply[comment.id] = true"
          :enable-show-more="false"
          style="margin-left: 70px"
        />
        <write-comment
          v-show="showReply[comment.id]"
          type="REPLY"
          :comment-id="comment.id"
          @close-reply-form="showReply[comment.id] = false"
          style="margin-left: 52px; margin-right: 52px; margin-bottom: 15px"
        />
        <template v-for="reply in replyByCommentId[comment.id]?.list?.items">
          <user-comment :comment="reply" :type="COMMENT_TYPE.REPLY" />
        </template>
      </template>
    </div>
    <div class="next-comment-post">
      <span v-show="showScrollNext" @click="handleNext" id="span-next-comment-post">Next</span>
    </div>
  </div>
</template>
