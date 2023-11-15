<script lang="ts">
import { defineComponent } from 'vue'
import { mapActions } from 'pinia'
import { useAuthStore } from '@/stores/auth'
import IconCaretDown from '@/components/icons/IconCaretDown.vue'

export default defineComponent({
  components: { IconCaretDown },
  data() {
    return {
      showUserOptionDropdown: false,
    }
  },
  methods: {
    ...mapActions(useAuthStore, ['logout']),
    handleClickOutsideUserOption() {
      this.showUserOptionDropdown = false
    }
  },
})
</script>

<template>
  <div class="user-option" v-click-outside="handleClickOutsideUserOption">
    <img
      src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI2yu2yiXUHnKhLe8NxbVIYn3m1HSuIdnYwK69m0g6w4awMdtgX9W6IrEixDfv3GMXx2o&usqp=CAU"
      alt=""
    />
    <icon-caret-down
      @click="showUserOptionDropdown = !showUserOptionDropdown"
    />
    <div
      id="userOptionDropdown"
      class="dropdown-content"
      :class="[showUserOptionDropdown ? 'show' : '']"
    >
      <a @click="logout">Logout</a>
    </div>
  </div>
</template>

<style>
.user-option {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: 54px;
  height: 45px;
  position: relative;
  cursor: pointer;
  gap: 10px;
}

.user-option img {
  width: 34px;
  height: 34px;
  border-radius: 50%;
}

.user-option select {
  border: none;
  background: none;
  outline: none;
}

.dropdown-content {
  display: none;
  position: absolute;
  right: 0px;
  top: 50px;
  min-width: 160px;
  background: #FFFFFF;
  border: 1px solid #F1F1F5;
  box-shadow: 0px 5px 15px rgba(68, 68, 79, 0.1);
  border-radius: 8px;
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 16px;
  letter-spacing: 0.1px;
  color: #696974;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  cursor: pointer;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
  color: #44444F;
  background-color: #FAFAFB;
}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {
  display: block;
}
</style>
