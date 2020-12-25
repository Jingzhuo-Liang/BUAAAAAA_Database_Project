/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/table',
  name: 'Table',
  meta: {
    title: 'Tables',
    icon: 'table'
  },
  children: [
    // {
    //   path: 'dynamic-table',
    //   component: () => import('@/views/table/dynamic-table/index'),
    //   name: 'DynamicTable',
    //   meta: { title: 'Dynamic Table' }
    // },
    // {
    //   path: 'drag-table',
    //   component: () => import('@/views/table/drag-table'),
    //   name: 'DragTable',
    //   meta: { title: 'Drag Table' }
    // },
    // {
    //   path: 'inline-edit-table',
    //   component: () => import('@/views/table/inline-edit-table'),
    //   name: 'InlineEditTable',
    //   meta: { title: 'Inline Edit' }
    // },
    {
      path: 'user-table',
      component: () => import('@/views/table/user-table'),
      name: 'User Table',
      meta: { title: 'User Table', roles: ['admin'] }
    },
    {
      path: 'user-user',
      component: () => import('@/views/table/user-user'),
      name: 'User Table',
      meta: { title: 'User Table', roles: ['editor'] }
    },
    {
      path: 'commodity-table',
      component: () => import('@/views/table/commodity-table'),
      name: 'Commodity Table',
      meta: { title: 'Commodity Table', roles: ['admin'] }
    },
    {
      path: 'user-commodity',
      component: () => import('@/views/table/user-commodity'),
      name: 'Commodity Table',
      meta: { title: 'Commodity Table', roles: ['editor'] }
    },
    {
      path: 'leavemessage-table',
      component: () => import('@/views/table/leavemessage-table'),
      name: 'leavemessage Table',
      meta: { title: 'LeaveMessage Table', roles: ['admin'] }
    },
    {
      path: 'replymessage-table',
      component: () => import('@/views/table/replymessage-table'),
      name: 'replymessage Table',
      meta: { title: 'ReplyMessage Table', roles: ['admin'] }
    }
  ]
}
export default tableRouter
