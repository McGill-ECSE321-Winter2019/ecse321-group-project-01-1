<template>
    <div id="internship" v-if="!!selectedInternship">
        <h2 >Internship {{ selectedInternship.academic_semester +  " " + selectedInternship.year}}</h2>
        <b-alert v-if="!!alert" show dismissible>
          {{alert}}
        </b-alert>
        <b-card no-body>
          <b-tabs pills card>
            <b-tab title="Application Form" active>
                <b-card-text>
                    <ApplicationForm :selectedInternship="selectedInternship"></ApplicationForm>
                </b-card-text>
            </b-tab>
            <b-tab title="Documents">
                <b-card-text>
                    <DocumentsForm :selectedInternship="selectedInternship" @fetch="fetch"></DocumentsForm>
                </b-card-text>
            </b-tab>
          </b-tabs>
        </b-card>
    </div>
</template>

<script>
    import ApplicationForm from "./ApplicationForm";
    import DocumentsForm from "./DocumentsForm";

    export default {
        components: {ApplicationForm, DocumentsForm},
        props: ['internship_id'],
        name: "InternshipItem",

        data (){
            return{
                selectedInternship: null,
            }
        },
        created: function () {
            this.fetch()
        },
        methods:{
            fetch() {
                this.$http.get(`/api/internships/` + this.internship_id.toString())
                    .then(response => {
                        this.selectedInternship = response.data
                    })
                    .catch(e => {
                        this.error = e;
                    });
            },
        }
    }
</script>

<style scoped>

</style>
