/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const cultureRouter = {
  path: '/culture',
  component: Layout,
  redirect: '/sys/dataCollection',
  name: 'Culture',
  meta: {
    title: '廉政文化',
    icon: 'table'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/sys/dataCollection'),
      name: 'DynamicTable',
      meta: { title: '资料集锦' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/sys/windBeautiful'),
      name: 'DragTable',
      meta: { title: '清风文苑' }
    }
  ]
}
export default cultureRouter
