<template>
    <div id="internship">
        <div class="container">
            <h2> First internship</h2>
            <b-button> Application Form</b-button>
            <div v-if="">
                <table v-if="internship.progress">
                    <tr>
                        Contract status:
                        <body v-if="internship.progress[0]">
                        completed
                        <b-button v-on:click="downloadFile">Download</b-button>
                        <b-button>Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload

                        </body>

                    </tr>
                    <tr>
                        Work report status:
                        <body v-if="internship.progress[1]">
                        completed
                        <b-button>Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </tr>
                    <tr>
                        Technical report status:
                        <body v-if="internship.progress[2]">
                        completed
                        <b-button>Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </tr>
                    <tr>
                        Evaluation status:
                        <body v-if="internship.progress[2]">
                        completed
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </tr>
                    <tr>
                        Progress: {{numCompleted(internship.progress)}} out of 4 documents uploaded
                    </tr>
                </table>
            </div>
            <h3>Upload Documents</h3>
            <div>
                <select v-model="selectedDocument">
                    <option
                        v-for="(docType, index) in DocumentTypesDisp"
                        :key="index">
                        {{ docType == '' ? 'selectedDocument' : docType }}
                    </option>
                </select>
                <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" >
                <button v-on:click="submitFile">Submit</button>
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
                file: '',
                num_completed: '',
                application: true,
                selectedDocument :'Contract',
                DocumentTypesDisp: ['CONTRACT','WORK_REPORT', 'TECHNICAL_REPORT', 'EVALUATION']
            }
        },
        created: function () {
        },
        methods:{
            numCompleted: function (arr) {
                var count = 0;
                let i;
                for(i=0;i<arr.length;i++){
                    if(arr[i]){
                        count++;
                    }
                }
                return count;
            },
            submitFile() {
                let formData = new FormData();
                formData.append('file', this.file);

                this.$http.post('/api/internships/'+ this.internship.id.toString() +'/documents',
                    formData, {
                        params: {
                            type: this.selectedDocument
                        }
                    }
                ).then(function () {
                    console.log('SUCCESS!!');
                })
                    .catch(function () {
                        console.log('FAILURE!!');
                    });
            },
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
                console.log('>>>> 1st element in files array >>>> ', this.file);
            },
            // downloadFile(){
            //     axios({
            //         url: 'http://api.dev/file-download',
            //         method: 'GET',
            //         responseType: 'blob', // important
            //     }).then((response) => {
            //         const url = window.URL.createObjectURL(new Blob([response.data]));
            //         const link = document.createElement('a');
            //         link.href = url;
            //         link.setAttribute('download', 'file.pdf'); //or any other extension
            //         document.body.appendChild(link);
            //         link.click();
            //     });
            // }
        }
    }
</script>

<style scoped>

</style>
