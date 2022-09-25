var url ="http://localhost:8080/";
const loginApp1 = {
    data(){
        return{
            date1:'',
            date2:'',
            date3:'',
            date4:'',
            date5:'',
            date6:'',
            date7:'',
            doctor_data:{
                msg:'',
                data:[]
            },
            department_data:{
                msg:'',
                data:[]
            },
            code:0,
            msg:'',
        }
    },
    methods:{
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
        },
        getAllDoctor () {
            let p = {
                "name":"",
                "office":""
            }
            axios({
                url:url+"doctor/LNAO/",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                this.code=res.data.code;
                this.doctor_data.msg=res.data.msg;
                this.doctor_data.doc_list=res.data.data;
                console.log(this.doctor_data.doc_list)
            })
        },
        getAllDepartment () {
            let p = {
                "office":""
            }
            axios({
                url:url+"offices/LOAD/",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                this.code=res.data.code;
                this.department_data.msg=res.data.msg;
                this.department_data.data=res.data.data;
                console.log(this.department_data.data)
            })
        },
        Search_for_info(){
            let doc=document.getElementById("doctor_name").value;
            let dpm=document.getElementById("department_name").value;
            let ill=document.getElementById("illness_name").value;
            if(doc===''&&dpm===''&&ill===''){
                this.getAllDepartment();
            }
            //这里是查医生表单。疾病或医生名非空
            if(((ill!=''&&ill!=null)||(doc!=''&&doc!=null))){
                let p = {
                    "name":doc,
                    "skill":ill,
                    "office":dpm
                }
                console.log(p);
                axios({
                    url:url+"doctor/LEI/",
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    this.code=res.data.code;
                    this.doctor_data.msg=res.data.msg;
                    this.doctor_data.data=res.data.data;
                })
            }
            //这里是查科室表单。科室非空
            else{
                let p = {
                    "officeName":dpm,
                };
                console.log(p);
                axios({
                    url:url+"offices/LOAD/",
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    this.code=res.data.code;
                    this.department_data.msg=res.data.msg;
                    this.department_data.data=res.data.data;
                })
            }
        },
        gotodoc(id){
            localStorage.setItem("doc_id",id);
        },
        go_office(id,name){
            localStorage.setItem("dpm_id",id);
            localStorage.setItem("dpm_name",name);
        }
    },
    beforeMount() {
        this.getMyDate();
        this.getAllDoctor();
        this.getAllDepartment();
    }
}
const app3 = Vue.createApp(loginApp1)
/*app.use(router)*/
app3.mount("#Schedule")