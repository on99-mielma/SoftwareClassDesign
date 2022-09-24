const app1 = {
    data(){
        return{
            code:'0'
        }
    },
    methods:{
        change(){
            localStorage.setItem("aa","1");
            let a = localStorage.getItem("aa");
            this.code=a;
        },
        back(){
            localStorage.setItem("aa","2");
            let a = localStorage.getItem("aa");
            this.code=a;
        }
    },
    beforeMount(){
        this.change();
    }
}
const app = Vue.createApp(app1)
/*app.use(router)*/
app.mount("#zz")