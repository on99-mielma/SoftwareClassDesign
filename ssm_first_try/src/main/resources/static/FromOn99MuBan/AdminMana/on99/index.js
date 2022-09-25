var url ="http://localhost:8080/";
const App = {
    data() {
        return {
            theDate:new Date().toLocaleDateString(),
            UserName:"Guest",
            Msg1:"Welcome back,",
            Msg2:"Your analytics dashboard AdminMana.",
            formData:{},
            code:0,
            msg:'',
            data:[],
            countAll:{
                countUsers:0,
                countDoctor:0,
                countOffices:0,
                countArticle:0,
                countQueue:0,
                countResume:0,
                countKnowledge:0,
                countBillboard:0
            },
            UserUpdate:true,
            showDashBoard:true,
            showUsers:false,
            showArticle:false,
            showDoctor:false,
            showOffices:false,
            showQueue:false,
            showResume:false,
            showKnowledge:false,
            showBillboard:false,
            articleData:{
                code:0,
                msg:'',
                data:[],
                articleGS:true,
                articleWork:false,
                articleEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            },
            doctorData:{
                imgurl:"",
                code:0,
                msg:'',
                data:[],
                doctorGS:true,
                doctorWork:false,
                doctorEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            },
            officesData:{
                code:0,
                msg:'',
                data:[],
                officesGS:true,
                officesWork:false,
                officesEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedName:'',
                ChangedId:0,
            },
            resumeData:{
                code:0,
                msg:'',
                data:[],
                resumeGS:true,
                resumeWork:false,
                resumeEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            },
            queueData:{
                code:0,
                msg:'',
                data:[],
                queueGS:true,
                queueWork:false,
                queueEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            },
            loginStatusData:{
                code:0,
                msg:"",
                data:[]
            },
            knowledgeData:{
                code:0,
                msg:'',
                data:[],
                knowledgeGS:true,
                knowledgeWork:false,
                knowledgeEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            },
            billboardData:{
                code:0,
                msg:'',
                data:[],
                billboardGS:true,
                billboardWork:false,
                billboardEdit:false,
                NeedToUpdateData:{
                    code:0,
                    msg:'',
                    data:"",
                },
                ChangedId:0
            }
        }
    },
    methods:{
            LoginStatusCheck(){
                axios({
                    xhrFields: {
                        withCredentials: true,
                    },
                    url:url+"users/checkStatus",
                    method:"GET",
                }).then((res)=>{
                    this.loginStatusData.code=res.data.code;
                    this.loginStatusData.msg=res.data.msg;
                    this.loginStatusData.data=res.data.data;
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===30030||res.data.msg==="404"){
                        console.log("LLLLLLLLLLLLLLLLLLL");
                        this.showDashBoard=false;
                        this.showUsers=false;
                        this.showDoctor=false;
                        this.showOffices=false;
                        this.showQueue=false;
                        this.showResume=false;
                        this.showArticle=false;
                        this.showBillboard=false;
                        this.showKnowledge=false;
                        window.location.href="login.html";
                    }
                    else{
                        console.log("SSSSSSSSSSSSSSSSS");
                        this.UserName = res.data.data.uName;
                        this.UserUpdate = false;
                        this.UserUpdate = !this.UserUpdate;
                        /*console.log(res.data.data.uName);
                        console.log(this.UserName);*/
                    }
                })
            },
            UTCtoGMT8(dateForm){
                if (dateForm === "") {  //解决deteForm为空传1970-01-01 00:00:00
                    return "";
                }else{
                    let dateee = new Date(dateForm).toJSON();
                    let date1 = new Date(+new Date(dateee)+ 8 * 3600 * 1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
                    return date1;
                }
            },
            strToByte(bstr){
                let n = bstr.length;
                let u8arr = new Uint8Array(n);
                while (n--) {
                    u8arr[n] = bstr.charCodeAt(n);
                    /*console.log(bstr.charCodeAt(n));*/
                }
                return u8arr;
            },
            do_settings(){
                console.log(this.countAll);
                console.log("setting");
            },
            do_logout(){
                window.location.href="login.html";
                console.log("logout");
            },
            getCountOfUsers(){
                axios({
                    url:url+'users'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countUsers=res.data.data;
                    console.log("users"+res.data.data);
                })
            },
            getCountOfDoctor() {
                axios({
                    url:url+'doctor'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countDoctor=res.data.data;
                    console.log("doctor"+res.data.data);
                })
            },
            getCountOfOffice() {
                axios({
                    url:url+'offices'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countOffices=res.data.data;
                    console.log("office"+res.data.data);
                })
            },
            getCountOfQueue() {
                axios({
                    url:url+'queuereg'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countQueue=res.data.data;
                    console.log("queue"+res.data.data);
                })
            },
            getCountOfResume() {
                axios({
                    url:url+'resume'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countResume=res.data.data;
                    console.log("resume"+res.data.data);
                })
            },
            getCountOfArticle() {
                axios({
                    url:url+'article'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countArticle=res.data.data;
                    console.log("article"+res.data.data);
                })
            },
            getCountOfKnowledge() {
                axios({
                    url:url+'knowledge'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countKnowledge=res.data.data;
                    console.log("knowledge"+res.data.data);
                })
            },
            getCountOfBillboard(){
                axios({
                    url:url+'billboard'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countBillboard=res.data.data;
                    console.log("billboard count: "+res.data.data);
                })
            },
            /*todo article js*/
            getAllArticle() {
                axios({
                    url:url+"article/gs",
                    method:"GET",
                }).then((res)=>{
                    this.articleData.code=res.data.code;
                    this.articleData.msg=res.data.msg;
                    this.articleData.data=res.data.data;
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            setOneArticle(id){
                this.articleData.ChangedId = id ;
                console.log(id);
                this.articleData.articleGS = false ;
                this.articleData.articleWork = true ;
                this.articleData.articleEdit = false ;
                axios({
                    url:url+"article/"+id,
                    method:"GET",
                }).then((res)=>{
                    this.articleData.NeedToUpdateData.code=res.data.code;
                    this.articleData.NeedToUpdateData.msg=res.data.msg;
                    this.articleData.NeedToUpdateData.data=res.data.data;
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            articleBack(){
                this.articleData.NeedToUpdateData.data='';
                this.articleData.articleGS = true ;
                this.articleData.articleWork = false ;
                this.articleData.articleEdit = false ;
            },
            articleEditIn() {
                this.articleData.NeedToUpdateData.data='';
                this.articleData.articleGS = false ;
                this.articleData.articleWork = false ;
                this.articleData.articleEdit = true ;
            },
            articleIn() {
                this.Msg2 = "处理文章";
                this.getAllArticle();
                this.showBillboard=false;
                this.showKnowledge=false;
                this.showDashBoard=false;
                this.showUsers=false;
                this.showDoctor=false;
                this.showOffices=false;
                this.showQueue=false;
                this.showResume=false;
                this.showArticle=true;
            },
            articleInsert(){
                let title = document.getElementById("insertTitle").value;
                let text = document.getElementById("insertText").value;
                let author = document.getElementById("insertAuthor").value;
                if(title===''||title===null||text===''||text===null||author===''||author===null){
                    alert("不能为空")
                    return;
                }
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {
                    "title":title,
                    "text":text,
                    "author":author,
                    "date":articleDateFinal
                }
                console.log(p);
                axios({
                    url:url+"article/insert",
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===10010||res.data.code==='10010'){
                        alert("请检查输入");
                        return;
                    }
                    alert("成功添加!");
                    document.getElementById("insertTitle").value='';
                    document.getElementById("insertText").value='';
                    document.getElementById("insertAuthor").value='';
                    this.articleData.NeedToUpdateData.data='';
                    this.articleData.articleGS = true ;
                    this.articleData.articleWork = false ;
                    this.articleData.articleEdit = false ;
                    this.getAllArticle();
                })
                scrollTo(0,0);
            },
            articleDeleteConfirm() {
                var aDc = confirm("是否删除？");
                if(aDc===true){
                    this.articleDelete();
                }
                else{
                    alert("已取消");
                }
            },
            articleDelete() {
                let idDelete = this.articleData.ChangedId;
                console.log(idDelete);
                let p ={
                    "id":idDelete
                }
                axios({
                    url:url+"article/",
                    method:"DELETE",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===20020||res.data.code==='20020'){
                        alert("删除失败");
                        return;
                    }
                    alert("成功删除!");
                    this.articleData.NeedToUpdateData.data='';
                    this.articleData.ChangedId=0;
                    this.articleData.articleGS = true ;
                    this.articleData.articleWork = false ;
                    this.articleData.articleEdit = false ;
                    this.getAllArticle();
                })
            },
            articleUpdate() {
                let idUpdate = this.articleData.ChangedId;
                let title = document.getElementById("updateTitle").value;
                let text = document.getElementById("updateText").value;
                let author = document.getElementById("updateAuthor").value;
                console.log(idUpdate);
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {
                    "id":idUpdate,
                    "title":title,
                    "text":text,
                    "author":author,
                    "date":articleDateFinal
                }
                axios({
                    url:url+"article/",
                    method:"PUT",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===40040||res.data.code==='40040'){
                        alert("更新失败");
                        return;
                    }
                    alert("成功更新!");
                    this.articleData.NeedToUpdateData.data='';
                    this.articleData.ChangedId=0;
                    this.articleData.articleGS = true ;
                    this.articleData.articleWork = false ;
                    this.articleData.articleEdit = false ;
                    this.getAllArticle();
                })
            },
            dashBoardIn () {
                this.Msg2 = "Your analytics dashboard AdminMana.";
                this.getCountOfUsers();
                this.getCountOfArticle();
                this.getCountOfDoctor();
                this.getCountOfOffice();
                this.getCountOfQueue();
                this.getCountOfResume();
                this.getCountOfKnowledge();
                this.getCountOfBillboard();
                this.showDashBoard=true;
                this.showBillboard=false;
                this.showKnowledge=false;
                this.showUsers=false;
                this.showDoctor=false;
                this.showOffices=false;
                this.showQueue=false;
                this.showResume=false;
                this.showArticle=false;
            },
            /*todo doctor js*/
            doctorIn (){
                this.Msg2 = "Doctor management";
                this.showBillboard=false;
                this.showKnowledge=false;
                this.showDashBoard=false;
                this.showUsers=false;
                this.showDoctor=true;
                this.showOffices=false;
                this.showQueue=false;
                this.showResume=false;
                this.showArticle=false;
                this.getAllDoctor();
            },
            getAllDoctor () {
                axios({
                    url:url+"doctor/gs",
                    method:"GET",
                }).then((res)=>{
                    this.doctorData.code=res.data.code;
                    this.doctorData.msg=res.data.msg;
                    this.doctorData.data=res.data.data;
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            doctorBack () {
                this.doctorData.NeedToUpdateData.data='';
                this.doctorData.doctorGS = true ;
                this.doctorData.doctorEdit = false ;
                this.doctorData.doctorWork = false ;
            },
            doctorEditIn () {
                this.doctorData.NeedToUpdateData.data='';
                this.doctorData.doctorGS = false ;
                this.doctorData.doctorWork = false ;
                this.doctorData.doctorEdit = true ;
            },
            setOneDoctor (id){
                this.doctorData.ChangedId = id ;
                console.log(id);
                this.doctorData.imgurl = "../../../FromSzl/assets/img/doctorPic/"+id+".jpg";
                this.doctorData.doctorGS = false ;
                this.doctorData.doctorWork = true ;
                this.doctorData.doctorEdit = false ;
                axios({
                    url:url+"doctor/"+id,
                    method:"GET",
                }).then((res)=>{
                    this.doctorData.NeedToUpdateData.code=res.data.code;
                    this.doctorData.NeedToUpdateData.msg=res.data.msg;
                    this.doctorData.NeedToUpdateData.data=res.data.data;
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            doctorUpdate () {
                let idUpdate = this.doctorData.ChangedId;
                let name = document.getElementById("updateDoctorName").value;
                let office = document.getElementById("updateDoctorOffice").value;
                let skill = document.getElementById("updateDoctorSkill").value;
                let info = document.getElementById("updateDoctorInfo").value;
                if(name===''||name===null||office===''||office===null||skill===''||skill===null||info===''||info===null){
                    alert("检测到空值！");
                    return;
                }
                console.log(idUpdate);
                let morning = [];
                let afternoon = [];
                let night = [];
                var regax = /^(0|[1-9][0-9]*)$/;
                for (let i = 0; i < 7; i++) {
/*                    console.log("进入循环"+i);*/
                    let mIdStr = "updateDoctorMorningDtt"+i;
                    let m = document.getElementById(mIdStr).value;
                    let aIdStr = "updateDoctorAfternoonDtt"+i;
                    let a = document.getElementById(aIdStr).value;
                    let nIdStr = "updateDoctorNightDtt"+i;
                    let n = document.getElementById(nIdStr).value;
/*                    console.log("m的type:"+typeof m);*/
                    if(m===''||m===null||a===''||a===null||n===''||n===null){
                        alert("检测到空值！");
                        return;
                    }
                    if(!regax.test(m)||!regax.test(a)||!regax.test((n))){
                        alert("检测到格式错误！请检查输入格式！");
                        return;
                    }
                    morning.push(m);
                    afternoon.push(a);
                    night.push(n);
                }
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {
                    "id":idUpdate,
                    "name":name,
                    "office":office,
                    "skill":skill,
                    "info":info,
                    "date":articleDateFinal,
                    "morning":morning,
                    "afternoon":afternoon,
                    "night":night,
                }
                axios({
                    url:url+"doctor/",
                    method:"PUT",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===40040||res.data.code==='40040'){
                        alert("更新失败");
                        return;
                    }
                    alert("成功更新!");
                    this.doctorData.NeedToUpdateData.data='';
                    this.doctorData.ChangedId=0;
                    this.doctorData.doctorGS = true ;
                    this.doctorData.doctorWork = false ;
                    this.doctorData.doctorEdit = false ;
                    this.getAllDoctor();
                })
            },
            doctorInsert (){
                let name = document.getElementById("insertDoctorName").value;
                let office = document.getElementById("insertDoctorOffice").value;
                let skill = document.getElementById("insertDoctorSkill").value;
                let info = document.getElementById("insertDoctorInfo").value;
                if(name===''||name===null||office===''||office===null||skill===''||skill===null||info===''||info===null){
                    alert("检测到空值！");
                    return;
                }
                let p = {
                    "name":name,
                    "office":office,
                    "skill":skill,
                    "info":info
                }
                console.log(p);
                axios({
                    url:url+"doctor/insert/",
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===10010||res.data.code==='10010'){
                        alert("请检查输入");
                        return;
                    }
                    alert("成功添加!");
                    document.getElementById("insertDoctorName").value='';
                    document.getElementById("insertDoctorOffice").value='';
                    document.getElementById("insertDoctorSkill").value='';
                    document.getElementById("insertDoctorInfo").value='';
                    this.getAllDoctor();
                    console.log("已经getAll");
                    this.doctorData.NeedToUpdateData.data='';
                    this.doctorData.doctorGS = true ;
                    this.doctorData.doctorWork = false ;
                    this.doctorData.doctorEdit = false ;
                })
                scrollTo(0,0);
            },
            doctorDeleteConfirm() {
                var aDc = confirm("是否删除？");
                if(aDc===true){
                    this.doctorDelete();
                }
                else{
                    alert("已取消");
                }
            },
            doctorDelete() {
                let idDelete = this.doctorData.ChangedId;
                console.log(idDelete);
                axios({
                    url:url+"doctor/"+idDelete,
                    method:"DELETE",
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===20020||res.data.code==='20020'){
                        alert("删除失败");
                        return;
                    }
                    alert("成功删除!");
                    this.doctorData.NeedToUpdateData.data='';
                    this.doctorData.ChangedId=0;
                    this.doctorData.doctorGS = true ;
                    this.doctorData.doctorWork = false ;
                    this.doctorData.doctorEdit = false ;
                    this.getAllDoctor();
                })
            },
            /*todo queue js*/
            queueIn(){/* change */
                this.Msg2 = "Queue management";/* change */
                this.showBillboard=false;
                this.showKnowledge=false;
                this.showDashBoard=false;
                this.showUsers=false;
                this.showDoctor=false;
                this.showOffices=false;/* change */
                this.showQueue=true;
                this.showResume=false;
                this.showArticle=false;
                this.getAllQueue();/* change */
            },
            getAllQueue() {/* change */
                axios({
                    url:url+"queuereg/",/* change */
                    method:"GET",
                }).then((res)=>{
                    this.queueData.code=res.data.code;/* change */
                    this.queueData.msg=res.data.msg;/* change */
                    this.queueData.data=res.data.data;/* change */
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            queueBack() {/* change */
                this.queueData.NeedToUpdateData.data='';/* change */
                this.queueData.queueGS = true ;/* change */
                this.queueData.queueEdit = false ;/* change */
                this.queueData.queueWork = false ;/* change */
            },
            queueEditIn() {/* change */
                this.queueData.NeedToUpdateData.data='';/* change */
                this.queueData.queueGS = false ;/* change */
                this.queueData.queueWork = false ;/* change */
                this.queueData.queueEdit = true ;/* change */
            },
            setOneQueue(id){/* change */
                this.queueData.ChangedId = id ;/* change */
                console.log(id);
                this.queueData.queueGS = false ;/* change */
                this.queueData.queueWork = true ;/* change */
                this.queueData.queueEdit = false ;/* change */
                axios({
                    url:url+"queuereg/"+id,/* change */
                    method:"GET",
                }).then((res)=>{
                    this.queueData.NeedToUpdateData.code=res.data.code;/* change */
                    this.queueData.NeedToUpdateData.msg=res.data.msg;/* change */
                    this.queueData.NeedToUpdateData.data=res.data.data;/* change */
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            queueUpdate() {/* change */
                let idUpdate = this.queueData.ChangedId;/* change */
                let name = document.getElementById("updateQueueName").value;/* change */
                let office = document.getElementById("updateQueueOffice").value;/* change */
                let doctor = document.getElementById("updateQueueDoctor").value;/* change */
                let info = document.getElementById("updateQueueInfo").value;/* change */
                if(name===''||name===null||info===''||info===null||office===''||office===null||doctor===''||doctor===null){/* change */
                    alert("检测到空值！");
                    return;
                }
                console.log(idUpdate);
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {/* change */
                    "id":idUpdate,
                    "name":name,
                    "office":office,
                    "doctor":doctor,
                    "info":info,
                    "time":articleDateFinal
                }
                axios({
                    url:url+"queuereg/",/* change */
                    method:"PUT",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===40040||res.data.code==='40040'){
                        alert("更新失败");
                        return;
                    }
                    alert("成功更新!");
                    this.queueData.NeedToUpdateData.data='';/* change */
                    this.queueData.ChangedId=0;/* change */
                    this.queueData.queueGS = true ;/* change */
                    this.queueData.queueWork = false ;/* change */
                    this.queueData.queueEdit = false ;/* change */
                    this.getAllQueue();/* change */
                })
            },
            queueInsert(){/* change */
                let name = document.getElementById("insertQueueName").value;/* change */
                let office = document.getElementById("insertQueueOffice").value;/* change */
                let doctor = document.getElementById("insertQueueDoctor").value;/* change */
                let info = document.getElementById("insertQueueInfo").value;/* change */
                if(name===''||name===null||info===''||info===null||office===''||office===null||doctor===''||doctor===null){/* change */
                    alert("检测到空值！");
                    return;
                }
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {/* change */
                    "name":name,
                    "office":office,
                    "doctor":doctor,
                    "info":info,
                    "time":articleDateFinal
                }
                console.log(p);
                axios({
                    url:url+"queuereg/insert/",/* change */
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===10010||res.data.code==='10010'){
                        alert("请检查输入");
                        return;
                    }
                    alert("成功添加!");
                    document.getElementById("insertQueueName").value='';/* change */
                    document.getElementById("insertQueueOffice").value='';/* change */
                    document.getElementById("insertQueueDoctor").value='';/* change */
                    document.getElementById("insertQueueInfo").value='';/* change */
                    this.getAllQueue();/* change */
                    this.queueData.NeedToUpdateData.data='';/* change */
                    this.queueData.queueGS = true ;/* change */
                    this.queueData.queueWork = false ;/* change */
                    this.queueData.queueEdit = false ;/* change */
                })
                scrollTo(0,0);
            },
            queueDeleteConfirm() {/* change */
                var aDc = confirm("是否删除？");
                if(aDc===true){
                    this.queueDelete();/* change */
                }
                else{
                    alert("已取消");
                }
            },
            queueDelete() {/* change */
                let idDelete = this.queueData.ChangedId;/* change */
                console.log(idDelete);
                let p ={/* change */
                    "id":idDelete
                }
                axios({
                    url:url+"queuereg/",/* change */
                    method:"DELETE",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===20020||res.data.code==='20020'){
                        alert("删除失败");
                        return;
                    }
                    alert("成功删除!");
                    this.queueData.NeedToUpdateData.data='';/* change */
                    this.queueData.ChangedId=0;/* change */
                    this.queueData.queueGS = true ;/* change */
                    this.queueData.queueWork = false ;/* change */
                    this.queueData.queueEdit = false ;/* change */
                    this.getAllQueue();/* change */
                })
            },
            /*todo offices js */
            officesIn(){/* change */
                this.Msg2 = "Offices management";/* change */
                this.showBillboard=false;
                this.showKnowledge=false;
                this.showDashBoard=false;
                this.showUsers=false;
                this.showDoctor=false;
                this.showOffices=true;/* change */
                this.showQueue=false;
                this.showResume=false;
                this.showArticle=false;
                this.getAllOffices();/* change */
            },
            getAllOffices() {/* change */
                axios({
                    url:url+"offices/gs",/* change */
                    method:"GET",
                }).then((res)=>{
                    this.officesData.code=res.data.code;/* change */
                    this.officesData.msg=res.data.msg;/* change */
                    this.officesData.data=res.data.data;/* change */
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            officesBack() {/* change */
                this.officesData.NeedToUpdateData.data='';/* change */
                this.officesData.officesGS = true ;/* change */
                this.officesData.officesEdit = false ;/* change */
                this.officesData.officesWork = false ;/* change */
            },
            officesEditIn() {/* change */
                this.officesData.NeedToUpdateData.data='';/* change */
                this.officesData.officesGS = false ;/* change */
                this.officesData.officesWork = false ;/* change */
                this.officesData.officesEdit = true ;/* change */
            },
            setOneOffices(id){/* change */
                this.officesData.ChangedId = id ;/* change */
                console.log(id);
                this.officesData.officesGS = false ;/* change */
                this.officesData.officesWork = true ;/* change */
                this.officesData.officesEdit = false ;/* change */
                axios({
                    url:url+"offices/id/"+id,/* change */
                    method:"GET",
                }).then((res)=>{
                    this.officesData.NeedToUpdateData.code=res.data.code;/* change */
                    this.officesData.NeedToUpdateData.msg=res.data.msg;/* change */
                    this.officesData.NeedToUpdateData.data=res.data.data;/* change */
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                })
            },
            officesUpdate() {/* change */
                let idUpdate = this.officesData.ChangedId;/* change */
                let name = document.getElementById("updateOfficesName").value;/* change */
                let info = document.getElementById("updateOfficesInfo").value;/* change */
                if(name===''||name===null||info===''||info===null){/* change */
                    alert("检测到空值！");
                    return;
                }
                console.log(idUpdate);
                let p = {/* change */
                    "id":idUpdate,
                    "officeName":name,
                    "officeInfo":info,
                }
                axios({
                    url:url+"offices/",/* change */
                    method:"PUT",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===40040||res.data.code==='40040'){
                        alert("更新失败");
                        return;
                    }
                    alert("成功更新!");
                    this.officesData.NeedToUpdateData.data='';/* change */
                    this.officesData.ChangedId=0;/* change */
                    this.officesData.officesGS = true ;/* change */
                    this.officesData.officesWork = false ;/* change */
                    this.officesData.officesEdit = false ;/* change */
                    this.getAllOffices();/* change */
                })
            },
            officesInsert(){/* change */
                let name = document.getElementById("insertOfficesName").value;/* change */
                let info = document.getElementById("insertOfficesInfo").value;/* change */
                if(name===''||name===null||info===''||info===null){/* change */
                    alert("检测到空值！");
                    return;
                }
                let p = {/* change */
                    "officeName":name,
                    "officeInfo":info,
                }
                console.log(p);
                axios({
                    url:url+"offices/insert/",/* change */
                    method:"POST",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===10010||res.data.code==='10010'){
                        alert("请检查输入");
                        return;
                    }
                    alert("成功添加!");
                    document.getElementById("insertOfficesName").value='';/* change */
                    document.getElementById("insertOfficesInfo").value='';/* change */
                    this.getAllOffices();/* change */
                    this.officesData.NeedToUpdateData.data='';/* change */
                    this.officesData.officesGS = true ;/* change */
                    this.officesData.officesWork = false ;/* change */
                    this.officesData.officesEdit = false ;/* change */
                })
                scrollTo(0,0);
            },
            officesDeleteConfirm() {/* change */
                var aDc = confirm("是否删除？");
                if(aDc===true){
                    this.officesDelete();/* change */
                }
                else{
                    alert("已取消");
                }
            },
            officesDelete() {/* change */
                let idDelete = this.officesData.ChangedId;/* change */
                console.log(idDelete);
                let p ={/* change */
                    "id":idDelete
                }
                axios({
                    url:url+"offices/",/* change */
                    method:"DELETE",
                    data:JSON.stringify(p),
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                        /*'Content-Type': 'application/json;charset=UTF-8'*/
                    }
                }).then((res)=>{
                    console.log(res.data.code);
                    console.log(res.data.msg);
                    console.log(res.data.data);
                    if(res.data.code===20020||res.data.code==='20020'){
                        alert("删除失败");
                        return;
                    }
                    alert("成功删除!");
                    this.officesData.NeedToUpdateData.data='';/* change */
                    this.officesData.ChangedId=0;/* change */
                    this.officesData.officesGS = true ;/* change */
                    this.officesData.officesWork = false ;/* change */
                    this.officesData.officesEdit = false ;/* change */
                    this.getAllOffices();/* change */
                })
            },
            /*todo knowledge js*/
        knowledgeIn(){/* change */
            this.Msg2 = "Knowledge management";/* change */
            this.showBillboard=false;
            this.showKnowledge=true;/* change */
            this.showDashBoard=false;
            this.showUsers=false;
            this.showDoctor=false;
            this.showOffices=false;
            this.showQueue=false;
            this.showResume=false;
            this.showArticle=false;
            this.getAllKnowledge();/* change */
        },
        getAllKnowledge() {/* change */
            axios({
                url:url+"knowledge/gs",/* change */
                method:"GET",
            }).then((res)=>{
                this.knowledgeData.code=res.data.code;/* change */
                this.knowledgeData.msg=res.data.msg;/* change */
                this.knowledgeData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        knowledgeBack() {/* change */
            this.knowledgeData.NeedToUpdateData.data='';/* change */
            this.knowledgeData.knowledgeGS = true ;/* change */
            this.knowledgeData.knowledgeEdit = false ;/* change */
            this.knowledgeData.knowledgeWork = false ;/* change */
        },
        knowledgeEditIn() {/* change */
            this.knowledgeData.NeedToUpdateData.data='';/* change */
            this.knowledgeData.knowledgeGS = false ;/* change */
            this.knowledgeData.knowledgeWork = false ;/* change */
            this.knowledgeData.knowledgeEdit = true ;/* change */
        },
        setOneKnowledge(id){/* change */
            this.knowledgeData.ChangedId = id ;/* change */
            console.log(id);
            this.knowledgeData.knowledgeGS = false ;/* change */
            this.knowledgeData.knowledgeWork = true ;/* change */
            this.knowledgeData.knowledgeEdit = false ;/* change */
            axios({
                url:url+"knowledge/"+id,/* change */
                method:"GET",
            }).then((res)=>{
                this.knowledgeData.NeedToUpdateData.code=res.data.code;/* change */
                this.knowledgeData.NeedToUpdateData.msg=res.data.msg;/* change */
                this.knowledgeData.NeedToUpdateData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        knowledgeUpdate() {/* change */
            let idUpdate = this.knowledgeData.ChangedId;/* change */
            let title = document.getElementById("knowledgeUpdateTitle").value;
            let text = document.getElementById("knowledgeUpdateText").value;
            let author = document.getElementById("knowledgeUpdateAuthor").value;
            if(title===''||title===null||text===''||text===null||author===''||author===null){/* change */
                alert("检测到空值！");
                return;
            }
            console.log(idUpdate);
            let knowledgeDate = new Date().toISOString();
            let knowledgeDateFinal = this.UTCtoGMT8(knowledgeDate);
            let p = {/* change */
                "id":idUpdate,
                "title":title,
                "text":text,
                "author":author,
                "date":knowledgeDateFinal
            }
            axios({
                url:url+"knowledge/",/* change */
                method:"PUT",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===40040||res.data.code==='40040'){
                    alert("更新失败");
                    return;
                }
                alert("成功更新!");
                this.knowledgeData.NeedToUpdateData.data='';/* change */
                this.knowledgeData.ChangedId=0;/* change */
                this.knowledgeData.knowledgeGS = true ;/* change */
                this.knowledgeData.knowledgeWork = false ;/* change */
                this.knowledgeData.knowledgeEdit = false ;/* change */
                this.getAllKnowledge();/* change */
            })
        },
        knowledgeInsert(){/* change */
            let title = document.getElementById("knowledgeInsertTitle").value;
            let text = document.getElementById("knowledgeInsertText").value;
            let author = document.getElementById("knowledgeInsertAuthor").value;
            if(title===''||title===null||text===''||text===null||author===''||author===null){/* change */
                alert("检测到空值！");
                return;
            }
            let knowledgeDate = new Date().toISOString();
            let knowledgeDateFinal = this.UTCtoGMT8(knowledgeDate);
            let p = {/* change */
                "title":title,
                "text":text,
                "author":author,
                "date":knowledgeDateFinal
            }
            console.log(p);
            axios({
                url:url+"knowledge/",/* change */
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===10010||res.data.code==='10010'){
                    alert("请检查输入");
                    return;
                }
                alert("成功添加!");
                document.getElementById("knowledgeInsertTitle").value='';/* change */
                document.getElementById("knowledgeInsertText").value='';/* change */
                document.getElementById("knowledgeInsertAuthor").value='';/* change */
                this.getAllKnowledge();/* change */
                this.knowledgeData.NeedToUpdateData.data='';/* change */
                this.knowledgeData.knowledgeGS = true ;/* change */
                this.knowledgeData.knowledgeWork = false ;/* change */
                this.knowledgeData.knowledgeEdit = false ;/* change */
            })
            scrollTo(0,0);
        },
        knowledgeDeleteConfirm() {/* change */
            var aDc = confirm("是否删除？");
            if(aDc===true){
                this.knowledgeDelete();/* change */
            }
            else{
                alert("已取消");
            }
        },
        knowledgeDelete() {/* change */
            let idDelete = this.knowledgeData.ChangedId;/* change */
            console.log(idDelete);
            axios({
                url:url+"knowledge/"+idDelete,/* change */
                method:"DELETE",
            }).then((res)=>{
                console.log(res.data.code);
                console.log(typeof res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code!=20021){
                    alert("删除失败");
                    return;
                }
                alert("成功删除!");
                this.knowledgeData.NeedToUpdateData.data='';/* change */
                this.knowledgeData.ChangedId=0;/* change */
                this.knowledgeData.knowledgeGS = true ;/* change */
                this.knowledgeData.knowledgeWork = false ;/* change */
                this.knowledgeData.knowledgeEdit = false ;/* change */
                this.getAllKnowledge();/* change */
            })
        },
        /*todo billboard js*/
        billboardIn(){/* change */
            this.Msg2 = "Billboard management";/* change */
            this.showBillboard=true;
            this.showKnowledge=false;/* change */
            this.showDashBoard=false;
            this.showUsers=false;
            this.showDoctor=false;
            this.showOffices=false;
            this.showQueue=false;
            this.showResume=false;
            this.showArticle=false;
            this.getAllBillboard();/* change */
        },
        getAllBillboard() {/* change */
            axios({
                url:url+"billboard/gs",/* change */
                method:"GET",
            }).then((res)=>{
                this.billboardData.code=res.data.code;/* change */
                this.billboardData.msg=res.data.msg;/* change */
                this.billboardData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        billboardBack() {/* change */
            this.billboardData.NeedToUpdateData.data='';/* change */
            this.billboardData.billboardGS = true ;/* change */
            this.billboardData.billboardEdit = false ;/* change */
            this.billboardData.billboardWork = false ;/* change */
        },
        billboardEditIn() {/* change */
            this.billboardData.NeedToUpdateData.data='';/* change */
            this.billboardData.billboardGS = false ;/* change */
            this.billboardData.billboardWork = false ;/* change */
            this.billboardData.billboardEdit = true ;/* change */
        },
        setOneBillboard(id){/* change */
            this.billboardData.ChangedId = id ;/* change */
            console.log(id);
            this.billboardData.billboardGS = false ;/* change */
            this.billboardData.billboardWork = true ;/* change */
            this.billboardData.billboardEdit = false ;/* change */
            axios({
                url:url+"billboard/"+id,/* change */
                method:"GET",
            }).then((res)=>{
                this.billboardData.NeedToUpdateData.code=res.data.code;/* change */
                this.billboardData.NeedToUpdateData.msg=res.data.msg;/* change */
                this.billboardData.NeedToUpdateData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        billboardUpdate() {/* change */
            let idUpdate = this.billboardData.ChangedId;/* change */
            let title = document.getElementById("billboardUpdateTitle").value;
            let text = document.getElementById("billboardUpdateText").value;
            let author = document.getElementById("billboardUpdateAuthor").value;
            if(title===''||title===null||text===''||text===null||author===''||author===null){/* change */
                alert("检测到空值！");
                return;
            }
            console.log(idUpdate);
            let billboardDate = new Date().toISOString();
            let billboardDateFinal = this.UTCtoGMT8(billboardDate);
            let p = {/* change */
                "id":idUpdate,
                "title":title,
                "text":text,
                "author":author,
                "date":billboardDateFinal
            }
            axios({
                url:url+"billboard/",/* change */
                method:"PUT",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===40040||res.data.code==='40040'){
                    alert("更新失败");
                    return;
                }
                alert("成功更新!");
                this.billboardData.NeedToUpdateData.data='';/* change */
                this.billboardData.ChangedId=0;/* change */
                this.billboardData.billboardGS = true ;/* change */
                this.billboardData.billboardWork = false ;/* change */
                this.billboardData.billboardEdit = false ;/* change */
                this.getAllBillboard();/* change */
            })
        },
        billboardInsert(){/* change */
            let title = document.getElementById("billboardInsertTitle").value;
            let text = document.getElementById("billboardInsertText").value;
            let author = document.getElementById("billboardInsertAuthor").value;
            if(title===''||title===null||text===''||text===null||author===''||author===null){/* change */
                alert("检测到空值！");
                return;
            }
            let knowledgeDate = new Date().toISOString();
            let knowledgeDateFinal = this.UTCtoGMT8(knowledgeDate);
            let p = {/* change */
                "title":title,
                "text":text,
                "author":author,
                "date":knowledgeDateFinal
            }
            console.log(p);
            axios({
                url:url+"billboard/insert/",/* change */
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===10010||res.data.code==='10010'){
                    alert("请检查输入");
                    return;
                }
                alert("成功添加!");
                document.getElementById("billboardInsertTitle").value='';/* change */
                document.getElementById("billboardInsertText").value='';/* change */
                document.getElementById("billboardInsertAuthor").value='';/* change */
                this.getAllBillboard();/* change */
                this.billboardData.NeedToUpdateData.data='';/* change */
                this.billboardData.billboardGS = true ;/* change */
                this.billboardData.billboardWork = false ;/* change */
                this.billboardData.billboardEdit = false ;/* change */
            })
            scrollTo(0,0);
        },
        billboardDeleteConfirm() {/* change */
            var aDc = confirm("是否删除？");
            if(aDc===true){
                this.billboardDelete();/* change */
            }
            else{
                alert("已取消");
            }
        },
        billboardDelete() {/* change */
            let idDelete = this.billboardData.ChangedId;/* change */
            console.log(idDelete);
            let p={
                "id":idDelete
            }
            axios({
                url:url+"billboard/",/* change */
                method:"DELETE",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(typeof res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code!=20021){
                    alert("删除失败");
                    return;
                }
                alert("成功删除!");
                this.billboardData.NeedToUpdateData.data='';/* change */
                this.billboardData.ChangedId=0;/* change */
                this.billboardData.billboardGS = true ;/* change */
                this.billboardData.billboardWork = false ;/* change */
                this.billboardData.billboardEdit = false ;/* change */
                this.getAllBillboard();/* change */
            })
        },
        /*todo resume js*/
        resumeIn(){/* change */
            this.Msg2 = "Resume management";/* change */
            this.showBillboard=false;
            this.showKnowledge=false;/* change */
            this.showDashBoard=false;
            this.showUsers=false;
            this.showDoctor=false;
            this.showOffices=false;
            this.showQueue=false;
            this.showResume=true;
            this.showArticle=false;
            this.getAllResume();/* change */
        },
        getAllResume() {/* change */
            axios({
                url:url+"resume",/* change */
                method:"GET",
            }).then((res)=>{
                this.resumeData.code=res.data.code;/* change */
                this.resumeData.msg=res.data.msg;/* change */
                this.resumeData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        resumeBack() {/* change */
            this.resumeData.NeedToUpdateData.data='';/* change */
            this.resumeData.resumeGS = true ;/* change */
            this.resumeData.resumeEdit = false ;/* change */
            this.resumeData.resumeWork = false ;/* change */
        },
        resumeEditIn() {/* change */
            this.resumeData.NeedToUpdateData.data='';/* change */
            this.resumeData.resumeGS = false ;/* change */
            this.resumeData.resumeWork = false ;/* change */
            this.resumeData.resumeEdit = true ;/* change */
        },
        setOneResume(id){/* change */
            this.resumeData.ChangedId = id ;/* change */
            console.log(id);
            this.resumeData.resumeGS = false ;/* change */
            this.resumeData.resumeWork = true ;/* change */
            this.resumeData.resumeEdit = false ;/* change */
            axios({
                url:url+"resume/"+id,/* change */
                method:"GET",
            }).then((res)=>{
                this.resumeData.NeedToUpdateData.code=res.data.code;/* change */
                this.resumeData.NeedToUpdateData.msg=res.data.msg;/* change */
                this.resumeData.NeedToUpdateData.data=res.data.data;/* change */
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
            })
        },
        resumeUpdate() {/* change */
            let idUpdate = this.resumeData.ChangedId;/* change */
            let name = document.getElementById("resumeUpdateName").value;
            let gender = document.getElementById("resumeUpdateGender").value;
            let phoneNumber = document.getElementById("resumeUpdatePhoneNumber").value;
            let cardNumber = document.getElementById("resumeUpdateCardNumber").value;
            if(name===''||name===null||gender===''||gender===null||phoneNumber===''||phoneNumber===null||cardNumber===''||cardNumber===null){/* change */
                alert("检测到空值！");
                return;
            }
            console.log(idUpdate);
            let billboardDate = new Date().toISOString();
            let billboardDateFinal = this.UTCtoGMT8(billboardDate);
            let p = {/* change */
                "id":idUpdate,
                "name":name,
                "gender":gender,
                "phoneNumber":phoneNumber,
                "cardNumber":cardNumber
            }
            axios({
                url:url+"resume/",/* change */
                method:"PUT",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===40040||res.data.code==='40040'){
                    alert("更新失败");
                    return;
                }
                alert("成功更新!");
                this.resumeData.NeedToUpdateData.data='';/* change */
                this.resumeData.ChangedId=0;/* change */
                this.resumeData.resumeGS = true ;/* change */
                this.resumeData.resumeWork = false ;/* change */
                this.resumeData.resumeEdit = false ;/* change */
                this.getAllResume();/* change */
            })
        },
        resumeDeleteConfirm() {/* change */
            var aDc = confirm("是否删除？");
            if(aDc===true){
                this.resumeDelete();/* change */
            }
            else{
                alert("已取消");
            }
        },
        resumeDelete() {/* change */
            let idDelete = this.resumeData.ChangedId;/* change */
            console.log(idDelete);
            let p={
                "id":idDelete
            }
            axios({
                url:url+"resume/",/* change */
                method:"DELETE",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                }
            }).then((res)=>{
                console.log(res.data.code);
                console.log(typeof res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code!=20021){
                    alert("删除失败");
                    return;
                }
                alert("成功删除!");
                this.resumeData.NeedToUpdateData.data='';/* change */
                this.resumeData.ChangedId=0;/* change */
                this.resumeData.resumeGS = true ;/* change */
                this.resumeData.resumeWork = false ;/* change */
                this.resumeData.resumeEdit = false ;/* change */
                this.getAllResume();/* change */
            })
        },
        resumeDownload(){
            let idUpdate = this.resumeData.ChangedId;
            axios({
                url:url+'resume/'+idUpdate,
                method:"GET"
            }).then((res)=>{
                this.resumeData.data=res.data.data;
                if(res.data.data.file==''){
                    alert("无文件");
                    return;
                }
                let a = document.createElement("a");
                a.style.display = "none";
                /*let blob = dataURLToBlob(dom.toDataURL("image/png"));*/
                a.setAttribute("href", URL.createObjectURL(new Blob([this.strToByte(atob(res.data.data.file))],{type:"application/msword"})));
                /*console.log(atob(res.data.data.file));
                console.log(this.strToByte(atob(res.data.data.file)));*/
                //这块是保存图片操作  可以设置保存的图片的信息
                a.setAttribute("download", idUpdate + ".docx");
                document.body.appendChild(a);
                a.click();
                URL.revokeObjectURL(res.data.data.file);
                document.body.removeChild(a);
            })
        }
    },
    beforeMount(){
        this.LoginStatusCheck();
        console.log(this.theDate);
        this.getCountOfUsers();
        this.getCountOfArticle();
        this.getCountOfDoctor();
        this.getCountOfOffice();
        this.getCountOfQueue();
        this.getCountOfResume();
        this.getCountOfBillboard();
        this.getCountOfKnowledge();
        console.log(this.countAll);
    }
}
const app = Vue.createApp(App)
app.mount("#adminDashBoard9")

const loginApp = {
    data(){
        return{
            noVisable:false,
            formData:{},
            code:0,
            msg:'',
            data:[],
            OADdata:[{
                "id":"",
                "officeName":"",
                "officeInfo":"",
                "doctorInOffice":{
                    "0":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "1":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "2":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "3":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "4":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "5":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "6":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "10":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "11":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "12":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "13":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "14":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "15":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "16":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "20":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "21":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "22":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "23":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "24":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "25":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                    "26":[{
                        "id": "",
                        "name": "",
                        "office": "",
                        "skill": "",
                        "info": "",
                        "morning": "",
                        "afternoon": "",
                        "night": ""
                    }],
                }
            }],
            doctor_data:{
                "code":0,
                "msg":"",
                "data":[]
            }
        }
    },
    methods:{
        do_login:function (){
            var uName = document.getElementById("theLoginName").value;
            var uPw = document.getElementById('thePassword').value;
            var p = {
                "uName":uName,
                "uPw":uPw
            }
            console.log(p);
            axios({
                xhrFields: {
                    withCredentials: true,}
                ,
                url:url+"users/login/",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /*'Content-Type': 'application/json;charset=UTF-8'*/
                },
            }).then((res)=>{
                this.code=res.data.code;
                this.msg=res.data.msg;
                this.data=res.data.data;
                console.log(res.data.code);
                console.log(res.data.msg);
                console.log(res.data.data);
                if(res.data.code===30030||res.data.msg==='404'){
                    this.noVisable = true;
                    this.do_reset();
                }
                else{
                    console.log("-------------------------------------");
                    /*this.$router.push("adminAll");*/
                    window.location.href="adminAll.html";
                    /*this.$router.push({
                        path:'/adminAll',
                        name:"adminAll"
                    });*/
                }
            })
        },
        closeNo:function (){
            this.noVisable = false;
        },
        do_reset:function (){
            document.getElementById('thePassword').value = null;
            document.getElementById("theLoginName").value = null;
            /*console.log(this.OADdata[0]["doctorInOffice"]["1"][0]);//todo 访问下标*/
        },
        /*do_init(){
            let p = {
                "officeName":"肿瘤内科"
            }
            axios({
                url:url+"offices/LOAD",
                method:"POST",
                data:JSON.stringify(p),
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                    /!*'Content-Type': 'application/json;charset=UTF-8'*!/
                }
            }).then((res)=>{
                this.OADdata = res.data.data;
                console.log(res.data.data);
            })
        },
        do_init_2(){
            axios({
                url:url+"doctor/",
                method:"GET",
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then((res)=>{
                this.doctor_data.data = res.data.data;
                console.log(res.data.data);
            })
        }*/
    },
    beforeMount() {
        /*this.do_init_2();
        this.do_init();*/
        console.log(new Date());
    }
}
/*const routes = [{ path: '/home', redirect: '/' }]*/
/*const routes = [
    {
        path:'/adminAll',
        name:'adminAll',
        component:()=>import('adminAll.html')
    }
]
const router = VueRouter.createRouter({
    history:VueRouter.createWebHashHistory(),
    routes:routes,
})*/
const app2 = Vue.createApp(loginApp)
/*app2.use(router)*/
app2.mount("#login")