<script lang="ts">
import { defineComponent } from 'vue'
import IconFacebookBlue from '@/components/icons/IconFacebookBlue.vue'
import IconGoogleColor from '@/components/icons/IconGoogleColor.vue'
import IconTwitterColor from '@/components/icons/IconTwitter.vue'
import IconOptionPostStatus from '@/components/icons/IconOptionPostStatus.vue'
import { COMMENT_TYPE } from '@/constants'
import { mapActions, mapState } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import { PROVIDER_TYPE } from '@/constants/index'
import type { Reply, Comment } from '@/types/state'
import type { PropType } from 'vue'
import momment from 'moment'
import { useCommentStore } from '@/stores/comment'
import { useReplyStore } from '@/stores/reply'
import { ElNotification } from 'element-plus'

export default defineComponent({
  props: {
    type: { default: COMMENT_TYPE.COMMENT, type: String },
    comment: { required: true, type: Object as PropType<Reply | Comment> },
  },
  computed: {
    ...mapState(useAuthStore, ['authenticated', 'currentUser', 'isAdmin']),
    repliable(): boolean {
      return this.type === COMMENT_TYPE.COMMENT
    },
    showCommentOptionComp(): boolean {
      return (this.comment.userId === this.currentUser?.id || this.isAdmin) && this.authenticated
    },
    formattedCreatedAt(): any {
      return momment(this.comment.createdAt).format('DD MMMM [at] hh.mm A') //12 April at 09.28 PM
    },
  },
  components: {
    IconFacebookBlue,
    IconOptionPostStatus,
    IconGoogleColor,
    IconTwitterColor,
  },
  data() {
    return {
      showCommentOption: false,
      COMMENT_TYPE: COMMENT_TYPE,
      PROVIDER_TYPE: PROVIDER_TYPE,
      defaultImageUrl: 'https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png'
    }
  },
  methods: {
    ...mapActions(useCommentStore, ['handleDeleteComment']),
    ...mapActions(useReplyStore, ['handleDeleteReply']),
    handleClickOutsideCommentOption() {
      this.showCommentOption = false
    },
    async deleteComment() {
      if (this.type === COMMENT_TYPE.COMMENT) {
        await this.handleDeleteComment(this.comment.id)
        ElNotification({
          title: 'Success',
          message: 'Delete comment successfully',
          type: 'success',
        })
      } else {
        await this.handleDeleteReply(
          this.comment.id,
          (this.comment as Reply).commentId
        )
        ElNotification({
          title: 'Success',
          message: 'Delete reply successfully',
          type: 'success',
        })
      }
    },
  },
})
</script>

<template>
  <div class="comment-group">
    <div
      class="user-comment"
      :class="{ 'user-reply': type === COMMENT_TYPE.REPLY }"
    >
      <div class="img-user-comment">
        <img :src="comment.imageUrl ? comment.imageUrl : defaultImageUrl" :alt="comment.name" />
      </div>
      <div class="content-user-comment">
        <div class="icon-span-name-user">
          <div id="icon-fb-username">
            <icon-facebook-blue
              v-if="PROVIDER_TYPE.FACEBOOK === comment.provider"
            />
            <icon-google-color
              v-else-if="PROVIDER_TYPE.GOOGLE === comment.provider"
            />
            <icon-twitter-color
              v-else-if="PROVIDER_TYPE.TWITTER === comment.provider"
            />
            <span id="user-comment-span1">{{ comment.name }}</span>
          </div>
          <div id="user-comment-span2">{{ formattedCreatedAt }}</div>
        </div>
        <div>
          <span id="user-comment-span3">{{ comment.content }}</span>
        </div>
      </div>
      <div
        v-show="showCommentOptionComp"
        id="icon-option-user-comment"
        v-click-outside="handleClickOutsideCommentOption"
        @click="showCommentOption = !showCommentOption"
      >
        <IconOptionPostStatus />
        <ul
          v-show="showCommentOption"
          class="select-option-wrap"
          style="left: 0; top: 10px"
        >
          <li @click="deleteComment">Delete</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style>
.content-user-comment {
  width: 415px;
  background: #fafafb;
  border: 1px solid #f1f1f5;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 10px;
}
.comment-group {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.user-comment {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  gap: 10px;
}
.user-reply {
  margin-left: 52px;
}
.img-user-comment {
  width: 38px;
  height: 38px;
}

.img-user-comment img {
  width: 38px;
  height: 38px;
  border-radius: 50%;
}

#user-comment-span1 {
  font-family: 'Poppins';
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 21px;
  /* identical to box height */

  letter-spacing: 0.1px;
  color: #171725;
}

#user-comment-span2 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  margin-bottom: 10px;
  color: #92929d;
}

#user-comment-span3 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  /* identical to box height, or 171% */

  letter-spacing: 0.1px;

  color: #44444f;
}
.user-comment {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.img-user-comment {
  width: 38px;
  height: 38px;
}

.content-user-comment {
  width: 415px;
  background: #fafafb;
  border: 1px solid #f1f1f5;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 10px;
}

.img-user-comment img {
  width: 38px;
  height: 38px;
  border-radius: 50%;
}

#icon-fb-username {
  display: flex;
  flex-direction: row;
  gap: 8px;
  align-items: center;
  margin-bottom: 4px;
}

#user-comment-span2 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  /* identical to box height */

  color: #92929d;
}

#user-comment-span3 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  /* identical to box height, or 171% */

  letter-spacing: 0.1px;

  color: #44444f;
}

#icon-option-user-comment {
  margin-left: 20px;
  align-self: center;
  position: relative;
  cursor: pointer;
}
</style>
