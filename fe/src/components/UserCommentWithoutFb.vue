<script lang="ts">
import { defineComponent } from 'vue';
import IconOptionPostStatus from './icons/IconOptionPostStatus.vue';
export default defineComponent({
  components: {
    IconOptionPostStatus
  },
  props: {
    avatar: { type: String, require: true },
    name: { type: String, require: true },
    timePost: { type: String, require: true },
    contentPost: { type: String, require: true }
  }
})
</script>

<template>
  <div class="user-comment">
    <div class="img-user-comment">
      <!-- <img src="https://i.pinimg.com/originals/64/b4/8f/64b48f2e4b681a2b9188a44d91563e31.jpg" alt=""> -->
      <img :src="avatar" alt="">
    </div>
    <div class="content-user-comment">
      <div class="icon-span-name-user">
        <div id="icon-fb-username">
          <span id="user-comment-span1">{{ name }}</span>
        </div>
        <span id="user-comment-span2">{{ timePost }}</span>
      </div>
      <div>
        <span id="user-comment-span3">{{ contentPost }}</span>
      </div>
    </div>
    <div id="icon-option-user-comment">
      <IconOptionPostStatus />
    </div>
  </div>
</template>

<style>
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
  background: #FAFAFB;
  border: 1px solid #F1F1F5;
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


  color: #92929D;
}

#user-comment-span3 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  /* identical to box height, or 171% */

  letter-spacing: 0.1px;

  color: #44444F;
}

#icon-option-user-comment {
  margin-left: 20px;
  align-self: center;
  position: relative;
  cursor: pointer;
}
</style>