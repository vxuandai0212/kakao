<script lang="ts">
import { defineComponent } from 'vue'
import IconTwitter from '@/components/icons/IconTwitter.vue'
import IconFacebook from '@/components/icons/IconFacebook.vue'
import IconGoogle from '@/components/icons/IconGoogle.vue'
import {
  GOOGLE_AUTH_URL,
  TWITTER_AUTH_URL,
  FACEBOOK_AUTH_URL,
} from '@/constants'
export default defineComponent({
  components: {
    IconTwitter,
    IconFacebook,
    IconGoogle,
  },
  methods: {
    loginWithTwitter() {
      window.location.href = TWITTER_AUTH_URL
    },
    loginWithGoogle() {
      window.location.href = GOOGLE_AUTH_URL
    },
    loginWithFacebook() {
      window.location.href = FACEBOOK_AUTH_URL
    },
  },
})
</script>

<template>
  <div class="sign-with">
    <div class="span-login-with">
      <span>Login with</span>
    </div>
    <div class="icons-social-network">
      <div class="icon-sc">
        <IconTwitter @click="loginWithTwitter" />
      </div>
      <div class="icon-sc">
        <IconGoogle @click="loginWithGoogle" />
      </div>
      <div class="icon-sc">
        <IconFacebook @click="loginWithFacebook" />
      </div>
    </div>
  </div>
</template>

<style>
.sign-with {
  width: 231px;
  height: 46px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  justify-content: space-between;
}

.icons-social-network {
  width: 150px;
  height: 46px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.icons-social-network .icon-sc {
  width: 46px;
  height: 46px;
  background: #ffffff;
  border: 1px solid #ececf2;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.span-login-with {
  display: flex;
  justify-content: center;
  align-items: center;
}

.span-login-with span {
  font-family: 'Lato';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 21px;
  /* identical to box height, or 150% */

  color: #8181a5;
}
</style>
