<script lang="ts">
import IconSearch from '@/components/icons/IconSearch.vue'
import { defineComponent } from 'vue'
export default defineComponent({
  components: {
    IconSearch,
  },
  methods: {
    handleSearchComment() {
      this.$emit('search-submit', this.searchKeyword)
    },
  },
  data() {
    return {
      searchKeyword: '',
    }
  },
})
</script>

<template>
  <div class="search-text">
    <input type="text" name="" placeholder="Search" maxlength="150" v-model="searchKeyword"
      @keypress.enter="handleSearchComment" />
    <IconSearch style="cursor: pointer" @click="handleSearchComment" />
  </div>
</template>

<style>
.search-text {
  flex: 1;
  margin-right: 15px;
  padding-left: 15px;
  padding-right: 15px;
  gap: 20px;
  width: 500px;
  height: 50px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background: #fafafb;
  border: 1px solid #f1f1f5;
  border-radius: 15px;
}

.search-text input {
  width: 100%;
  border: none;
  background: none;
  outline: none;
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 24px;
  /* identical to box height, or 171% */

  letter-spacing: 0.1px;

  color: #44444F;
}
</style>
