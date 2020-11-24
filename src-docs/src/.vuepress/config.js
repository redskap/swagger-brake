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
    repo: 'redskap/swagger-brake',
    editLinks: true,
    docsDir: 'src-docs/src',
    editLinkText: 'Help improve these docs!',
    lastUpdated: true,
    sidebar: [
      '/',
      '/configuration/',
      '/cli/',
      '/maven/',
      '/gradle/',
      '/rules/',
      '/constraints/',
      '/troubleshooting/',
      '/devguide/',
      '/changelog/',
    ]
  },
  plugins: [
    '@vuepress/plugin-back-to-top',
    '@vuepress/plugin-medium-zoom',
    ['@vuepress/plugin-google-analytics', { ga: "G-XDJP4RYYJ6" }],
  ]
}
