<template>
    <div id="internship" v-if="!!selectedInternship">
        <h2 >Internship {{ selectedInternship.academic_semester +  " " + selectedInternship.year}}</h2>
        <b-alert v-if="!!alert" show dismissible>
          {{alert}}
        </b-alert>
        <b-card no-body>
          <b-tabs pills card vertical>
            <b-tab title="Application Form" active>
                <b-card-text>
                    <ApplicationForm :selectedInternship="selectedInternship"></ApplicationForm>
                </b-card-text>
            </b-tab>
            <b-tab title="Documents">
                <h2> Summary of documents</h2>
                <b-card-text>
                    <div >
                        <h4 >Current internship listings</h4>
                        <b-table
                            borderless
                            hover
                            :items="getItems()"
                            :fields="fields"

                        >
                            <template slot="download" slot-scope="data">
                                <span v-if="selectedInternship.progress[data.index]">
                                     <a href="#" data-document-type="DocumentTypesDisp[data.index+1].value" v-on:click="downloadFile">{{getPathByType(DocumentTypesDisp[data.index+1].value).file_name}}</a>
                                </span>
                                <span v-else>Not Uploaded</span>

                            </template>
                        </b-table>
                                <!--Progress: {{numCompleted(selectedInternship.progress)}} out of 4 documents uploaded-->
                                <h4>Internship progress</h4>
                                <b-progress :value= numCompleted(selectedInternship.progress) :max=4 show-progress></b-progress>

                        <div>
                            <b>Upload document</b>
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
                                <b-button type="submit" variant="primary">Submit</b-button>
                            </b-form>
                        </div>
                    </div>
                </b-card-text>
            </b-tab>
          </b-tabs>
        </b-card>
    </div>
</template>

<script>
    import ApplicationForm from "./ApplicationForm";

    export default {
        components: {ApplicationForm},
        props: ['internship_id'],
        name: "InternshipItem",

        data (){
            return{
                alert: "",
                file: '',
                selectedInternship: null,
                documents_table: {
                    CONTRACT: null,
                    WORK_REPORT: null,
                    TECHNICAL_REPORT: null,
                    EVALUATION: null
                },
                error:'',
                selectedDocument: null,
                DocumentTypesDisp: [
                {value: null, text: 'Please select a document type'},
                {value: 'CONTRACT', text: 'Contract'},
                {value: 'WORK_REPORT', text: 'Work report'},
                {value: 'TECHNICAL_REPORT', text: 'Technical report'},
                {value: 'EVALUATION', text: 'Evaluation'}
                ],
                items:[

                    {type:'Contract', status:'Awaiting Upload', download: '<span>Ok</span>'},
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
        created: function () {
            this.fetch()
        },
        methods:{
            fetch() {
                this.$http.get(`/api/internships/` + this.internship_id.toString())
                    .then(response => {
                        this.selectedInternship = response.data
                        for(var i =0;i<this.selectedInternship.document.length; i++){
                            this.documents_table[this.selectedInternship.document[i].document_type] = this.selectedInternship.document[i]
                        }
                    })
                    .catch(e => {
                        this.error = e;
                    });
            },
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
                let formData = new FormData();
                formData.append('file', this.file);

                this.$http.post(`/api/internships/`+ this.selectedInternship.id.toString() +`/documents`,
                    formData, {
                        params: {
                            type: this.selectedDocument
                        }
                    })
                .then(() => {
                    this.fetch()
                    this.alert = "File successfully uploaded!"

                    console.log('SUCCESS!!');
                })
                .catch( (e) => {
                    console.log(e);
                });
            },
            downloadFile(evt){
                evt.preventDefault()
                let type = evt.target.attributes['data-document-type'].value
                let d = this.getPathByType(type)
                this.$http({
                    // url: "http://127.0.0.1:8081/api/internships/1/documents/5e86f66b-74d2-4e96-82e7-9a76b35855d3/download",
                    url: "/api/internships/"+this.internship_id.toString()+"/documents/" + d.id +"/download",
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

<style scoped>

</style>
