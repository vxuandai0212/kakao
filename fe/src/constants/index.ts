export const API_BASE_URL = 'http://127.0.0.1:8080'
export const SOCIAL_SERVICE = 'social'

export const ACCESS_TOKEN = 'accessToken'
export const AUTHENTICATED = 'authenticated'
export const CURRENT_USER = 'currentUser'

export const COMMENT_DISPLAY_TYPE = {
  SCROLL: 'SCROLL',
  NOTICE: 'NOTICE'
}
export const COMMENT_TYPE = {
  COMMENT: 'COMMENT',
  REPLY: 'REPLY'
}
export const COMMENT_SORT_TYPE = {
  DESC: 1,
  ASC: 2
}
export const PROVIDER_TYPE = {
  GOOGLE: 'GOOGLE',
  TWITTER: 'TWITTER',
  FACEBOOK: 'FACEBOOK',
  LOCAL: 'LOCAL'
}

export const OAUTH2_REDIRECT_URI = 'http://127.0.0.1:5173/oauth2/redirect'
export const GOOGLE_AUTH_URL =
  API_BASE_URL + '/auth/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI
export const FACEBOOK_AUTH_URL =
  API_BASE_URL +
  '/auth/oauth2/authorize/facebook?redirect_uri=' +
  OAUTH2_REDIRECT_URI
export const TWITTER_AUTH_URL =
  API_BASE_URL + '/auth/oauth2/authorize/twitter?redirect_uri=' + OAUTH2_REDIRECT_URI
