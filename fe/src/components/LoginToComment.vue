<script lang="ts">
import { defineComponent } from 'vue'
import LoginWithSocial from '@/components/SignWith.vue'
export default defineComponent({
  components: {
    LoginWithSocial,
  },
})
</script>

<template>
  <div class="write-comment">
    <img
      src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
    />
    <textarea
      class="textarea-comment"
      type="text"
      placeholder="You must login to comment..."
      disabled
    ></textarea>
    <div class="login-with-social">
      <LoginWithSocial />
    </div>
  </div>
</template>

<style lang="scss">
.write-comment {
  display: flex;
  flex-direction: row;
}

.span-login-to-comment {
  width: 419px;
  height: 46px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.span-login-to-comment span {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  /* identical to box height, or 171% */

  letter-spacing: 0.1px;

  color: #92929d;
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

.login-with-social {
  display: flex;
  flex-direction: row;
  margin-left: 20px;
  .icon-sc {
    cursor: pointer;
  }
}
</style>
