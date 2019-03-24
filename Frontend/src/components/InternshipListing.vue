<template>
    <div id="internshipListing">
        <template v-if="listing">
            <h2>Current internship listings</h2>
            <table>
                <tr v-for="internship in internshipList">
                    <td>{{ internship.id }}</td>
                </tr>
            </table>
            <b-button v-on:click="showInternship"> Click me</b-button>
        </template>
        <template v-else>
            <h2> The other side</h2>
            <b-button v-on:click="showInternship"> Click me</b-button>
            <internship-item internship=currentInternship></internship-item>
        </template>
    </div>
</template>

<script>
    import InternshipItem from "./InternshipItem";
    function PersonDto (name) {
        this.id = name
        this.events = []
    }
    function InternshipDto(id){
        this.id =id;
        this.course = "FACC300";
        this.progress = 3;
    }
    export default {
        name: "InternshipListing",
        components: {InternshipItem},
        data() {
            return {
                internshipList: [],
                error: false,
                listing: true,
                currentInternship: ''
            }
        },
        created: function () {
            //
            // const p1 = new PersonDto('John');
            // const p2 = new PersonDto('Jill');
            // const i1 = new InternshipDto(1);
            // const i2 = new InternshipDto(2);
            // // Sample initial content
            // this.internshipList = [i1, i2];
            // this.currentInternship = i1;

            this.$http.get(`/api/internships`)
                .then(response => {
                    this.internshipList = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        methods: {
            showInternship: function(internship){
                this.currentInternship = internship;
                this.listing = !this.listing
            }
        }
    }
</script>

<style>
    #internshipListing {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }
</style>
