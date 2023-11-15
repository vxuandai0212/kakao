<script lang="ts">
import IconCaretDown from '@/components/icons/IconCaretDown.vue'
import Search from '@/components/Search.vue'
import { mapState } from 'pinia'
import { defineComponent } from 'vue'
import { useCommentStore } from '@/stores/comment'
import { COMMENT_SORT_TYPE } from '@/constants'
export default defineComponent({
  components: {
    IconCaretDown,
    Search,
  },
  computed: {
    ...mapState(useCommentStore, {
      displayValue(store) {
        return store.list.query.sortDirection === COMMENT_SORT_TYPE.DESC ? 'Latest' : 'Earliest'
      }
    })
  },
  data() {
    return {
      total: 200,
      showDisplayOption: false,
      COMMENT_SORT_TYPE: COMMENT_SORT_TYPE
    }
  },
  methods: {
    handleClickOutside() {
      this.showDisplayOption = false
    },
    async handleChooseSortType(value: number) {
      const state = useCommentStore()
      state.list.query.sortDirection = value
      state.list.query.page = 1
      state.list.query.limit = 10
      await state.searchComment()
    },
    async handleSearchSubmit(searchKeyword: string) {
      const state = useCommentStore()
      state.list.query.page = 1
      state.list.query.limit = 10
      state.list.query.searchKeyword = searchKeyword
      await state.searchComment()
    }
  }
})
</script>

<template>
  <div class="comment-filter">
    <search @search-submit="handleSearchSubmit" />
    <div class="scroll-notice" v-click-outside="handleClickOutside" @click="showDisplayOption = !showDisplayOption">
      <div style="margin-left: 12px">
        <span class="select-label">Display:</span><span class="select-value">{{ displayValue }}</span>
      </div>
      <div style="display: flex; flex-direction: row; align-items: center">
        <div class="separator"></div>
        <icon-caret-down style="margin-right: 10px; margin-left: 10px"></icon-caret-down>
      </div>
      <ul v-show="showDisplayOption" class="select-option-wrap">
        <li @click="handleChooseSortType(COMMENT_SORT_TYPE.DESC)">
          Latest
        </li>
        <li @click="handleChooseSortType(COMMENT_SORT_TYPE.ASC)">
          Earliest
        </li>
      </ul>
    </div>
  </div>
</template>
