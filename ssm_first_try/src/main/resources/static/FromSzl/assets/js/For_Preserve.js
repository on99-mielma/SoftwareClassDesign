var url ="http://localhost:8080/";

const app = {
    data(){
        return{
            date1:'',
            date2:'',
            date3:'',
            date4:'',
            date5:'',
            date6:'',
            date7:'',

            code:0,
            msg:'',
            dpmlist:[

            ],
            DocInDpm:[

            ],
            doctor_data:{
                msg:'',
                data:[]
            },

            Info:'',
            name:'',
            phone:'',
            doc:'',
            dpm:'',
            time:'',
            Puretime:''
        }
    },
    methods:{
        getAllDpm(){
            axios({
                url:url+"offices/2d/4",
                method:"GET",
            }).then((res)=>{;
                this.dpmlist = res.data.data;
            })
        },
        get_Doc(id,name){
            axios({
                url:url+"offices/2d/id/"+id+"/4",
                method:"GET",
            }).then((res)=>{
                this.DocInDpm = res.data.data.doctorBut2D;
                console.log(this.DocInDpm);
            })
            localStorage.setItem("dpm_select",name);
            document.getElementById("dpm_choose").innerText=name;
            document.getElementById("dpm_choose_describe").style.display='inline';
            document.getElementById("doc_choose_describe").style.display='none';
            document.getElementById("doc_choose").style.display='none';
            document.getElementById("doc_table").style.display='none';
            document.getElementById("time_choose_describe").style.display='none';
            document.getElementById("Msg").style.display="none";
        },
        choose(name){
            localStorage.setItem("doc_select",name);
            document.getElementById("doc_choose").innerText=name;
            document.getElementById("doc_choose_describe").style.display='inline';
            document.getElementById("doc_choose").style.display='inline';
            document.getElementById("doc_table").style.display='table';
            document.getElementById("time_choose_describe").style.display='none';
            document.getElementById("Msg").style.display="none";
        },
        setAllInfo(){
            this.Info = document.getElementById("ID").value;
            this.name = document.getElementById("name").value;
            this.phone = document.getElementById("phone").value;
            this.doc = localStorage.getItem("doc_select");
            this.dpm = localStorage.getItem("dpm_select");
            console.log(this.Info)
        },
        Preserve(){
            let articleDate = new Date().toISOString();
            let articleDateFinal = this.UTCtoGMT8(articleDate);
            let p = {
                "info":this.Info,
                "office":this.dpm,
                "doctor":this.doc,
                "name":this.name,
                "phone":this.phone,
                "date":articleDateFinal
            };
            axios({
                url:url+"queuereg/insert",
                method:"post",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                this.msg=res.data.msg;
                if(res.data.code==10011){
                    alert(res.data.msg);
                    window.location.href="Homepage_szl.html";
                }
            })
            document.getElementById("Msg").style.display="inline";
        },
        getDocData(doc){
            let p = {
                "name":doc,
            }
            axios({
                url:url+"doctor/LEI/",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then((res)=>{
                this.code=res.data.code;
                this.doctor_data.msg=res.data.msg;
                this.doctor_data.data=res.data.data;
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
            console.log(this.date1)
        },
        chooseTime(id,time){
            if(id == 0){
                this.time = "计划于 "+this.date1+"的"+time+"前往";
                this.Puretime = this.date1+" "+time;
            }
            else
            if(id == 1){
                this.time = "计划于 "+this.date2+"的"+time+"前往";
                this.Puretime = this.date2+" "+time;
            }
            else
            if(id == 2){
                this.time = "计划于 "+this.date3+"的"+time+"前往";
                this.Puretime = this.date3+" "+time;
            }
            else
            if(id == 3){
                this.time = "计划于 "+this.date4+"的"+time+"前往";
                this.Puretime = this.date4+" "+time;
            }
            else
            if(id == 4){
                this.time = "计划于 "+this.date5+"的"+time+"前往";
                this.Puretime = this.date5+" "+time;
            }
            else
            if(id == 5){
                this.time = "计划于 "+this.date6+"的"+time+"前往";
                this.Puretime = this.date6+" "+time;
            }
            else
            if(id == 6){
                this.time = "计划于 "+this.date7+"的"+time+"前往";
                this.Puretime = this.date7+" "+time;
            }
            document.getElementById("time_choose_describe").innerText=this.time;
            document.getElementById("time_choose_describe").style.display='inline';
            document.getElementById("Msg").style.display="none";
        },
        removeAllChoose(){
            this.doc = localStorage.removeItem("dpm_select");
            this.doc = localStorage.removeItem("doc_select");
            this.time = '';
        },
        removeDocAndTimeChoose(){
            this.doc = localStorage.removeItem("doc_select");
            this.time = '';
        },
        removeTimeChoose(){
            this.time = '';
        }
    },
    beforeMount(){
        this.getAllDpm();
        this.getMyDate();
        this.removeAllChoose();
    }
}
const aa = Vue.createApp(app);
aa.mount("#preserve");