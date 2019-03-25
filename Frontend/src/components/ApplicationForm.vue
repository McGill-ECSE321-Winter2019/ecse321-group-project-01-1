<template>
  <div>
    <table>
      <tr>
        Enter the myFuture jobID (optional)
      </tr>
      <tr>
        <input type="text" v-model="jobId" placeholder="jobId">
      </tr>
      <tr>
        <input type="text" v-model="jobDescription" placeholder="jobDescription">
      </tr>
      <tr>
        <input type="text" v-model="employer" placeholder="employer">
      </tr>
      <tr>
        <input type="text" v-model="location" placeholder=" location">
      </tr>
      <tr>
        <input type="date" v-model="startDate" placeholder="startDate">
      </tr>
      <tr>
        <input type="date" v-model="endDate" placeholder="endDate">
      </tr>
      <tr>
        <td>WorkPermit</td>
        <td>
          <input type="checkbox" id="checkbox" v-model="WorkPermit">
          <label for="checkbox">{{ workPermit }}</label>
        </td>
      </tr>
      <tr>
        <b-button v-on:click="createApplicationForm(jobId, jobDescription, employer, location, startDate, endDate, workPermit)">Create Person</b-button>
      </tr>
    </table>
  </div>
</template>

<script src="./form.js">
</script>

<style scoped>

</style>
