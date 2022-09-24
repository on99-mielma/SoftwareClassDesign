var url ="http://localhost:8080/";
const doc = {
    data(){
        return{
            type:'',
            title:'',
            text:'',
            date:'',
            author:'',
            artList:[],
            msg:'',
            code:0
        }
    },
    methods:{
        getNewsArticle_for_card(){
            let type = localStorage.getItem("type");
            if(type == 'news'){
                axios({
                    url:url+"article/gs",
                    method:"GET",
                }).then((res)=>{
                    this.artList = res.data.data.slice(0,8);
                })
            }
            else if(type == 'knowledge'){
                axios({
                    url:url+"knowledge/gs",
                    method:"GET",
                }).then((res)=>{
                    this.artList = res.data.data.slice(0,8);
                })
            }
            //billboard
            else{
                axios({
                    url:url+"billboard/gs",
                    method:"GET",
                }).then((res)=>{
                    this.artList = res.data.data.slice(0,8);
                })
            }
        },
        getData(){
            let id = localStorage.getItem("article_id");
            let type = localStorage.getItem("type");
            console.log(id);
            console.log(type);
            if(type == 'news'){
                this.type='动态新闻';
                axios({
                    url:url+"article/"+id,
                    method:"GET",
                }).then((res)=>{
                    console.log(res.data);
                    this.msg = res.data.data.msg;
                    this.code = res.data.data.code;
                    this.title = res.data.data.title;
                    this.text = res.data.data.text;
                    this.date = res.data.data.date;
                    this.author = res.data.data.author;
                })
            }
            else if(type == 'knowledge'){
                this.type='健康知识';
                axios({
                    url:url+"knowledge/"+id,
                    method:"GET",
                }).then((res)=>{
                    console.log(res.data);
                    this.msg = res.data.data.msg;
                    this.code = res.data.data.code;
                    this.title = res.data.data.title;
                    this.text = res.data.data.text;
                    this.date = res.data.data.date;
                    this.author = res.data.data.author;
                })
            }
            //billboard
            else{
                this.type='医院公告';
                axios({
                    url:url+"billboard/"+id,
                    method:"GET",
                }).then((res)=>{
                    console.log(res.data)
                    this.msg = res.data.data.msg;
                    this.code = res.data.data.code;
                    this.title = res.data.data.title;
                    this.text = "    "+res.data.data.text;
                    this.date = res.data.data.date;
                    this.author = res.data.data.author;
                })
            }
        },
        gotoart(id){
            localStorage.setItem("article_id",id);
        }
    },
    beforeMount(){
        this.getData();
        this.getNewsArticle_for_card();

    }
}
const app = Vue.createApp(doc)
app.mount("#article")