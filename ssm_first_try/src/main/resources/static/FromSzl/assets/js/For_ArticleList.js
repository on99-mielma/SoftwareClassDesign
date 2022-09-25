var url ="http://localhost:8080/";
const doc = {
    data(){
        return{
            artList:[],
            type:'',
            msg:'',
            code:0,
            pages:0,
            cur:1
        }
    },
    methods:{
        getData(){
            let type = localStorage.getItem("type");
            if(type == 'news'){
                this.type='动态新闻';
                axios({
                    url:url+"article/gs/1/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
                axios({
                    url:url+"article/count",
                    method:"GET",
                }).then((res)=>{
                    if((res.data.data)%10)
                        this.pages=parseInt((res.data.data)/10+1);
                    else
                        this.pages=parseInt((res.data.data)/10);
                });
            }
            else if(type == 'knowledge'){
                this.type='健康知识';
                axios({
                    url:url+"knowledge/gs/1/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
                axios({
                    url:url+"knowledge/count",
                    method:"GET",
                }).then((res)=>{
                    if((res.data.data)%10)
                        this.pages=parseInt((res.data.data)/10+1);
                    else
                        this.pages=parseInt((res.data.data)/10);
                });
            }
            //billboard
            else{
                this.type='医院公告';
                axios({
                    url:url+"billboard/gs/1/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
                axios({
                    url:url+"billboard/count",
                    method:"GET",
                }).then((res)=>{
                    if((res.data.data)%10)
                        this.pages=parseInt((res.data.data)/10+1);
                    else
                        this.pages=parseInt((res.data.data)/10);
                });
            }
        },
        getCurData(){
            let type = localStorage.getItem("type");
            if(type == 'news'){
                this.type='动态新闻';
                axios({
                    url:url+"article/gs/"+this.cur+"/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
            }
            else if(type == 'knowledge'){
                this.type='健康知识';
                axios({
                    url:url+"knowledge/gs/"+this.cur+"/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
            }
            //billboard
            else{
                this.type='医院公告';
                axios({
                    url:url+"billboard/gs/"+this.cur+"/10",
                    method:"GET",
                }).then((res)=>{
                    this.msg = res.data.msg;
                    this.code = res.data.code;
                    this.artList = res.data.data;
                });
            }
            for(let i = 1;i<=this.pages;i++){
                document.getElementById(i).style.backgroundColor = "white";
                document.getElementById(i).style.color = "black";
            }
            document.getElementById(this.cur).style.backgroundColor = "#288cff";
            document.getElementById(this.cur).style.color = "white";
        },
        gotoart(id){
            localStorage.setItem("article_id",id);
        },
        setLastPage(){
            this.cur = (parseInt(this.cur)-1).toString();
            if(this.cur<1) this.cur=1;
        },
        setNextPage(){
            this.cur = (parseInt(this.cur)+1).toString();
            if(this.cur>this.pages) this.cur=this.pages;
        },
        setCurPage(cur){
            if(cur>this.pages) cur=this.pages;
            this.cur = cur;
        },
        judgeNextAndLast(){
            if(this.cur == this.pages) {
                document.getElementById("Next").classList.add("disabled","text-gray");
                document.getElementById("Next_text").style.color="lightgray";
                document.getElementById("Next_text").style.fontWeight="normal";
                document.getElementById("Last").classList.remove("disabled","text-gray");
                document.getElementById("Last_text").style.color="black";
                document.getElementById("Last_text").style.fontWeight="bold";
            }
            else
            if(this.cur == 1) {
                document.getElementById("Last").classList.add("disabled","text-gray");
                document.getElementById("Last_text").style.color="lightgray";
                document.getElementById("Last_text").style.fontWeight="normal";
                document.getElementById("Next").classList.remove("disabled","text-gray");
                document.getElementById("Next_text").style.color="black";
                document.getElementById("Next_text").style.fontWeight="bold";
            }
            else {
                document.getElementById("Last").classList.remove("disabled","text-gray");
                document.getElementById("Last_text").style.color="black";
                document.getElementById("Last_text").style.fontWeight="bold";
                document.getElementById("Next").classList.remove("disabled","text-gray");
                document.getElementById("Next_text").style.color="black";
                document.getElementById("Next_text").style.fontWeight="bold";
            }
        }
    },
    beforeMount(){
        this.getData();
    }
}
const app = Vue.createApp(doc)
app.mount("#artList")