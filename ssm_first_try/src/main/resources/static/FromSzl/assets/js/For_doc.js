var url ="http://localhost:8080/";
const doc = {
    data(){
        return{
            date1:'',
            date2:'',
            date3:'',
            date4:'',
            date5:'',
            date6:'',
            date7:'',

            office:'',
            name:'',
            info:'',
            skill:'',
            id:'',
            morning:[],
            afternoon:[],
            night:[],

            msg:'',
            code:0
        }
    },
    methods:{
        getData(){
            let id = localStorage.getItem("doc_id");
            console.log(id)
            axios({
                url:url+"doctor/"+id,
                method:"GET",
            }).then((res)=>{
                this.morning = res.data.data.morning;
                this.afternoon = res.data.data.afternoon;
                this.night = res.data.data.night;
                this.info = res.data.data.info;
                this.skill = res.data.data.skill;
                this.name = res.data.data.name;
                this.id = res.data.data.id;
                this.office = res.data.data.office;
                console.log(this.info)
            })
        },
        getMyDate(){
            let dateForm = new Date().toISOString();
            let date = new Date(dateForm).toJSON();
            this.date1 = new Date(+new Date(date)+ 8 * 3600 * 1000).toISOString().substring(0,10);
            this.date2 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 3).toISOString().substring(0,10);
            this.date3 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 6).toISOString().substring(0,10);
            this.date4 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 9).toISOString().substring(0,10);
            this.date5 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 12).toISOString().substring(0,10);
            this.date6 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 15).toISOString().substring(0,10);
            this.date7 = new Date(+new Date(date)+ 8 * 3600 * 1000 + 8 * 3600 * 1000 * 18).toISOString().substring(0,10);
        }
    },
    beforeMount(){
        this.getData();
        this.getMyDate();
    }
}
const app = Vue.createApp(doc)
app.mount("#doc")