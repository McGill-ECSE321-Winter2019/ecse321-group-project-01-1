<template>
    <div id="internship">
        <div class="container">
            <h2> First internship</h2>
            <b-button> Application Form</b-button>
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
                <b-button type="file" @change="onFileChange">Choose File</b-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['internship'],
        name: "InternshipItem",
        data (){
            return{
                selectedDocument :'Contract',
                DocumentTypes: ['Contract','Work report', 'Technical report', 'Evaluation']
            }
        },
        methods:{
            upload: function (formData) {
                const url = `${BASE_URL}/photos/upload`;
                return this.$http.post(url, formData)
                // get data
                    .then(x => x.data)
                    // add url field
                    .then(x => x.map(img => Object.assign({},
                        img, { url: `${BASE_URL}/images/${img.id}` })));
            }
        }
    }
</script>

<style scoped>

</style>
