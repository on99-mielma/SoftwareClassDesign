
//for_headNav
function goBillboard(){
    localStorage.setItem("type","billboard");
}
function goNews(){
    localStorage.setItem("type","news");
}
function goKnowledge(){
    localStorage.setItem("type","knowledge");
}

function gotoart(id){
    localStorage.setItem("article_id",id);
    localStorage.setItem("type","news");
}