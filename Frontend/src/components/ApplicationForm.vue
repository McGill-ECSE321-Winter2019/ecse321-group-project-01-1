<template>
  <div id="applicationform">
    <table>
      <tr>
          <input type="number" v-model="id" placeholder="form id">
      </tr>
      <tr>
          <input type="text" v-model="jobID" placeholder="jobID">
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
           <button @click="createcreateApplicationForm(id, jobID, jobDescrioption, employer, location, startDate, endDate, workPermit)">Create Person</button>
      </tr>
     </table>
     <p> 
       <span v-if="errorApplicationForm" style="color:red">Error: {{errorApplicationForm}} </span>
     </p>
  </div>  
</template>    