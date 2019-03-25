<template>
  <div id="app">
    <Navbar :student="student" @authenticated="authenticate"></Navbar>
    <router-view :onGuestRedirect="onGuestRedirect" @authenticated="authenticate">
      
    </router-view>
  </div>
</template>

<script>


import Navbar from './components/Navbar'
export default {
  data() {
    return {
      student: null,
      authenticated: null,
    }
  },
  mounted: function() {
    this.authenticate(true);
  },

  methods: {
    authenticate(value) {
      this.authenticated = null;
      if (value) {
        this.$http.get(`/api/profile`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.authenticated = true;
          this.student = response.data
          this.$emit("authenticated", true);
        })
        .catch(e => {
          // this.student = null
          this.authenticated = false;
          this.student = null
          this.$emit("authenticated", false);

        });
      } else {
        this.authenticated = false;
        this.student = null;
        this.$emit("authenticated", false);

      }

    },
    onGuestRedirect() {
      if (this.authenticated === null) {
        this.$on('authenticated', (value) => {
          if (!value) {
            this.$router.replace({ name: "login" });
          }
        })
      } else if (this.authenticated === false) {
        this.$router.replace({ name: "login" });
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
  color: #2c3e50;
}
</style>
