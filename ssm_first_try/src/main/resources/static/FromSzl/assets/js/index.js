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
                countResume:0
            },
            UserUpdate:true,
            showDashBoard:true,
            showUsers:false,
            showArticle:false,
            showDoctor:false,
            showOffices:false,
            showQueue:false,
            showResume:false,
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
            do_settings(){
                console.log("setting");
            },
            do_logout(){
                console.log("logout");
            },
            getCountOfUsers(){
                axios({
                    url:url+'users'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countUsers=res.data.data;
                    console.log(res.data.data);
                })
            },
            getCountOfDoctor() {
                axios({
                    url:url+'doctor'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countDoctor=res.data.data;
                    console.log(res.data.data);
                })
            },
            getCountOfOffice() {
                axios({
                    url:url+'offices'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countOffices=res.data.data;
                    console.log(res.data.data);
                })
            },
            getCountOfQueue() {
                axios({
                    url:url+'queuereg'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countQueue=res.data.data;
                    console.log(res.data.data);
                })
            },
            getCountOfResume() {
                axios({
                    url:url+'resume'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countResume=res.data.data;
                    console.log(res.data.data);
                })
            },
            getCountOfArticle() {
                axios({
                    url:url+'article'+'/count/',
                    method:"GET"
                }).then((res)=>{
                    this.countAll.countArticle=res.data.data;
                    console.log(res.data.data);
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
                this.showDashBoard=true;
                this.showUsers=false;
                this.showDoctor=false;
                this.showOffices=false;
                this.showQueue=false;
                this.showResume=false;
                this.showArticle=false;
                this.getCountOfUsers();
                this.getCountOfArticle();
                this.getCountOfDoctor();
                this.getCountOfOffice();
                this.getCountOfQueue();
                this.getCountOfResume();
            },
            /*todo doctor js*/
            doctorIn (){
                this.Msg2 = "Doctor management";
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
                let articleDate = new Date().toISOString();
                let articleDateFinal = this.UTCtoGMT8(articleDate);
                let p = {
                    "id":idUpdate,
                    "name":name,
                    "office":office,
                    "skill":skill,
                    "info":info,
                    "date":articleDateFinal
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
            data:[]
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
        }
    },
    beforeMount() {
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