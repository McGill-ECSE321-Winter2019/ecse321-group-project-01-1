<template>
  <b-container>
    <b-row align-v="center"align-h="center">
      <b-col cols="4" >
        <b-alert show v-show="!!error" variant="danger">Incorrect email or password.</b-alert>

        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
          <b-form-group
            id="exampleInputGroup1"
            label="Email address:"
            label-for="email"
          >
            <b-form-input
              id="email"
              type="email"
              v-model="form.email"
              required
              placeholder="Enter email" />
          </b-form-group>

          <b-form-group id="exampleInputGroup2" label="Password:" label-for="password">
            <b-form-input
              id="password"
              type="password"
              v-model="form.password"
              required
              placeholder="Enter password" />
          </b-form-group>

          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </b-col col>
    </b-row>
  </b-container>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          email: '',
          password: '',
        },
        error: false,
        show: true
      }
    },


    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        this.$http.post(`/login`, this.$jsonToFormData(this.form))
        .then(response => {
          // JSON responses are automatically parsed.
          this.$emit("authenticated", true);
          this.$nextTick(() =>{
            this.$router.replace({ name: "profile" });
          })
        })
        .catch(e => {
          this.error = true
        });
      },
      onReset(evt) {
        evt.preventDefault()
        /* Reset our form values */
        this.form.email = ''
        this.form.password = ''

        /* Trick to reset/clear native browser form validation state */
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
  }
</script>