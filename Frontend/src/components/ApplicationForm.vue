<template>
  <b-row>
    <b-col >
      <div v-if="new_form && !editing">
        <div class="mb-3">No application form has been submitted.</div>
        <b-button @click="toggleEditing" variant="primary">New form</b-button>
      </div>
      <b-form @submit="createApplicationForm" v-if="!new_form || editing">
        <b-form-group>
          <label for="jobID">Your Job ID</label>
          <input type="text" id="jobID" class="form-control" v-model="ApplicationForm.job_id" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="jobDescription">Enter Job Description</label>
          <input type="text" id="jobDescription" class="form-control" v-model="ApplicationForm.job_description" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="employer">Your Employer</label>
          <input type="text" id="employer" class="form-control" v-model="ApplicationForm.employer" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="location">Enter the Location</label>
          <input type="text" id="location" class="form-control" v-model="ApplicationForm.location" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="startDate">Start Date</label>
          <input type="date" id="startDate" class="form-control" v-model="ApplicationForm.start_date" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="endDate">End Date</label>
          <input type="date" id="endDate" class="form-control" v-model="ApplicationForm.end_date" v-bind:readonly="!editing">
        </b-form-group>
        <b-form-group>
          <label for="checkbox">Do you require a work permit?</label>
          <input type="checkbox" id="checkbox" v-model="ApplicationForm.workPermit" v-bind:readonly="!editing">
        </b-form-group>
        <b-button v-if="editing" type="submit" variant="primary">Submit</b-button>
        <b-button v-if="editing" @click="toggleEditing" variant="danger">Cancel</b-button>
        <b-button v-if="!editing" @click="toggleEditing" variant="primary">Edit</b-button>
      </b-form>

    </b-col>
  </b-row>
</template>

<script>
   export default{
    props:['selectedInternship'],
    created() {
      if (this.selectedInternship.application_form != null) {
        this.ApplicationForm = this.selectedInternship.application_form
        this.new_form = false
      }
    },
    watch: {
      selectedInternship() {
        if (this.selectedInternship.application_form != null) {
          this.ApplicationForm = this.selectedInternship.application_form
          this.new_form = false
        }
      }
    },
    data() {
       return{
           ApplicationForm:{
               job_id:'',
               job_description:'',
               employer:'',
               location:'',
               start_date:'',
               end_date:'',
               work_permit:true
           },
           editing: false,
           new_form: true,
       }
    },
     methods: {
        toggleEditing(evt) {
          evt.preventDefault()
          this.editing = !this.editing
        },
       createApplicationForm(evt){
        evt.preventDefault()
        if (this.new_form) {
          this.$http.post('/api/internships/'+ this.selectedInternship.id +'/application_form',  null, {
            params: this.ApplicationForm
          }).then( (response) => {
            this.editing = false;
            this.$emit("refresh")
            console.log(response);
          }).catch((error) => {
            console.log(error);
          });
        } else {
          this.$http.put('/api/internships/'+ this.selectedInternship.id +'/application_form',  null, {
            params: this.ApplicationForm
          }).then((response) => {
            this.editing = false;
            console.log(response);
          }).catch((error) => {
            console.log(error);
          });
        }

       },
       convertDate: function (date) {
         console.log(date);
         // return date.getFullYear().toString() + '-' + date.getMonth().toString() + '-' + date.getDay().toString()
       }
     }
   }
</script>

<style>
  #applicationform {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
