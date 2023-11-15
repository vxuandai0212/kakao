<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    type: { type: String, required: true },
  },
})
</script>

<template>
  <button class="button" :class="type"><slot></slot></button>
</template>

<style>
.button {
  cursor: pointer;
  border-radius: 8px;
  border: none;
  font-size: 14px;
  font-weight: 700;
  font-family: 'Lato';
  width: 250.5px;
  height: 46px;
}
.primary {
  background: #5e81f4;
  color: #ffffff;
  transition: all 0.3s ease;
}
.primary:hover {
  background: #0062ff;
}
</style>
