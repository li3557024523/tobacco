import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
import tableRouter from './modules/table'
import cultureRouter from './modules/culture'
import examineRouter from './modules/examine'
import nestedRouter from './modules/nested'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component:
      () =>
        import('@/views/login/index'),
    hidden:
      true
  },
  {
    path: '/auth-redirect',
    component:
      () =>
        import('@/views/login/auth-redirect'),
    hidden:
      true
  },
  {
    path: '/404',
    component:
      () =>
        import('@/views/error-page/404'),
    hidden:
      true
  },
  {
    path: '/401',
    component:
      () =>
        import('@/views/error-page/401'),
    hidden:
      true
  },
  {
    path: '/',
    component:
      Layout,
    redirect:
      '/dashboard',
    children:
      [
        {
          path: 'dashboard',
          component: () => import('@/views/dashboard/index'),
          name:
            'Dashboard',
          meta:
          {
            title: 'dashboard', icon:
              'dashboard', affix:
              true
          }
        }
      ]
  },
  // {
  //   path: '/documentation',
  //   component:
  //     Layout,
  //   children:
  //     [
  //       {
  //         path: 'index',
  //         component: () => import('@/views/documentation/index'),
  //         name:
  //           'Documentation',
  //         meta:
  //         {
  //           title: 'documentation', icon:
  //             'documentation', affix:
  //             true
  //         }
  //       }
  //     ]
  // },
  // {
  //   path: '/guide',
  //   component:
  //     Layout,
  //   redirect:
  //     '/guide/index',
  //   children:
  //     [
  //       {
  //         path: 'index',
  //         component: () => import('@/views/guide/index'),
  //         name:
  //           'Guide',
  //         meta:
  //         {
  //           title: 'guide', icon:
  //             'guide', noCache:
  //             true
  //         }
  //       }
  //     ]
  // },
  {
    path: '/profile',
    component:
      Layout,
    redirect:
      '/profile/index',
    hidden:
      true,
    children:
      [
        {
          path: 'index',
          component: () => import('@/views/profile/index'),
          name:
            'Profile',
          meta:
          {
            title: 'profile', icon:
              'user', noCache:
              true
          }
        }
      ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
// export const asyncRoutes = [
//   {
//     path: '/permission',
//     component: Layout,
//     redirect: '/permission/page',
//     alwaysShow: true, // will always show the root menu
//     name: 'Permission',
//     meta: {
//       title: 'permission',
//       icon: 'lock',
//       roles: ['admin', 'editor' ,'Bos'] // you can set roles in root nav
//     },
//     children: [
//       {
//         path: 'user',
//         component: () => import('@/views/sys/user'),
//         name: 'PagePermission',
//         meta: {
//           title: 'pagePermission',
//           roles: ['admin','Bos'] // or you can only set roles in sub nav
//         }
//       },
//       {
//         path: 'directive',
//         component: () => import('@/views/permission/directive'),
//         name: 'DirectivePermission',
//         meta: {
//           title: 'directivePermission',
//           // if do not set roles, means: this page does not require permission
//           roles: ['admin','Bos']
//         }
//       },
//       {
//         path: 'role',
//         component: () => import('@/views/permission/role'),
//         name: 'RolePermission',
//         meta: {
//           title: 'rolePermission',
//           roles: ['admin','Bos']
//         }
//       }
//     ]
//   },

//   {
//     path: '/icon',
//     component: Layout,
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/icons/index'),
//         name: 'Icons',
//         meta: { title: 'icons', icon: 'icon', noCache: true }
//       }
//     ]
//   },

//   /** when your routing map is too long, you can split it into small modules **/

//   {
//     path: '/example',
//     component: Layout,
//     redirect: '/example/list',
//     name: 'Example',
//     meta: {
//       title: 'example',
//       icon: 'example'
//     },
//     children: [
//       {
//         path: 'create',
//         component: () => import('@/views/example/create'),
//         name: 'CreateArticle',
//         meta: { title: 'createArticle', icon: 'edit' }
//       },
//       {
//         path: 'edit/:id(\\d+)',
//         component: () => import('@/views/example/edit'),
//         name: 'EditArticle',
//         meta: { title: 'editArticle', noCache: true, activeMenu: '/example/list' },
//         hidden: true
//       },
//       {
//         path: 'list',
//         component: () => import('@/views/example/list'),
//         name: 'ArticleList',
//         meta: { title: 'articleList', icon: 'list' }
//       }
//     ]
//   },

//   {
//     path: '/tab',
//     component: Layout,
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/tab/index'),
//         name: 'Tab',
//         meta: { title: 'tab', icon: 'tab' }
//       }
//     ]
//   },

//   {
//     path: '/error',
//     component: Layout,
//     redirect: 'noRedirect',
//     name: 'ErrorPages',
//     meta: {
//       title: 'errorPages',
//       icon: '404'
//     },
//     children: [
//       {
//         path: '401',
//         component: () => import('@/views/error-page/401'),
//         name: 'Page401',
//         meta: { title: 'page401', noCache: true }
//       },
//       {
//         path: '404',
//         component: () => import('@/views/error-page/404'),
//         name: 'Page404',
//         meta: { title: 'page404', noCache: true }
//       }
//     ]
//   },

//   {
//     path: '/error-log',
//     component: Layout,
//     children: [
//       {
//         path: 'log',
//         component: () => import('@/views/error-log/index'),
//         name: 'ErrorLog',
//         meta: { title: 'errorLog', icon: 'bug' }
//       }
//     ]
//   },

//   {
//     path: '/excel',
//     component: Layout,
//     redirect: '/excel/export-excel',
//     name: 'Excel',
//     meta: {
//       title: 'excel',
//       icon: 'excel'
//     },
//     children: [
//       {
//         path: 'export-excel',
//         component: () => import('@/views/excel/export-excel'),
//         name: 'ExportExcel',
//         meta: { title: 'exportExcel' }
//       },
//       {
//         path: 'export-selected-excel',
//         component: () => import('@/views/excel/select-excel'),
//         name: 'SelectExcel',
//         meta: { title: 'selectExcel' }
//       },
//       {
//         path: 'export-merge-header',
//         component: () => import('@/views/excel/merge-header'),
//         name: 'MergeHeader',
//         meta: { title: 'mergeHeader' }
//       },
//       {
//         path: 'upload-excel',
//         component: () => import('@/views/excel/upload-excel'),
//         name: 'UploadExcel',
//         meta: { title: 'uploadExcel' }
//       }
//     ]
//   },

//   {
//     path: '/zip',
//     component: Layout,
//     redirect: '/zip/download',
//     alwaysShow: true,
//     name: 'Zip',
//     meta: { title: 'zip', icon: 'zip' },
//     children: [
//       {
//         path: 'download',
//         component: () => import('@/views/zip/index'),
//         name: 'ExportZip',
//         meta: { title: 'exportZip' }
//       }
//     ]
//   },

//   {
//     path: '/pdf',
//     component: Layout,
//     redirect: '/pdf/index',
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/pdf/index'),
//         name: 'PDF',
//         meta: { title: 'pdf', icon: 'pdf' }
//       }
//     ]
//   },
//   {
//     path: '/pdf/download',
//     component: () => import('@/views/pdf/download'),
//     hidden: true
//   },

//   {
//     path: '/theme',
//     component: Layout,
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/theme/index'),
//         name: 'Theme',
//         meta: { title: 'theme', icon: 'theme' }
//       }
//     ]
//   },

//   {
//     path: '/clipboard',
//     component: Layout,
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/clipboard/index'),
//         name: 'ClipboardDemo',
//         meta: { title: 'clipboardDemo', icon: 'clipboard' }
//       }
//     ]
//   },

//   {
//     path: '/i18n',
//     component: Layout,
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/i18n-demo/index'),
//         name: 'I18n',
//         meta: { title: 'i18n', icon: 'international' }
//       }
//     ]
//   },

//   {
//     path: 'external-link',
//     component: Layout,
//     children: [
//       {
//         path: 'https://github.com/PanJiaChen/vue-element-admin',
//         meta: { title: 'externalLink', icon: 'link' }
//       }
//     ]
//   },

//   // 404 page must be placed at the end !!!
//   { path: '*', redirect: '/404', hidden: true }
// ]

export const asyncRoutes = [
  {
    path: '/sys',
    component: Layout,
    redirect: '',
    alwaysShow: true, // 总是显示根菜单
    name: 'sys',
    meta: {
      title: '系统管理',
      icon: 'example',
      roles: ['admin', 'editor', '超级管理员'] // 您可以在根导航中设置角色
    },
    children: [
      {
        path: 'user',
        component: () => import('@/views/sys/user'),
        name:
          'user',
        meta:
        {
          title: '用户管理', roles:
            ['admin', '超级管理员']
        }
      },
      {
        path: 'role',
        component:
          () =>
            import('@/views/sys/role'),
        name:
          'role',
        meta:
        {
          title: '角色管理', roles:
            ['admin', '超级管理员']
        }
      },
      {
        path: 'menu',
        component:
          () =>
            import('@/views/sys/menu'),
        name:
          'menu',
        meta:
        {
          title: '菜单管理', roles:
            ['admin', '超级管理员']
        }
      },
      {
        path: 'dept',
        component:
          () =>
            import('@/views/sys/dept'),
        name:
          'dept',
        meta:
        {
          title: '部门管理', roles:
            ['admin', '超级管理员']
        }

      },
      {
      path: 'log',
      component:
        () =>
          import('@/views/sys/log'),
      name:
        'log',
      meta:
      {
        title: '日志管理', roles:
          ['admin', '超级管理员']
      }

    }
    ]
  },
  {
    path: '/JobsRisk',
    component: Layout,
    redirect: '',
    alwaysShow: true, // 总是显示根菜单
    name: 'JobsRisk',
    meta: {
      title: '风险防控',
      icon: 'tree-table',
      //roles: ['admin', 'editor', '超级管理员'] // 您可以在根导航中设置角色
    },
    children: [
      {
        path: 'JobsRisk',
        component: () => import('@/views/sys/JobsRisk'),
        name:
          'JobsRisk',
        meta:
          {
            title: '岗位风险梳理'
          }
      },{
        path: 'FlowRisk',
        component: () => import('@/views/sys/FlowRisk'),
        name:
          'FlowRisk',
        meta:
          {
            title: '流程风险梳理'
          }
      },{
        path: 'RiskPoint',
        component: () => import('@/views/sys/RiskPoint'),
        name:
          'RiskPoint',
        meta:
          {
            title: '风险点评定'
          }
      }


    ]
  },/*
path: '/sys',
  component
:
Layout,
  redirect
:
'/education/user',
  alwaysShow
:
true, // 总是显示根菜单
  name
:
'education',
  meta
:
{
  title: '廉政教育',
    icon
:
  'example',
    roles
:
  ['admin', 'editor', '超级管理员'] // 您可以在根导航中设置角色
}
,
children: [
  {
    path: 'leaderSpeek',
    component: () = > import('@/views/sys/leaderSpeek'),
  name
:
'leaderSpeek',
  meta
:
{
  title: '领导讲话' , roles
:
  ['admin', '超级管理员']
}
},
{
  path: 'cleanNews',
    component
:
  () =
>
  import('@/views/sys/cleanNews'),
    name
:
  'cleanNews',
    meta
:
  {
    title: '廉政要闻' , roles
  :
    ['admin', '超级管理员']
  }
}
,
{
  path: 'documentSystem',
    component
:
  () =
>
  import('@/views/sys/documentSystem'),
    name
:
  'documentSystem',
    meta
:
  {
    title: '文件制度' , roles
  :
    ['admin', '超级管理员']
  }
}
,
{
  path: 'policeClock',
    component
:
  () =
>
  import('@/views/sys/policeClock'),
    name
:
  'policeClock',
    meta
:
  {
    title: '警钟长鸣', roles
  :
    ['admin', '超级管理员']
  }
,
}
]
},*///   componentsRouter,
  chartsRouter,
  //   nestedRouter,
  tableRouter,
  cultureRouter,
  examineRouter,

  {
    path: '/record',
    component:
      Layout,
    redirect:
      '/record',
    alwaysShow:
      true, // 总是显示根菜单
    name:
      'record',
    meta:
    {
      title: '基础档案设置',
      icon:
        'example',
      roles:
        ['editor', 'admin', '超级管理员'] // you can set roles in root nav
    },
    children: [
      {
        path: 'recordset',
        component: () => import('../views/table/complex-table'),
        name:
          '基础档案设置',
        meta:
        {
          title: '基础档案设置', roles:
            ['admin', 'editor', '超级管理员']
        }
      },
      {
        path: 'pick',
        component:
          () =>
            import('../views/table/complex-table'),
        name:
          'pick',
        meta:
        {
          title: '收派标准', roles:
            ['editor', '超级管理员']
        }
      },
      {
        path: 'regular',
        component:
          () =>
            import('../views/table/complex-table'),
        name:
          '班车线路',
        meta:
        {
          title: '班车线路', roles:
            ['editor', '超级管理员']
        }
      }
    ]
  },
  // 404 页面必须放在最后 !!!
  {
    path: '*', redirect:
      '/404', hidden:
      true
  }
]

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes:
      constantRoutes
  })

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
