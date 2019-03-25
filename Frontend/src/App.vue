<template>
  <div id="app">
    <Navbar :student="student" @authenticated="authenticate"></Navbar>
    <router-view :onGuestRedirect="onGuestRedirect" @authenticated="authenticate">
      
    </router-view>
  </div>
</template>

<script>

<<<<<<< HEAD
        },
        data() {
            return {
                collapsed: false,
                themes: ['', 'white-theme'],
                selectedTheme: '',
                num_reminders : '3',
                menu: [
                    {
                        header: true,
                        title: 'Navigation'
                    },
                    {
                        href: '/view',
                        title: 'Profile',
                        icon: 'fa fa-download'
                    },
                    {
                        href: '/list',
                        title: 'Internships',
                        icon: 'fa fa-code'
                    },
                    {
                        // href: '#',
                        title: 'Reminder',
                        icon: 'fa fa-cog',
                        //TODO get reminders to change the badge number
                        badge: {
                            text: 'new',
                            class: 'badge-danger'
                        }
                    },
                    {
                        title: 'Sign out',
                        icon: 'fa fa-code'
                    },
                    {
                        href: '/test',
                        title: 'test',
                        icon: 'fa fa-code'
                    }
                ]
=======
>>>>>>> 619547a40beffb85b1cd1cf2bd97dae3e9c614c2

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

      this.$on('authenticated', (value) => {
        if (!value) {
          this.$router.replace({ name: "login" });
        }
      })
      if (this.authenticated === false) {
        this.$router.replace({ name: "login" });
      }
    },
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
