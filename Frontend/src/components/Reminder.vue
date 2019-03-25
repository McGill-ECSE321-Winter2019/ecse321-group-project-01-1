<template>
  <div id="reminders">
    <h2>Reminders</h2>
    <b-table hover :items="reminders_list" :fields="fields"/>
  </div>
</template>

<script>
  function ReminderDto(message, createTime) {
      this.message = message;
      this.createTime = createTime;
  }

  export default {
    props: {
      onGuestRedirect: { type: Function },
    },
    data() {
      return {
        fields: [
          {
            key: 'message',
            sortable: false
          },
          {
            key: 'create_date_time',
            label: 'Time created',
            sortable: true
          }
        ],
        reminders_list: []
      }
    },
    created: function () {
      this.onGuestRedirect();
      this.$http.get('/api/reminders')
        .then(response => {
            this.reminders_list = response.data
        })
        .catch(e => {
            this.errorreminder = e;
        });
    }
  }
</script>

<style>
</style>

