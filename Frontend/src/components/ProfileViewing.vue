<template>
  <div id="profileviewing">
    <h2>Profile Menu</h2>
    <table>
      <tr>
          <td>
          Student ID: {{student.student_id}}<br>
          First Name: {{student.first_name}}<br>
          Last Name: {{student.last_name}}<br>
          Email: {{student.email}}</td>
      </tr>
    </table>
    <div id="fixedfooter">ECSE321 - Group01 - McGill Winter 2019</div>
  </div>   
</template>

<script src="./PViewing.js">
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
