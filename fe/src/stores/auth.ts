import { defineStore } from 'pinia'
import { ACCESS_TOKEN, AUTHENTICATED, CURRENT_USER } from '@/constants'
import { login, getCurrentUser } from '@/apis/auth'
import type { LoginRequest } from '@/types/request'
import type { CurrentUser } from '@/types/state'
import { PROVIDER_TYPE } from '@/constants'
import router from '@/router'

const getCurrentUserFromLocalStorage = () => {
  const value = localStorage.getItem(CURRENT_USER)
  if (value) return JSON.parse(value)
  return null
}

const getIsAuthenticatedFromLocalStorage = () => {
  const value = localStorage.getItem(AUTHENTICATED)
  if (value && value == 'true') return true
  return false
}

const getIsAdmin = () => {
  const value = localStorage.getItem(CURRENT_USER)
  if (value) {
    const currentUser = JSON.parse(value)
    if (currentUser && currentUser.provider === PROVIDER_TYPE.LOCAL) return true
  } 
  return false
}

export const useAuthStore = defineStore('auth', {
  state: () => ({
    authenticated: getIsAuthenticatedFromLocalStorage(),
    currentUser: getCurrentUserFromLocalStorage() as CurrentUser | null,
    isAdmin: getIsAdmin(),
  }),
  actions: {
    getCurrentUser() {
      if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject('No access token set.')
      }

      return new Promise((resolve, reject) => {
        getCurrentUser()
          .then((response) => {
            const { data } = response
            if (!data) {
              reject('Verification failed, please Login again.')
            }
            localStorage.setItem(CURRENT_USER, JSON.stringify(data))
            if (data.provider === PROVIDER_TYPE.LOCAL) this.isAdmin = true
            this.currentUser = data
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    login(loginRequest: LoginRequest) {
      return new Promise((resolve, reject) => {
        login(loginRequest)
          .then((response) => {
            const { data } = response
            if (!data) {
              reject('Verification failed, please Login again.')
            }
            const { accessToken } = data
            if (accessToken) {
              localStorage.setItem(ACCESS_TOKEN, accessToken as string)
              localStorage.setItem(AUTHENTICATED, 'true')
              this.authenticated = true
            }
            resolve(data)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },
    async logout() {
      await localStorage.removeItem(ACCESS_TOKEN)
      await localStorage.removeItem(AUTHENTICATED)
      await localStorage.removeItem(CURRENT_USER)
      this.authenticated = false
      this.currentUser = null
      this.isAdmin = false
      router.push('/login')
    },
  },
})
