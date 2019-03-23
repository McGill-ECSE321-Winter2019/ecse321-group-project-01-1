import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: {'Access-Control-Allow-Origin': frontendUrl}
})

export default {
  name: 'documents',
  data() {
    return {
      people: [],
      newPerson: '',
      errorPerson: '',
      response: []
    }
  },
  methods: {
    createPerson: function (personName) {
      AXIOS.post(`/persons/` + personName, {}, {})
        .then(response => {
          // JSON responses are automatically parsed.
          this.people.push(response.data)
          this.newPerson = ''
          this.errorPerson = ''
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        });
    }
  }
}
