//Defines the webform for filling out an application form

<template>
  <b-row>
    <b-col >
      <b-alert v-if="!!form.info" show dismissible>
        {{form.info}}
      </b-alert>
      
      <b-alert v-if="!!form.error" variant="danger" show dismissible>
        {{form.error}}
      </b-alert>
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
          <label for="company">Your Company</label>
          <input type="text" id="company" class="form-control" v-model="ApplicationForm.company" v-bind:readonly="!editing">
        </b-form-group>
        
        <b-form-group>
          <label for="employer">Your Employer</label>
          <input type="text" id="employer" class="form-control" v-model="ApplicationForm.employer" v-bind:readonly="!editing">
        </b-form-group>
        
        <b-form-group>
              <label for="employer_email">Your Employer's contact email</label>
              <input type="text" id="employer_email" class="form-control" v-model="ApplicationForm.employer_email" v-bind:readonly="!editing">
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
        <b-button v-if="editing" type="submit" variant="primary" :disabled="form.submitted"><b-spinner small v-if="form.submitted"></b-spinner> Submit</b-button>
        <b-button v-if="editing" @click="toggleEditing" variant="danger">Cancel</b-button>
        <b-button v-if="!editing" @click="toggleEditing" variant="primary" :disabled="form.submitted"><b-spinner small v-if="form.submitted"></b-spinner> Edit</b-button>
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
               company:'',
               employer:'',
               employer_email: '',
               location:'',
               start_date:'',
               end_date:'',
               work_permit:true
           },
           form: {
            submitted: false,
            error: "",
            info: ""
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
        this.form.error = "";
        this.form.submitted = true;

        if (this.new_form) {
          this.$http.post('/api/internships/'+ this.selectedInternship.id +'/application_form',  null, {
            params: this.ApplicationForm
          }).then((response) => {
            this.editing = false;
            this.new_form = false;
            this.form.submitted = false;
          }).catch((error) => {
            console.log(error);
            this.form.error = "An error occurred while submitting the form."
            this.form.submitted = false;
          });
        } else {
          this.$http.put('/api/internships/'+ this.selectedInternship.id +'/application_form',  null, {
            params: this.ApplicationForm
          }).then((response) => {
            this.editing = false;
            this.form.submitted = false;
          }).catch((error) => {
            console.log(error);
            this.form.error = "An error occurred while submitting the form."
            this.form.submitted = false;
          });
        }

       },
     }
   }
</script>

<style>

</style>
