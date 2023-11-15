<script lang="ts">
import CustomInput from '@/components/CustomInput.vue'
import CustomButton from '@/components/CustomButton.vue'
import IconImgLogin from '@/components/icons/IconImgLogin.vue'
import { defineComponent } from 'vue'
import { mapActions } from 'pinia'
import { useAuthStore } from '@/stores/auth'

export default defineComponent({
  components: {
    CustomInput,
    CustomButton,
    IconImgLogin,
  },
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    ...mapActions(useAuthStore, ['login', 'getCurrentUser']),
    async handleLogin() {
      await this.login({ username: this.username, password: this.password })
      await this.getCurrentUser()
      await this.$router.push('/comments')
    },
  },
})
</script>

<template>
  <div class="login">
    <div class="login-bg">
      <div class="login-group1">
        <div class="spans-login-group1">
          <span class="span1-login-group1"
            >Creativity is thinking up new things.</span
          >
          <br />
          <span class="span1-login-group1"
            >Innovation is doing new things.</span
          >
          <br />
          <span class="span2-login-group1"
            >There is a way to do it better - find it.</span
          >
        </div>
        <div class="form-login">
          <div class="input-username-password">
            <custom-input
              label="Username"
              placeholder="Start typing..."
              v-model:value="username"
              type="text"
            />
            <custom-input
              label="Password"
              placeholder="Start typing..."
              v-model:value="password"
              type="password"
            />
          </div>
          <div class="button-login">
            <custom-button type="primary" @click="handleLogin"
              >Sign In</custom-button
            >
          </div>
        </div>
      </div>
    </div>
    <div class="login-gradient-bg">
      <IconImgLogin />
    </div>
  </div>
</template>

<style>
.login {
  display: flex;
  flex-direction: row;
  height: 100vh;
  overflow: hidden;
}

.login-bg {
  width: 55%;
  display: flex;
  justify-content: center;
  margin-top: 145px;
}

.login-group1 {
  width: 508px;
  height: 430px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.span1-login-group1 {
  font-family: 'Lato';
  font-style: normal;
  font-weight: 700;
  font-size: 32px;
  line-height: 42px;
  /* or 131% */

  color: #1c1d21;
}

.span2-login-group1 {
  font-family: 'Lato';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 21px;
  /* identical to box height, or 150% */

  color: #8181a5;
}

.input-username-password {
  width: 508px;
  height: 228px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.button-login {
  width: 508px;
  height: 84px;
  display: flex;
  justify-content: center;
  align-items: center;
  align-items: end;
}

.login-gradient-bg {
  background: radial-gradient(
    98.58% 98.58% at 57.43% 48.15%,
    #5e81f4 0%,
    #1b51e5 100%
  );
  display: flex;
  flex-direction: column;
  align-items: end;
}
</style>
