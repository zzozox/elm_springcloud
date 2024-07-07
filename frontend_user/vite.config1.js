import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

import path from 'path'

export default defineConfig(({ mode}) => {

  // const env = loadEnv(mode, __dirname); // 根据 mode 来判断当前是何种环境
  return {
    plugins: [
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url)), // 使用 __dirname 获取 src 目录的绝对路径
      },
    },
    server: {
      // port: 2000, // 监听的端口号
      proxy: {
        '/mock': {
          target: 'http://localhost:13200',
          // changeOrigin: true,
          rewrite: (path) => path.replace(/^\/ele/, '')
        },
      },
    },
  };
})
