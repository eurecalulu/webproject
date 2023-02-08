import Vue from 'vue'
import Router from 'vue-router'
import Container from '@/pages/main/Container'
import Dashboard from '@/pages/main/Dashboard'
import Help from '@/pages/main/Help'
import PIListContainer from '@/pages/main/PIListContainer'
import PICreate from '@/pages/main/PICreate'
import PIDetail from '@/pages/main/PIDetail'
import Login from '@/pages/Login'
import Register from '@/pages/Register'
import NotFound from '@/pages/NotFound'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        title: '登录-银保监会政策解读库'
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
      meta: {
        title: '注册-银保监会政策解读库'
      }
    },
    {
      path: '/',
      name: 'Container',
      component: Container,
      redirect: '/dashboard',
      children: [
        {
          path: '/pi/create',
          name: 'PICreate',
          component: PICreate,
          meta: {
            title: '新增政策解读'
          }
        },
        {
          path: '/pi/:status',
          name: 'PIList',
          component: PIListContainer,
          meta: {
            title: '政策解读列表'
          }
        },
        {
          path: '/pi/detail/:state/:id',
          name: 'PIDetail',
          component: PIDetail
        },
        {
          path: '/dashboard',
          name: 'Dashboard',
          component: Dashboard,
          meta: {
            title: '首页-银保监会政策解读库'
          }
        },
        {
          path: '/help',
          name: 'Help',
          component: Help,
          meta: {
            title: '用户手册'
          }
        }
      ]
    },
    {
      path: '*',
      component: NotFound,
      meta: {
        title: '404-出错啦'
      }
    }
  ]
})
