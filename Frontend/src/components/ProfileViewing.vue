<template>
  <div id="profileviewing">
    <h2>Profile</h2>
    <table>
      <tr>
          <td>
          {{x}}
          Student ID: document.write(student.student_id)<br>
          First Name: {{this.student.first_name}}<br>
          Last Name: {{student.last_name}}<br>
          Email: {{student.email}}</td>
      </tr>
    </table>
  </div>   
</template>

<script src="./PViewing.js">
</script>
<style>
</style>