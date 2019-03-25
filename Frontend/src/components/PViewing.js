import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: {'Access-Control-Allow-Origin': frontendUrl}
})

function StudentDto(studentID, firstName, lastName, email) {
    this.studentID = studentID
    this.firstName = firstName
    this.lastName = lastName
    this.email = email
    this.password = password
}

export default {
    name: 'Viewing',
    data() {
        return {
            student: '',
            errorStudent: '',
            response: '',
            password: ''
        }
    },
    created: function () {
        // Obtaining student from backend
        this.$http.get('api/profile')
            .then(response => {
                // JSON responses are automatically parsed.
                this.student = response.data
            })
            .catch(e => {
                this.errorStudent = e;
            });
    },

    methods: {
    	changePassword: function (oldPass,newPass) {
    		if(oldPass == student.password){
    			student.setPassword(newPass)
    		}
    	}
   }
}
