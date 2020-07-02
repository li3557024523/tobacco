/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const examineRouter = {
  path: '/styleOfWork',
  component: Layout,
  redirect: '/sys/styleOfWork',
  name: 'styleOfWork',
  meta: {
    title: '责任监督',
    icon: 'table'
  },
  children: [
    {
      path: 'styleOfWork',
      component: () => import('@/views/sys/styleOfWork'),
      name: 'styleOfWork',
      meta: { title: '通知' }

    },
    {
      path: 'again',
      component: () => import('@/views/sys/again'),
      name: 'again',
      meta: { title: '整改通知' }
    },
    {
      path: 'executiveForce',
      component: () => import('@/views/sys/executiveForce'),
      name: 'executiveForce',
      meta: { title: '报告' }
    },
    {
      path: 'forceAgain',
      component: () => import('@/views/sys/forceAgain'),
      name: 'forceAgain',
      meta: { title: '整改报告' }
    }
  ]
}
export default examineRouter
