// router
import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '../components/home.vue'
import index from '../index.vue'
import store from '../store/index.js'
import highchar from '../components/highcharts.vue'

import advertisinglist from '../components/Advertisinglist.vue'
import advertisingTimes from '../components/advertisingTimes.vue'

import advertisingUsers from '../components/advertisingUsers.vue'
import advertisingUsersZhl from '../components/advertisingUsersZhl.vue'
import advertisinguserDetail from '../components/AdvertisinguserDetail.vue'

import channeldayActiveuser from '../components/ChanneldayActiveuser.vue'
import channelhourActiveuser from '../components/ChannelhourActiveuser.vue'
import channelmonthActiveuser from '../components/ChannelmonthActiveuser.vue'
import channelnewuser from '../components/Channelnewuser.vue'
import channeltimes from '../components/Channeltimes.vue'
import channelusernums from '../components/Channelusernums.vue'
import channeluserDetail from '../components/ChanneluserDetail.vue'
import channelweekActiveuser from '../components/ChannelweekActiveuser.vue'

import gaiKuanglist from '../components/GaiKuanglist.vue'


import huodongareaNum from '../components/huodongareaNum.vue'
import huodongorderNums from '../components/huodongorderNums.vue'

import liuliangdayActiveuser from '../components/LiuliangdayActiveuser.vue'
import liulianghourActiveuser from '../components/LiulianghourActiveuser.vue'
import liuliangmonthActiveuser from '../components/LiuliangmonthActiveuser.vue'
import liuliangnewuser from '../components/Liuliangnewuser.vue'
import liuliangtimes from '../components/Liuliangtimes.vue'
import liulianguserDetail from '../components/LiulianguserDetail.vue'
import liuliangusernums from '../components/Liuliangusernums.vue'
import liuliangweekActiveuser from '../components/LiuliangweekActiveuser.vue'


import miaoshaOrderPayNums from '../components/miaoshaOrderPayNums.vue'
import miaoshaPayNums from '../components/miaoshaPayNums.vue'
import orderkeDanJia from '../components/orderkeDanJia.vue'
import ordertimes from '../components/ordertimes.vue'
import orderUserNum from '../components/orderUserNum.vue'

import tuangoulist from '../components/tuangoulist.vue'
import tuangouOrderTimes from '../components/tuangouOrderTimes.vue'
import tuangouProductTypeTimes from '../components/tuangouProductTypeTimes.vue'
import tuangouUserNums from '../components/tuangouUserNums.vue'

import VueResource from 'vue-resource'

Vue.use(VueRouter)
Vue.use(VueResource)
/* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   render: h => h(App)
// })


// 0. 如果使用模块化机制编程， 要调用 Vue.use(VueRouter)

// 1. 定义（路由）组件。
// 可以从其他文件 import 进来
// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点在讨论嵌套路由。
const routes = [
  { path: '/',name:"home",component: home},
  { path: '/highcharts',name:"highchar",component: highchar},
  { path: '/liuliangnewuser',name:"liuliangnewuser",component: liuliangnewuser},
  { path: '/liulianguserDetail',name:"liulianguserDetail",component: liulianguserDetail},
  {path: '/channeluserDetail',name:"channeluserDetail",component: channeluserDetail},
  {path: '/advertisinglist',name:"advertisinglist",component: advertisinglist},
  {path: '/advertisingTimes',name:"advertisingTimes",component: advertisingTimes},
  {path: '/advertisingUsers',name:"advertisingUsers",component: advertisingUsers},
  {path: '/advertisingUsersZhl',name:"advertisingUsersZhl",component: advertisingUsersZhl},
  {path: '/advertisinguserDetail',name:"advertisinguserDetail",component: advertisinguserDetail},
  { path: '/channeldayActiveuser',name:"channeldayActiveuser",component: channeldayActiveuser},
  { path: '/channelhourActiveuser',name:"channelhourActiveuser",component: channelhourActiveuser},
  {path: '/channelmonthActiveuser',name:"channelmonthActiveuser",component: channelmonthActiveuser},
  {path: '/channelnewuser',name:"channelnewuser",component: channelnewuser},
  {path: '/channeltimes',name:"channeltimes",component: channeltimes},
  {path: '/channelusernums',name:"channelusernums",component: channelusernums},
  {path: '/channelweekActiveuser',name:"channelweekActiveuser",component: channelweekActiveuser},
  { path: '/gaiKuanglist',name:"gaiKuanglist",component: gaiKuanglist},
  { path: '/huodongareaNum',name:"huodongareaNum",component: huodongareaNum},
  {path: '/huodongorderNums',name:"huodongorderNums",component: huodongorderNums},
  {path: '/liuliangdayActiveuser',name:"liuliangdayActiveuser",component: liuliangdayActiveuser},
  {path: '/liulianghourActiveuser',name:"liulianghourActiveuser",component: liulianghourActiveuser},
  {path: '/liuliangmonthActiveuser',name:"liuliangmonthActiveuser",component: liuliangmonthActiveuser},
  {path: '/liuliangtimes',name:"liuliangtimes",component: liuliangtimes},
  { path: '/liuliangusernums',name:"liuliangusernums",component: liuliangusernums},
  {path: '/liuliangweekActiveuser',name:"liuliangweekActiveuser",component: liuliangweekActiveuser},
  {path: '/miaoshaOrderPayNums',name:"miaoshaOrderPayNums",component: miaoshaOrderPayNums},
  {path: '/miaoshaPayNums',name:"miaoshaPayNums",component: miaoshaPayNums},
  {path: '/orderkeDanJia',name:"orderkeDanJia",component: orderkeDanJia},
  {path: '/ordertimes',name:"ordertimes",component: ordertimes},
  {path: '/orderUserNum',name:"orderUserNum",component: orderUserNum},
  {path: '/tuangoulist',name:"tuangoulist",component: tuangoulist},
  {path: '/tuangouOrderTimes',name:"tuangouOrderTimes",component: tuangouOrderTimes},
  {path: '/tuangouProductTypeTimes',name:"tuangouProductTypeTimes",component: tuangouProductTypeTimes},
  {path: '/tuangouUserNums',name:"tuangouUserNums",component: tuangouUserNums}
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  mode: 'history',
  routes // （缩写）相当于 routes: routes
})

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
const app = new Vue({
  store,
  router,
  render: h => h(index)
}).$mount('#app')

// 现在，应用已经启动了！
