<template>
    <div id="internship">
        <div class="container">
            <h2> First internship</h2>
            <button> Application Form</button>
                <table>

                    <tr>
                        Progress: 3 out of 4 documents uploaded
                    </tr>
                    <tr>
                        Next document due: March 30th
                    </tr>
                </table>
            <h3>Upload Documents</h3>
            <div>
                <select v-model="selectedDocument">
                    <option
                        v-for="(docType, index) in DocumentTypes"
                        :key="index"
                    >
                        {{ docType == '' ? 'selectedDocument' : docType }}
                    </option>
                </select>
                <input type="file" @change="onFileChange">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "InternshipItem",
        data (){
            return{
                selectedDocument :'Contract',
                DocumentTypes: ['Contract','Work report', 'Technical report', 'Evaluation']
            }
        }
    }
</script>

<style scoped>

</style>
