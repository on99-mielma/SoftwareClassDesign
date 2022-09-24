var url ="http://localhost:8080/";

const article_card = {
    data(){
        return {
            billboard: {
                msg: '',
                code:0,
                data:[],
            },
            news:{
                msg: '',
                code:0,
                data:[],
            },
            health:{
                msg: '',
                code:0,
                data:[],
            },
        }
    },
    methods:{
        getHealthArticle_for_card(){
            axios({
                url:url+"knowledge/gs",
                method:"GET",
            }).then((res)=>{
                this.health.code=res.data.code;
                this.health.msg=res.data.msg;
                this.health.data=res.data.data.slice(0,3);
                console.log(res.data.data);
                for(let i=0;i<=2&&i<this.health.data.length;i++){
                    if(this.health.data[i].title.length>15){
                        this.health.data[i].title=this.health.data[i].title.substring(0,15)+"...";
                    }
                }
            })
        },
        getNewsArticle_for_card(){
            axios({
                url:url+"article/gs",
                method:"GET",
            }).then((res)=>{
                this.news.code=res.data.code;
                this.news.msg=res.data.msg;
                this.news.data=res.data.data.slice(0,3);
                console.log(res.data.data);
                for(let i=0;i<=2&&i<this.news.data.length;i++){
                    if(this.news.data[i].title.length>15){
                        this.news.data[i].title=this.news.data[i].title.substring(0,15)+"...";
                    }
                }
            })
        },
        getBillboardArticle_for_card(){
            axios({
                url:url+"billboard/gs",
                method:"GET",
            }).then((res)=>{
                this.billboard.code=res.data.code;
                this.billboard.msg=res.data.msg;
                this.billboard.data=res.data.data.slice(0,3);
                console.log(this.billboard.data);
                for(let i=0;i<=2&&i<this.billboard.data.length;i++){
                    if(this.billboard.data[i].title.length>=15){
                        this.billboard.data[i].title=this.billboard.data[i].title.substring(0,15)+"...";
                    }
                }
            })
        },
        gotoart(id){
            localStorage.setItem("article_id",id);
        },
        //for_headNav
        goBillboard(){
            localStorage.setItem("type","Billboard");
        },
        goNews(){
            localStorage.setItem("type","news");
        },
        goKnowledge(){
            localStorage.setItem("type","knowledge");
        },
    },
    beforeMount(){
        this.getHealthArticle_for_card();
        this.getNewsArticle_for_card();
        this.getBillboardArticle_for_card();
    }
}
const app1 = Vue.createApp(article_card)
app1.mount("#article_card")

const dpm_guide ={
    data(){
        return{
            dpmList:{
                msg:'',
                code:0,
                data:[
                    {dpmlist:[{id:1,officeName:"1",officeInfo:"1"}]}
                ]
            },
            msg:'',
            code:0,
            img:'',
            name:''
        }
    },
    methods: {
        getAllOffices() {
            axios({
                url:url+"offices/2d/4",
                method:"GET",
            }).then((res)=>{
                this.dpmList.code=res.data.code;
                this.dpmList.msg=res.data.msg;
                this.dpmList.data=res.data.data;
                this.img=this.dpmList.data[0][0].id;
                this.name=this.dpmList.data[0][0].officeName;
            })
        },
        ChangeImgHtml(id,name){
            this.img = id;
            this.name = name;
        },
        go_office(id,name){
            localStorage.setItem("dpm_id",id);
            localStorage.setItem("dpm_name",name);
        },
        setFocus(id){
            document.getElementById(id).focus();
        }
    },
    beforeMount() {
        this.getAllOffices();
    },
    mount(){

    }
}
const app2 = Vue.createApp(dpm_guide)
app2.mount("#dpm_guide")