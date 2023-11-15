<script lang="ts">
import IconLogoSavvycom from '@/components/icons/IconLogoSavvycom.vue'
import UserOption from '@/components/UserOption.vue'
import Administrator from '@/components/Administrator.vue'
import PostStatus from '@/components/PostStatus.vue'
import SelectSort from '@/components/SelectSort.vue'
import MainComments from '@/components/MainComments.vue'
import CardProject from '@/components/CardProject.vue'
import CommentFilter from '@/views/admin/comment/components/Filter.vue'
import CommentList from '@/views/admin/comment/components/CommentList.vue'
import { defineComponent } from 'vue'
import { useCommentStore } from '@/stores/comment'
export default defineComponent({
  components: {
    IconLogoSavvycom,
    UserOption,
    Administrator,
    MainComments,
    PostStatus,
    SelectSort,
    CardProject,
    CommentList,
    CommentFilter,
  },
  data() {
    return {
      total: 200,
    }
  },
  async created() {
    const commentStore = useCommentStore()
    commentStore.list.query.page = 1
    commentStore.list.query.limit = 10
    commentStore.list.query.searchKeyword = ''
    await commentStore.searchComment()
  }
})
</script>

<template>
  <div class="admin">
    <div class="header-admin">
      <icon-logo-savvycom style="margin-left: 36px" />
      <user-option style="margin-right: 34px" />
    </div>
    <div class="content-admin">
      <div class="sidebar-admin">
        <div class="adminitrators">
          <administrator style="margin-left: 10px; margin-top: 20px" />
        </div>
        <main-comments />
      </div>
      <div class="post-admin">
        <span id="span-management">Comments Management</span>
        <div class="post-user-after-login" style="margin-top: 17px">
          <post-status
            src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
            name="Geralt of Rivia"
            timePost="12 April at 09.28 PM"
            contentPost="I'll carry on killing monsters in the ruins of this world until some monster kills me."
          />
          <comment-filter class="comment-filter-container" />
          <comment-list />
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.comment-filter-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding-top: 20px;
  margin-top: 20px;
  margin-bottom: 20px;
  border-top: 1px solid #F1F1F5;
}
.admin {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.adminitrators {
  display: flex;
}

.header-admin {
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #f1f1f5;
  background-color: #ffffff;
}

.content-admin {
  display: flex;
  flex-direction: row;
}

.sidebar-admin {
  width: 250px;
  border: 1px solid #f1f1f5;
  background-color: #ffffff;
}

.post-admin {
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  margin-left: 30px;
  margin-bottom: 40px;
}

.post-search-comments {
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 15px;
  margin-top: 17px;
  padding: 20px;
}

.filter-comments-admin {
  display: flex;
  flex-direction: column;
}

.search-sort {
  display: flex;
  flex-direction: row;
}

#span-management {
  font-family: 'Poppins';
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  line-height: 36px;
  letter-spacing: 0.1px;
  color: #171725;
}

.main-comments {
  margin-top: 20px;
  display: flex;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.form {
  display: flex;
  flex-direction: column;
  width: 508px;
  height: 241px;
  margin: 0;
  padding: 0;
}

.seeall {
  margin-top: 10px;
}

.send-message {
  margin-top: 10px;
}

.button-delete {
  margin-top: 10px;
}

.administrator {
  margin-top: 10px;
}

.mainComments {
  margin-top: 20px;
}

.card-project {
  margin-top: 10px;
}

.total-comments {
  margin-top: 10px;
}

.sign-with {
  margin-top: 10px;
}

.scroll-notice {
  margin-top: 10px;
}

.post-something {
  margin-top: 10px;
}

.search {
  margin-top: 10px;
}
</style>
