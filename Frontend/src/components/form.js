export default{
    name: 'applicationform',
    data(){
        return{
            applicationforms: [],
            newApplicationForm: '',
            errorApplicationForm: '',
            response: []
        }
    },

    created: function(){
        //add code
    },
  methods:{
    createApplicationForm: function (formjobId, formjobDescription, formemployer, formlocation, formstartDate, formendDate, formworkPermit,internshipId){
      this.$http.post('/api/internships/'+ internshipId.toString() +'/application_form',  null, {
        params:{
          job_id :formjobId,
          job_description: formjobDescription,
          employer: formemployer,
          location :formlocation,
          start_date: formstartDate,
          end_date :formendDate,
          work_permit :formworkPermit
        }
      }).then(function (response) {
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      });
    }
  }
}

