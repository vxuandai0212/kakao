<script lang="ts">
import { defineComponent } from 'vue'
import PostStatus from '@/components/PostStatus.vue'
import TotalComments from '@/components/TotalComments.vue'
import ScrollNotice from '@/components/ScrollNotice.vue'
import WriteComment from '@/views/user/post/components/register-comment/WriteComment.vue'
import LoginToComment from '@/components/LoginToComment.vue'
import { COMMENT_DISPLAY_TYPE } from '@/constants'
import NoticeCommentList from '@/views/user/post/components/list-comment/NoticeCommentList.vue'
import ScrollCommentList from '@/views/user/post/components/list-comment/ScrollCommentList.vue'
import { mapState, mapActions } from 'pinia'
import { useCommentStore } from '@/stores/comment'
import { useAuthStore } from '@/stores/auth'
import { useReplyStore } from '@/stores/reply'

export default defineComponent({
  components: {
    PostStatus,
    TotalComments,
    ScrollNotice,
    WriteComment,
    LoginToComment,
    NoticeCommentList,
    ScrollCommentList,
  },
  methods: {
    ...mapActions(useCommentStore, ['getAllComment']),
    ...mapActions(useReplyStore, ['getAllReply']),
  },
  computed: {
    ...mapState(useCommentStore, {
      commentListComponent(store) {
        return store.ui.displayType === COMMENT_DISPLAY_TYPE.NOTICE
          ? 'NoticeCommentList'
          : 'ScrollCommentList'
      },
      total: store => store.list.total
    }),
    ...mapState(useAuthStore, {
      registerCommentComponent(store) {
        return store.authenticated ? 'WriteComment' : 'LoginToComment'
      },
    }),
  },
  data() {
    return {
    }
  },
  async created() {
    const commentStore = useCommentStore()
    commentStore.ui.displayType = COMMENT_DISPLAY_TYPE.NOTICE
    await commentStore.noticeGetInit()
  }
})
</script>

<template>
  <div class="post-user">
    <div class="post-user-after-login">
      <post-status
        src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
        name="Geralt of Rivia"
        timePost="12 April at 09.28 PM"
        contentPost="I'll carry on killing monsters in the ruins of this world until some monster kills me."
      />
      <div class="select-scroll-notice">
        <total-comments :totalComents="total" />
      </div>
      <div class="select-display-comments">
        <scroll-notice />
      </div>
      <transition name="fade" mode="out-in">
        <component :is="commentListComponent"></component>
      </transition>
      <transition name="fade" mode="out-in">
        <component :is="registerCommentComponent"></component>
      </transition>
    </div>
  </div>
</template>

<style>
html {
  background: #fafafb;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.comment-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-height: 50vh;
  overflow-y: scroll;
}

.post-user {
  margin-top: 40px;
  margin-bottom: 40px;
}

.post-user-after-login {
  width: 755px;
  margin: auto;
  padding: 20px;
  border-radius: 15px;
  background: #ffffff;
}

.reply-comment {
  width: 775px;
  height: 240px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.rep-cmt {
  margin-left: 50px;
  margin-top: 10px;
}

.select-scroll-notice {
  display: flex;
  flex-direction: row;
  width: 757px;
  height: 67px;
}

#span-next-comment-post {
  font-family: 'Poppins';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 21px;
  /* identical to box height */

  letter-spacing: 0.8px;
  text-transform: capitalize;

  color: #0062ff;
  cursor: pointer;
}

.next-comment-post {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.select-display-comments {
  display: flex;
  flex-direction: row-reverse;
}

.page-size {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

#span-total-comments {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 19px;
  /* identical to box height */

  letter-spacing: 0.1px;

  color: #92929d;
}
</style>
