import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/authentication/Login.vue'
import Post from '@/views/user/post/Post.vue'
import CommentManagement from '@/views/admin/comment/Index.vue'
import PageNotFound from '@/views/404/Index.vue'
import { ACCESS_TOKEN, AUTHENTICATED } from '@/constants'
import { ElNotification } from 'element-plus'
import 'element-plus/es/components/notification/style/css'
import 'element-plus/es/components/icon/style/css'
import { useAuthStore } from '@/stores/auth'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'post',
      component: Post,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/comments',
      name: 'comment',
      component: CommentManagement,
    },
    {
      path: '/oauth2/redirect',
      redirect: (to) => {
        const authStore = useAuthStore()
        const token = to.query.token
        const error = to.query.error
        if (token) {
          localStorage.setItem(ACCESS_TOKEN, token as string)
          localStorage.setItem(AUTHENTICATED, 'true')
          authStore.authenticated = true
          authStore.getCurrentUser()
          ElNotification({
            title: 'Success',
            message: 'Login successfully',
            type: 'success',
          })
        } else {
          ElNotification({
            title: 'Error',
            message: (error as string) || 'Login fail',
            type: 'error',
          })
        }
        return { path: '/', query: {} }
      },
    },
    {
      path: '/:pathMatch(.*)*',
      component: PageNotFound
    }
  ],
})

router.beforeEach(async (to, from, next) => {
  const authStore = useAuthStore()
  const { isAdmin, authenticated } = authStore
  if (to.name === 'comment' && !isAdmin) {
    next({ name: 'login' })
  } else if (to.name === 'login' && authenticated) {
    if (isAdmin) next({ name: 'comment' })
    else next({ name: 'post' })
  } else {
    next()
  }
})

export default router
