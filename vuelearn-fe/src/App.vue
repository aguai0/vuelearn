<template>
  <div id="app">
    <div class="debug-menu" v-if="showMenus">
      <el-button type="text" @click="hiddenMenus">收起菜单</el-button>
      <el-menu
        :default-active="defaultPage"
        class="app-menu"
        :router="true">
        <el-submenu v-for="(itemOne, indexOne) in navMenus"
                    :index="`${itemOne.path}`"
                    :key="`${itemOne.path}`"
                    v-if="!itemOne.hidden">
          <template slot="title">
            <i :class="[`${itemOne.icon}`]"></i>
            <span>{{itemOne.title}}</span>
          </template>
          <template v-for="(itemTwo, indexTwo) in itemOne.subMenus">
            <el-menu-item
              :index="`${itemTwo.path}`"
              v-if="!itemTwo.subMenus && !itemTwo.hidden"
              :key="`${itemTwo.path}`">{{itemTwo.title}}

            </el-menu-item>
            <el-submenu
              :index="`${itemTwo.path}`"
              :key="`${itemTwo.path}`"
              v-if="itemTwo.subMenus && !itemTwo.hidden">
              <template slot="title">{{itemTwo.title}}</template>
              <el-menu-item
                v-for="(itemThree, indexThree) in itemTwo.subMenus"
                :index="`${itemThree.path}`"
                :key="`${itemThree.path}`"
                v-if="!itemTwo.hidden">{{itemThree.title}}
              </el-menu-item>
            </el-submenu>
          </template>
        </el-submenu>
      </el-menu>
    </div>
    <router-view class="app-container"/>
  </div>
</template>

<script>
import menus from '@/constant/menu'
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      showMenus: false,
      defaultPage: '/',
      navMenus: []
    }
  },
  mounted () {
    this.navMenus = menus
    this.showMenus = !!window.developMode
    /**
     * 根据携带的cookie，自动获取当前系统的所有按钮权限，会发起一个http请求
     */
    // this.updatePermissions('demo')
  },
  methods: {
    ...mapActions([
      // 'updatePermissions'
    ]),
    hiddenMenus () {
      this.showMenus = false
    }
  }
}
</script>

<style>
  html, body, #app {
    margin: 0;
    padding: 0;
    height: 100%;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    display: flex;
  }

  .app-container {
    flex: 1 0 0;
    min-width: 0;
    padding: 20px;
    height: 100%;
    align-items: stretch;
  }

  .debug-menu {
    width: 250px;
  }

  .debug-menu .el-menu {
    height: 100%;
  }

  .debug-menu .el-button {
    width: 100%;
  }
</style>
