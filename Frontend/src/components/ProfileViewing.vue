<template>
  <div id="profileviewing">
    <h2>Profile Menu</h2>
    <b-form>
      <b-form-group>
        <label for="id">Student ID</label>
        <input type="text" id="id" class="form-control-plaintext" v-model="student.student_id" readonly>
      </b-form-group>
      <b-form-group>
        <label for="first_name">First Name</label>
        <input type="text" id="first_name" class="form-control-plaintext" v-model="student.first_name" readonly>
      </b-form-group>
      <b-form-group>
        <label for="last_name">Last Name</label>
        <input type="text" id="last_name" class="form-control-plaintext" v-model="student.last_name" readonly>
      </b-form-group>
      <b-form-group>
        <label for="email">Email</label>
        <input type="text" id="email" class="form-control-plaintext" v-model="student.email" readonly>
      </b-form-group>
    </b-form>
  </div>   
</template>

<script>


  export default {
      name: 'Viewing',
      props: {
        onGuestRedirect: { type: Function },
      },
      data() {
          return {
              student: null,
              errorStudent: '',
              response: ''
          }
      },
      created: function () {
          this.onGuestRedirect();
          // Obtaining student from backend
          this.$http.get('api/profile')
              .then(response => {
                  // JSON responses are automatically parsed.
                  this.student = response.data
              })
              .catch(e => {
                  this.errorStudent = e;
              });
      }
  }

</script>
<style>
</style>
