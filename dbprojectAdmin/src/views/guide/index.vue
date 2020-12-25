<template>
  <div class="app-container">
    <aside>
      <body>
        Hello visiter or admin!
        <p>'货比三家' is a website dedicate in providing high quality price comparison system for
          users to select products between various shopping sites like Taobao or JD. This webpage
          is mainly for administrator to manage user and data.</p>
        <p>If you are new to this website, please click the button 'Show Guide' below.</p>
        <p>Hope you enjoy it!</p>
      </body>
      <!--      The guide page is useful for some people who entered the project for the first time. You can briefly introduce the-->
      <!--      features of the project. Demo is based on-->
      <!--      <a href="https://github.com/kamranahmedse/driver.js" target="_blank">driver.js.</a>-->
    </aside>
    <el-button icon="el-icon-question" type="primary" @click.prevent.stop="guide">
      Show Guide
    </el-button>
  </div>
</template>

<script>
import Driver from 'driver.js' // import driver.js
import 'driver.js/dist/driver.min.css' // import driver.js css
import steps from './steps'

export default {
  name: 'Guide',
  data() {
    return {
      driver: null
    }
  },
  mounted() {
    this.driver = new Driver()
  },
  methods: {
    guide() {
      this.driver.defineSteps(steps)
      this.driver.start()
    }
  }
}
</script>
