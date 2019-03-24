<template>

  <div id="app">
    <Navbar :student="student" @authenticated="authenticate"></Navbar>
    <router-view @authenticated="authenticate">
      
    </router-view>
  </div>
</template>

<script>


import Navbar from './components/Navbar'
export default {
  data() {
    return {
      student: null
    }
  },
  created: function() {
    this.authenticate(true);
  },
  methods: {
    authenticate(value) {
      if (value) {
        this.$http.get(`/api/profile`)
        .then(response => {
          // JSON responses are automatically parsed.
          console.log(response)
          this.student = response.data
          this.$emit("authenticated", true);
        })
        .catch(e => {
          // this.student = null
          this.student = null
        });
      } else {
        this.student = null;
      }

    }
  },
  name: 'app',
  components: {
    Navbar
  }
}


</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
