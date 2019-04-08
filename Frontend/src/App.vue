//Main page for describing frontend

<template>
  <div id="app">
    <Navbar :student="student" @authenticated="authenticate"></Navbar>
    <div  v-bind:class="{'my-5': !$route.meta.no_container, container: !$route.meta.no_container}">
      <router-view :onGuestRedirect="onGuestRedirect" @authenticated="authenticate"> </router-view>
    </div>

   <div class="fixed-footer">ECSE321 - Group01 - McGill Winter 2019</div>
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
		  -webkit-font-smoothing: antialiased;
		  -moz-osx-font-smoothing: grayscale;
		  color: #2c3e50;
	}

  
	.fixed-footer {
		  position:fixed;
		  bottom:0px;
		  left:0px;
		  width:100%;
		  color:#CCC;
		  background:#333;
		  padding:8px;
		  z-index: 99999;
	}
</style>
