<template>
    <div id="internship_listing">
        <template v-if="listing">
            <h2>Current internship listings</h2>
            <b-table
                borderless
                hover
                :items="getItems()"
                :fields="['year', 'academic_semester', 'course']"
                @row-clicked="showInternship"

            ></b-table>
        </template>
        <template v-else>
            <internship-item v-bind:internship_id="currentInternship.id"></internship-item>
            <a href="#" v-on:click="back"> Back</a>
        </template>
    </div>
</template>

<script>
    import InternshipItem from "./InternshipItem";
    export default {
        name: "internship_listing",
        components: {InternshipItem},
        props: {
          onGuestRedirect: { type: Function },
        },
        data() {
            return {
                internshipList: [],
                error: false,
                listing: true,
                currentInternship: '',
            }
        },
        created: function () {
            this.onGuestRedirect();
            this.$http.get(`/api/internships`)
                .then(response => {
                    this.internshipList = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        methods: {

            showInternship: function(internship, index){
                this.currentInternship = this.internshipList[index];
                this.listing = !this.listing;
            },
            getItems() {
                return this.internshipList.map((el) => {
                    let copy  = Object.assign({}, el)
                    copy.course = el.course.course_id
                    return copy
                })
            },
            back: function(){
                this.listing = !this.listing;
            }
        }
    }
</script>

<style>
	#internship_listing td {
    	cursor: pointer;
	}
</style>
