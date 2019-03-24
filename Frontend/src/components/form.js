function ApplicationFormDto ( jobID, jobDescrioption, employer, location, startDate, endDate, workPermit){
    this.jobID = jobID
    this.jobDescrioption = jobDescrioption
    this.employer = employer
    this.location = location
    this.startDate = startDate
    this.endDate = endDate
    this.workPermit = workPermit
}

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
    createApplicationForm: function (formjobId, formjobDescription, formemployer, formlocation, formstartDate, formendDate, formworkPermit){
      var f = new ApplicationFormDto(formid, formjobId, formjobDescription, formemployer, formlocation, formstartDate, formendDate, formworkPermit)
      this.applicationforms.push(f)
      this.newApplicationForm = ''
    }
  }
}

