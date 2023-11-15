<script lang="ts">
import { defineComponent } from 'vue'
import IconEmoji from '@/components/icons/IconEmoji.vue'
// @ts-expect-error
import EmojiPicker from 'vue3-emoji-picker'
import 'vue3-emoji-picker/css'
import { useCommentStore } from '@/stores/comment'
import { useReplyStore } from '@/stores/reply'
import { mapActions, mapState } from 'pinia'
import { COMMENT_TYPE } from '@/constants'
import { useAuthStore } from '@/stores/auth'
export default defineComponent({
  props: {
    type: { default: COMMENT_TYPE.COMMENT, type: String },
    commentId: { type: Number },
  },
  computed: {
    ...mapState(useAuthStore, {
      avatar: (store) => {
        if (
          store.authenticated &&
          store.currentUser &&
          store.currentUser.imageUrl
        ) {
          return store.currentUser.imageUrl
        }
        return 'https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png'
      },
    }),
  },
  components: {
    IconEmoji,
    EmojiPicker,
  },
  data() {
    return {
      content: '',
      valueShowEmoji: false,
    }
  },
  methods: {
    ...mapActions(useCommentStore, ['handleRegisterComment']),
    ...mapActions(useReplyStore, ['handleRegisterReply']),
    onSelectEmoji(emoji: any) {
      this.content = this.content.concat(emoji.i)
    },
    autoGrow(event: any) {
      const element = event.target
      element.style.height = '5px'
      element.style.height = element.scrollHeight + 'px'
    },
    handleClickOutsideEmojiSelection() {
      this.valueShowEmoji = false
    },
    async _handleRegisterComment() {
      if (this.content && this.content.trim() != '') {
        if (this.type === COMMENT_TYPE.COMMENT) {
          await this.handleRegisterComment({ content: this.content })
          this.content = ''
        } else {
          await this.handleRegisterReply({
            commentId: this.commentId,
            content: this.content,
          })
          this.content = ''
          this.$emit('closeReplyForm')
        }
      }
    },
  },
})
</script>

<template>
  <div class="write-comment">
    <img :src="avatar" />
    <textarea
      maxlength="150"
      @input="autoGrow"
      class="textarea-comment"
      type="text"
      v-model="content"
      placeholder="Write your comment..."
      @keypress.enter="_handleRegisterComment"
    ></textarea>
    <div v-click-outside="handleClickOutsideEmojiSelection">
      <emoji-picker
        v-show="valueShowEmoji"
        class="showEmoji"
        :native="true"
        @select="onSelectEmoji"
      />
      <icon-emoji
        style="position: absolute; bottom: 15; right: 15; cursor: pointer"
        @click="valueShowEmoji = !valueShowEmoji"
      />
    </div>
  </div>
</template>

<style lang="scss">
.textarea-comment {
  height: 46px;
  background: #fafafb;
  border: 1px solid #f1f1f5;
  border-radius: 15px;
  position: relative;
  flex: 1;
  padding: 10px 50px 15px 15px;
}

textarea.textarea-comment {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  letter-spacing: 0.1px;
  color: #44444f;

  resize: none;
  overflow: hidden;
  min-height: 46px;
  max-height: 100px;

  &:focus-visible {
    outline: none;
  }

  &::placeholder {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 24px;
    letter-spacing: 0.1px;
    color: #92929d;
  }
}

.showEmoji {
  position: absolute;
  right: 0;
  bottom: 50px;
}

.write-comment {
  display: flex;
  justify-content: space-between;
  position: relative;

  img {
    border: 1px solid #f1f1f5;
    border-radius: 50%;
    width: 36px;
    height: 36px;
    margin-right: 10px;
    margin-top: 5px;
  }
}

.write-comment .input-write-comment-icon-emoji {
  flex: 1;
  width: 670px;
  height: 46px;
  border-radius: 15px;
  display: flex;
  flex-direction: row;
  align-items: center;
  background: #fafafb;
  border: 1px solid #f1f1f5;
  border-radius: 15px;
}

.write-comment .input-write-comment-icon-emoji textarea {
  background: none;
  outline: none;
  border-radius: 15px;
  width: 600px;
  height: 46px;
  font-size: 14px;
  border: none;
  background: none;
  outline: none;
  padding: 15px;
}

.write-comment .pp1 {
  width: 36px;
  height: 47.25px;
  display: flex;
}

.write-comment .pp1 img {
  width: 36px;
  height: 36px;
  border-radius: 50%;
}

#emoji-click {
  margin-left: 45px;
  margin-top: 5px;
}
</style>
