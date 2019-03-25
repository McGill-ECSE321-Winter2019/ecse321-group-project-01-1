<template>
  <div class="form-group">
     +<label for="jobID">Your Job ID</label>
     <input type="text" id="jobID" class="form-control" v-model="ApplicationForm.jobID">
   </div>
   <div class="form-group">
     <label for="jobDescription">Enter Job Description</label>
     <input type="text" id="jobDescription" class="form-control" v-model="ApplicationForm.jobDescription">
   </div>
   <div class="form-group">
     <label for="employer">Your Employer</label>
     <input type="text" id="employer" class="form-control" v-model="ApplicationForm.employer">
   </div>
   <div class="form-group">
     <label for="location">Enter the Location</label>
     <input type="text" id="location" class="form-control" v-model="ApplicationForm.location">
   </div>
   <div class="form-group">
     <label for="startDate">Start Date</label>
     <input type="date" id="startDate" class="form-control" v-model="ApplicationForm.startDate">
   </div>
   <div class="form-group">
     <label for="endDate">End Date</label>
     <input type="date" id="endDate" class="form-control" v-model="ApplicationForm.endDate">
   </div>
   <div class="form-group">
     <input type="checkbox" id="checkbox" v-model="ApplicationForm.workPermit">
     <label for="checkbox">{{ workPermit }}</label>
  </div>
</template>

<script>
   export default{
       data() {
           return{
               ApplicationForm:{
                   JobID:'',
                   jobDescription:'',
                   employer:'',
                   location:'',
                   startDate:'DD-MM-YYYY',
                   endDate:'DD-MM-YYYY',
                   workPermit:true

               }
           }
       }
   }

methods: {
   createApplicationForm: function (internshipId){
      this.$http.post('/api/internships/'+ internshipId.toString() +'/application_form',  null, {
        params:{
          job_id :jobID,
          job_description: jobDescription,
          employer: employer,
          location :location,
          start_date: startDate,
          end_date :endDate,
          work_permit :workPermit
        }
      }).then(function (response) {
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      });
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
