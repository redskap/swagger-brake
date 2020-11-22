const { description } = require('../../package')

module.exports = {
  base: '/swagger-brake/',
  dest: '../docs',
  title: 'Swagger Brake',
  description: description,
  head: [
    ['meta', { name: 'theme-color', content: '#3eaf7c' }],
    ['meta', { name: 'apple-mobile-web-app-capable', content: 'yes' }],
    ['meta', { name: 'apple-mobile-web-app-status-bar-style', content: 'black' }]
  ],
  themeConfig: {
    repo: '',
    editLinks: false,
    docsDir: 'src-docs',
    editLinkText: '',
    lastUpdated: false,
    nav: [
      {
        text: 'Introduction',
        link: '/introduction/',
      }
    ],
    sidebar: [
      '/introduction/',
      '/configuration/',
      '/cli/',
      '/maven/',
      '/gradle/',
      '/rules/',
      '/constraints/',
      '/troubleshooting/',
      '/embedding/',
    ]
  },
  plugins: [
    '@vuepress/plugin-back-to-top',
    '@vuepress/plugin-medium-zoom',
  ]
}
