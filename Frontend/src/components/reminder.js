import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ReminderDto(message, createTime) {
    this.message = message;
    this.createTime = createTime;
  }

  export default {
    data() {
      return {
        Reminder: {
          message: "",
          createTime: ""
        }
      }
    },
    created: function () {
        this.$http.get('api/reminders')
            .then(response => {
                this.Reminder = response.data
            })
            .catch(e => {
                this.errorreminder = e;
            });
    }


  }