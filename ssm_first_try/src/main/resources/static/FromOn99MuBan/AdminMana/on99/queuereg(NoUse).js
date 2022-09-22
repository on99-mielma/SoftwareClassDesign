var url ="http://localhost:8080/";
const loginApp = {
    data(){
        return{
            ResultVisable:false,
            FormVisable:true,
            formData:{},
            code:0,
            msg:'',
            data:[],
            seconddata:[],
            TheCaughtParams:{
                dataRange:"",
                first:undefined,
                second:undefined
            },
        }
    },
    methods:{
        do_first(key){
            console.log(key);
            this.TheCaughtParams.first=key.officeName;
            this.seconddata=key.doctorInOffice;
            console.log("cilck first");
            console.log(this.seconddata);
        },
        do_second(){
            console.log("cilck second");
        },
        do_init(){
            axios({
                xhrFields: {
                    withCredentials: true,
                }
                ,
                url: url + "offices/gs/",
                method: "GET",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
            }).then((res)=>{
                this.code=res.data.code;
                this.msg=res.data.msg;
                this.data=res.data.data;
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        }
    },
    beforeMount() {
        this.do_init();
        console.log(new Date());
    }
}
const app2 = Vue.createApp(loginApp)
app2.mount("#TheQueue")