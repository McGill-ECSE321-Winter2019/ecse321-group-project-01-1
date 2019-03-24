<template>
    <div id="internship">
        <div class="container">
            <h2> First internship</h2>
            <b-button> Application Form</b-button>
            <div >
                <div v-if="selectedInternship.progress">
                    <div>
                        Contract status:
                        <body v-if="selectedInternship.progress[0]">
                        completed
                        <b-button v-on:click="downloadFile('CONTRACT')">Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload

                        </body>

                    </div>
                    <div>
                        Work report status:
                        <body v-if="selectedInternship.progress[1]">
                        completed
                        <b-button v-on:click="downloadFile()">Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </div>
                    <div>
                        Technical report status:
                        <body v-if="selectedInternship.progress[2]">
                        completed
                        <b-button>Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </div>
                    <div>
                        Evaluation status:
                        <body v-if="selectedInternship.progress[3]">
                        completed
                        <b-button>Download</b-button>
                        </body>
                        <body v-else>
                        Awaiting upload
                        </body>
                    </div>
                    <div>
                        Progress: {{numCompleted(selectedInternship.progress)}} out of 4 documents uploaded
                    </div>
                </div>
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
        props: ['internship_id'],
        name: "InternshipItem",


        data (){
            return{
                file: '',
                num_completed: '',
                application: true,
                selectedInternship: '',
                error:'',
                selectedDocument :'Contract',
                DocumentTypesDisp: ['CONTRACT','WORK_REPORT', 'TECHNICAL_REPORT', 'EVALUATION']
            }
        },
        created: function () {
            this.$http.get(`/api/internships/` + this.internship_id.toString())
                .then(response => {
                    this.selectedInternship = response.data
                })
                .catch(e => {
                    this.error = e;
                });
        },
        methods:{
            numCompleted: function (arr) {
                let count = 0;
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

                this.$http.post(`/api/internships/`+ this.selectedInternship.id.toString() +`/documents`,
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
            downloadFile(type){

                this.$http({
                    url: this.getPathByType(type),
                    method: 'GET',
                    responseType: 'blob',
                }).then((response) => {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'file.pdf'); //or any other extension
                    document.body.appendChild(link);
                    link.click();
                }).catch(function () {
                    console.log('Failed to download!!');
                });
            },
            getPathByType(type){
              for(var i =0;i<this.selectedInternship.document.length; i++){
                  if(this.selectedInternship.document[i].document_type == type){
                      return this.selectedInternship.document[i].path;
                  }
              }
            },
        }
    }
</script>

<style scoped>

</style>
