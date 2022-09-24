var url ="http://localhost:8080/";
const dpm = {
    data(){
        return{
            name:'',
            info:'',
            doclist:[],
            artList:[],
            imgList:[],
            msg:'',
            code:0
        }
    },
    methods:{
        getData(){
            let id = localStorage.getItem("dpm_id");
            console.log(id);
            axios({
                url:url+"offices/id/"+id,
                method:"GET",
            }).then((res)=>{
                this.name=res.data.data.officeName;
                this.info=res.data.data.officeInfo;
                this.doclist=res.data.data.doctorInOffice['0'];
                console.log(this.doclist)
            });
        },
        getArtList(){
            let p = {
                "title":localStorage.getItem("dpm_name")
            }
            console.log(p);
            axios({
                url:url+"billboard/LTOT/",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                this.artList=res.data.data;
                if(this.artList.length>4){
                    this.artList=this.artList.slice(1);
                }
                console.log(this.artList)
            })
        },
        gotodoc(id){
            localStorage.setItem("doc_id",id);
        },
        gotoart(id){
            localStorage.setItem("article_id",id);
        },
        goBillboard(){
            localStorage.setItem("type","Billboard");
        },
        goNews(){
            localStorage.setItem("type","news");
        },
        goKnowledge(){
            localStorage.setItem("type","knowledge");
        }
    },
    beforeMount(){
        this.getData();
        this.getArtList()
    }
}
const app = Vue.createApp(dpm)
app.mount("#dpm")
