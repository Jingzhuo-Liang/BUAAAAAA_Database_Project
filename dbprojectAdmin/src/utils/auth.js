import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  console.log('cookies ' + Cookies.get(TokenKey))
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
