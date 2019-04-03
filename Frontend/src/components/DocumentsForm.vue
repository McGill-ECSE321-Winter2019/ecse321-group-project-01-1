<template>
    <div id="documents">
        <b-row>
            <b-col >
                <b-alert v-if="!!form.info" show dismissible>
                    {{form.info}}
                </b-alert>
                <b-alert v-if="!!form.error" variant="danger" show dismissible>
                    {{form.error}}
                </b-alert>
                <h4> Summary of documents</h4>
                <h5 >Current internship listings</h5>
                <b-table
                    outlined
                    :items="getItems()"
                    :fields="fields"
                >
                    <template slot="download" slot-scope="data">
                        <span v-if="selectedInternship.progress[data.index]">
                             <a href="#" v-on:click="downloadFile(DocumentTypesDisp[data.index+1].value)">{{getPathByType(DocumentTypesDisp[data.index+1].value).file_name}}</a>
                        </span>
                        <span v-else>Not Uploaded</span>

                    </template>
                </b-table>
                <h5>Internship progress</h5>
                <b-progress :value= numCompleted(selectedInternship.progress) :max=4 show-progress></b-progress>

                <div class="mt-4">
                    <h4>Upload document</h4>
                    <b-form @submit="submitFile">
                        <b-form-group>
                            <b-form-select v-model="selectedDocument" :options="DocumentTypesDisp" />
                        </b-form-group>
                        <b-form-group>
                            <b-form-file
                              v-model="file"
                              placeholder="Choose a file..."
                              drop-placeholder="Drop file here..."
                            />
                        </b-form-group>
                        <b-button type="submit" variant="primary" :disabled="form.submitted"><b-spinner small v-if="form.submitted"></b-spinner>Submit</b-button>
                    </b-form>
                </div>
            </b-col>
        </b-row>
    </div>
</template>

<script>
    export default {
        props:['selectedInternship'],
        name: "DocumentsForm",

        data (){
            return{
                file: '',
                documents_table: {
                    CONTRACT: null,
                    WORK_REPORT: null,
                    TECHNICAL_REPORT: null,
                    EVALUATION: null
                },
                form: {
                    submitted: false,
                    info: "",
                    error: "",
                },
                selectedDocument: null,
                
                DocumentTypesDisp: [
                {value: null, text: 'Please select a document type'},
                {value: 'CONTRACT', text: 'Contract'},
                {value: 'WORK_REPORT', text: 'Work report'},
                {value: 'TECHNICAL_REPORT', text: 'Technical report'},
                {value: 'EVALUATION', text: 'Evaluation'}
                ],
                items:[

                    {type:'Contract', status:'Awaiting Upload', download: 'None'},
                    {type:'Work Report', status:'Awaiting Upload', download: 'None'},
                    {type:'Technical Report', status:'Awaiting Upload', download: 'None'},
                    {type:'Evaluation', status:'Awaiting Upload', download: 'None'}

                ],
                fields:[
                    {
                        key:'type',
                        label:'Type'
                    },
                    {
                        key:'status',
                        label:'Status'
                    },
                    {
                        key:'download',
                        label:'Download Link'
                    },
                ]
            }
        },
        watch: { 
            selectedInternship: function(newVal, oldVal) { // watch it
                for(var i =0;i<this.selectedInternship.document.length; i++){
                    this.documents_table[this.selectedInternship.document[i].document_type] = this.selectedInternship.document[i]
                }
          }
        },
        created() {
            for(var i =0;i<this.selectedInternship.document.length; i++){
                this.documents_table[this.selectedInternship.document[i].document_type] = this.selectedInternship.document[i]
            }
        },
        methods:{
            getItems(){
                let copy = this.items;
                let i=0;
                for(i;i<4;i++){
                    if(this.selectedInternship.progress[i]){
                        copy[i].status = "Done";
                    }
                    else{
                        copy[i].status = "Awaiting upload"
                    }
                }
                return copy;
            },
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
            submitFile(evt) {
                evt.preventDefault()
                this.form.submitted = true;
                this.form.error = "";
                this.form.info = "";

                let formData = new FormData();
                formData.append('file', this.file);

                this.$http.post(`/api/internships/`+ this.selectedInternship.id.toString() +`/documents`,
                    formData, {
                        params: {
                            type: this.selectedDocument
                        }
                    })
                .then(() => {
                    this.$emit("fetch", true);
                    this.form.info = "File successfully uploaded!"
                    this.form.submitted = false;
                    console.log('SUCCESS!!');
                })
                .catch( (e) => {
                    console.log(e);
                    this.form.error = "An error occurred while submitting the document."
                    this.form.submitted = false;
                });
            },
            downloadFile(type){
                // evt.preventDefault();
                // let type = evt.target.attributes['data-document-type'].value;
                let d = this.getPathByType(type);
                this.$http({
                    // url: "http://127.0.0.1:8081/api/internships/1/documents/5e86f66b-74d2-4e96-82e7-9a76b35855d3/download",
                    url: "/api/internships/"+this.selectedInternship.id.toString()+"/documents/" + d.id +"/download",
                    method: 'GET',
                    responseType: 'blob',
                }).then((response) => {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', d.file_name); //or any other extension
                    document.body.appendChild(link);
                    link.click();
                }).catch(function () {
                    console.log('Failed to download!!');
                });
            },
            getPathByType(type){
                return this.documents_table[type];
            },
        }
    }
</script>
