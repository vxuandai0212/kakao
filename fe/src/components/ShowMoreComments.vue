<script lang="ts">
import { defineComponent } from 'vue'
import IconCaretDownSlim from '@/components/icons/IconCaretDownSlim.vue'
import WriteComment from '@/views/user/post/components/register-comment/WriteComment.vue'
import { mapActions } from 'pinia'
import { useReplyStore } from '@/stores/reply'
import { useAuthStore } from '@/stores/auth'
export default defineComponent({
  props: {
    commentId: { type: Number, required: true },
    total: { type: Number, default: 0 },
    enableShowMore: { default: true, type: Boolean },
    showReplyBtn: { default: true, type: Boolean }
  },
  computed: {
    exist(): number {
      const replyStore = useReplyStore()
      let exist = 0
      if (replyStore.byCommentId[this.commentId] && replyStore.byCommentId[this.commentId].list && replyStore.byCommentId[this.commentId].list.items) {
        exist = replyStore.byCommentId[this.commentId].list.items.length
      }
      return exist
    },
    isEnableShowMore(): boolean {
      return this.enableShowMore && this.total != undefined && this.total != null && this.total != 0 && this.exist < this.total
    },
    remain(): number {
      return this.total - this.exist
    },
    showCommentAction(): boolean {
      const authStore = useAuthStore()
      return authStore.authenticated && this.showReplyBtn
    }
  },
  components: {
    IconCaretDownSlim,
    WriteComment
  },
  methods: {
    ...mapActions(useReplyStore, ['loadMore']),
    async showMoreReply() {
      await this.loadMore(this.commentId)
    }
  }
})
</script>

<template>
  <div class="show-more-comments" :class="[isEnableShowMore ? '' : 'jc-fe']">
    <div v-show="isEnableShowMore" @click="showMoreReply"><span>Show {{ remain }} Replies</span><icon-caret-down-slim />
    </div>
    <div v-show="showCommentAction"><span @click="$emit('showReplyForm', 2)">Reply</span></div>
  </div>
</template>

<style>
.show-more-comments {
  cursor: pointer;
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 5px;
  width: 400px;
  justify-content: space-between;
}

.jc-fe {
  justify-content: flex-end;
}

.show-more-comments span {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  letter-spacing: 0.1px;
  color: #44444f;
  margin-right: 10px;
}
</style>
