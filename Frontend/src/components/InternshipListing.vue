<template>
    <div id="internshipListing">
        <template v-if="listing">
            <h2>Current internship listings</h2>
            <table>
                <tr v-for="internship in internshipList">
                    <td>{{ internship.id }}</td>
                </tr>
            </table>
            <button v-on:click="showInternship"> Click me</button>
        </template>
        <template v-else>
            <h2> The other side</h2>
            <button v-on:click="showInternship"> Click me</button>

        </template>
    </div>
</template>

<script>
    function PersonDto (name) {
        this.id = name
        this.events = []
    }
    export default {
        name: "InternshipListing",
        data() {
            return {
                internshipList: [],
                error: false,
                listing: true,
                currentInternship: ''
            }
        },
        created: function () {

            const p1 = new PersonDto('John')
            const p2 = new PersonDto('Jill')
            // Sample initial content
            this.internshipList = [p1, p2]
            // Initializing people from backend
            // this.$http.get(`/api/internships`)
            //     .then(response => {
            //         // JSON responses are automatically parsed.
            //         this.internshipList = response.data
            //     })
            //     .catch(e => {
            //         this.error = e;
            //     });
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
