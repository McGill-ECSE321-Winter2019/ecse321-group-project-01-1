<template>
    <div id="internshipListing">
        <h2>Sup</h2>
        <table>
            <tr v-for="internship in internshipList">
                <td>{{ internship.name }}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    function PersonDto (name) {
        this.name = name
        this.events = []
    }
    export default {
        name: "InternshipListing",
        data() {
            return {
                internshipList: [],
                error: false
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
