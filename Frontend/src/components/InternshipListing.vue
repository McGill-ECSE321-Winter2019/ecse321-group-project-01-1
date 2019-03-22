<template>
    <div>
        <h2>Sup</h2>
        <table>
            <tr v-for="internship in internshipList">
                <td>{{ internship.id }}</td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "InternshipListing",
        data() {
            return {
                internshipList: [],
                error: false
            }
        },
        created: function () {
            // Initializing people from backend
            this.$http.get(`/api/internships`)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.internshipList = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
    }
</script>

<style scoped>

</style>
