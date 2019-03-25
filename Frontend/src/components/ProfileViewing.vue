<template>
  <div id="profileviewing">
    <h2>Profile Menu</h2>
    <table>
      <tr>
          <td>
          Student ID: {{student.student_id}}<br>
          First Name: {{student.first_name}}<br>
          Last Name: {{student.last_name}}<br>
          Email: {{student.email}}<br>
          Password: {{student.password}}
          </td>
      </tr>
      <br>
    </table>
    <br>
        <td>
			Current Password: <input type="text" name="oldPass" value="old"><br>
			New Password: <input type="text" name="newPass" value="new"><br>
        <button @click="changePassword(oldPass,newPass)">Change Password</button>
			
		</form>
		</td>
  </div>   
</template>

<script src="./PViewing.js">
</script>
<style>
</style>
