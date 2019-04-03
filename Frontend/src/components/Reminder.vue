<template>
  <div id="reminders">
    <h2>Reminders</h2>
    <b-table hover :items="getTableItems()" :fields="fields"/>
    <div id="fixedfooter">ECSE321 - Group01 - McGill Winter 2019</div>
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
    },
    methods: {
      getTableItems() {

        return this.reminders_list.map((el) => {
            let copy  = Object.assign({}, el)
            let T = new Date(el.create_date_time)
            copy.create_date_time = T.toString().split(" ").slice(0, 5).join(" ")
            return copy
        }) 

      }
    }

  }
</script>

<style>
	div#fixedfooter {
		position:fixed;
		bottom:0px;
		left:0px;
		width:100%;
		color:#CCC;
		background:#333;
		padding:8px;
	}
</style>

