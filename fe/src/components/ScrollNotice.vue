<script lang="ts">
import { defineComponent } from 'vue';
import IconCaretDown from '@/components/icons/IconCaretDown.vue';
import { useCommentStore } from '@/stores/comment';
import { COMMENT_DISPLAY_TYPE } from '@/constants';
import { mapActions, mapState } from 'pinia';

export default defineComponent({
  components: { IconCaretDown },
  computed: {
    ...mapState(useCommentStore, {
      displayValue(store) {
        return store.ui.displayType === COMMENT_DISPLAY_TYPE.NOTICE ? 'Notice' : 'Scroll'
      },
    }),
  },
  data() {
    return {
      showDisplayOption: false,
      COMMENT_DISPLAY_TYPE: COMMENT_DISPLAY_TYPE
    }
  },
  methods: {
    ...mapActions(useCommentStore, ['noticeGetInit', 'scrollGetInit']),
    handleClickOutside() {
      this.showDisplayOption = false
    },
    handleChooseDisplayType(value: string) {
      const state = useCommentStore()
      state.ui.displayType = value
      if (value === COMMENT_DISPLAY_TYPE.NOTICE) {
        this.noticeGetInit()
      } else {
        this.scrollGetInit()
      }
    }
  }
})
</script>

<template>
  <div class="scroll-notice" v-click-outside="handleClickOutside" @click="showDisplayOption = !showDisplayOption">
    <div style="margin-left: 12px;"><span class="select-label">Display:</span><span class="select-value">{{ displayValue }}</span>
    </div>
    <div style="display: flex; flex-direction: row; align-items: center;">
      <div class="separator"></div>
      <icon-caret-down style="margin-right: 10px; margin-left: 10px;"></icon-caret-down>
    </div>
    <ul v-show="showDisplayOption" class="select-option-wrap">
      <li @click="handleChooseDisplayType(COMMENT_DISPLAY_TYPE.SCROLL)">Scroll</li>
      <li @click="handleChooseDisplayType(COMMENT_DISPLAY_TYPE.NOTICE)">Notice</li>
    </ul>
  </div>
</template>

<style lang="scss">
.separator {
  width: 1px;
  height: 38px;
  background: #F1F1F5;
}

.scroll-notice {
  position: relative;
  cursor: pointer;
  align-items: center;
  margin-top: 15px;
  margin-bottom: 15px;
  width: 200px;
  height: 38px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background: #FAFAFB;
  border: 1px solid #F1F1F5;
  border-radius: 10px;

  .select-label {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 16px;
    letter-spacing: 0.1px;
    color: #696974;
  }

  .select-value {
    margin-left: 5px;
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 16px;
    letter-spacing: 0.1px;
    color: #44444F;
  }
}

.select-option-wrap {
  cursor: pointer;
  position: absolute;
  right: 0;
  top: 30px;
  min-width: 160px;
  background: #FFFFFF;
  border: 1px solid #F1F1F5;
  box-shadow: 0px 5px 15px rgb(68 68 79 / 10%);
  border-radius: 8px;
  list-style: none;
  padding: 0;

  li {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 16px;
    letter-spacing: 0.1px;
    color: #696974;
    padding: 12px 16px;

    &:hover {
      font-weight: 500;
      color: #44444F;
      background-color: #FAFAFB;
    }
  }
}

#select-scroll-notice {
  width: 75px;
  height: 20px;
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 16px;
  letter-spacing: 0.1px;
  color: #44444F;
  border: none;
  background: none;
  outline: none;
  margin-left: 55px;
}

#span-scroll-notice {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 16px;
  letter-spacing: 0.1px;

  color: #696974;
  margin-left: 12px;
}
</style>