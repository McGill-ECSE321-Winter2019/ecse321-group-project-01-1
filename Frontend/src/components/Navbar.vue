<template>

  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-container>
        <b-navbar-brand href="/">Home</b-navbar-brand>

        <b-navbar-toggle target="nav_collapse" />

        <b-collapse is-nav id="nav_collapse">
          <b-navbar-nav>
            <b-nav-item v-if="!!student" href="/#/list">Internships</b-nav-item>
            <b-nav-item v-if="!!student" href="/#/reminders">Reminder</b-nav-item>
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-button v-if="!student" href="/#/login" variant="outline-info">Login</b-button>
            <b-nav-item-dropdown v-if="!!student" right>
              <!-- Using button-content slot -->
              <template slot="button-content"><em>Hi {{ student.first_name }}</em></template>
              <b-dropdown-item href="/#/profile">Profile</b-dropdown-item>
              <b-dropdown-item href="#" @click="logout">Log out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>

      </b-container>

    </b-navbar>
  </div>

</template>

<script>

export default {
  name: 'navbar',
  props: ['student'],
  methods: {
    logout(evt) {
      evt.preventDefault();
      this.$http.get(`/logout`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.$emit("authenticated", false);
        this.$router.replace({ name: "" });
      })
      .catch(e => {
        this.$emit("authenticated", false);
        this.$router.replace({ name: "" });
      });
    }
  },
  watch: {
    student: function(newVal, oldVal) {
      
    }
  }
}


</script>

<style>

</style>
