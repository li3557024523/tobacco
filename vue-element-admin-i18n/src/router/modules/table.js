/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/sys/leaderSpeek',
  name: 'Table',
  meta: {
    title: '廉政教育',
    icon: 'table'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/sys/leaderSpeek'),
      name: 'DynamicTable',
      meta: { title: '领导讲话' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/sys/cleanNews'),
      name: 'DragTable',
      meta: { title: '廉政要闻' }
    },
    {
      path: 'inline-edit-table',
      component: () => import('@/views/sys/documentSystem'),
      name: 'InlineEditTable',
      meta: { title: '文件制度' }
    },
    {
      path: 'complex-table',
      component: () => import('@/views/sys/policeClock'),
      name: 'ComplexTable',
      meta: { title: '警钟长鸣' }
    }
  ]
}
export default tableRouter
