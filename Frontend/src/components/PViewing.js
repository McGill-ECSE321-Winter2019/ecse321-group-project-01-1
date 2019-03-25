import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function StudentDto(studentID, firstName, lastName, email) {
        this.studentID = studentID
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
}

export default {
  name: 'Viewing',
  const x = 1225,
  data () {
    return {
      student: '',
      errorStudent: '',
      response: ''   
    }
  },
  //...
}


created: {
	  // Obtaining student from backend
	    AXIOS.get('http://localhost:8080/api/profile')
	    .then(response => {
	      // JSON responses are automatically parsed.
	      this.student = response.data
	    })
	    .catch(e => {
	      this.errorStudent = e;
	    });
	}  
  
/*
methods: {
	createStudent: function (personName) {
		  AXIOS.post(`/external/students/`+studentID, {}, {})
		  .then(response => {
		    // JSON responses are automatically parsed.
		    this.people.push(response.data)
		    this.newPerson = ''
		    this.errorPerson = ''
		  })
		  .catch(e => {
		    var errorMsg = e.message
		    console.log(errorMsg)
		    this.errorStudent = errorMsg
		  });
		}
	  }
	}
  */
 