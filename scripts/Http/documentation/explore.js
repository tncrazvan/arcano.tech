if(!server.issetUrlQuery("id")){
    status(STATUS_BAD_REQUEST);
    return send("Please specify a id name");
}
let id = server.getUrlQuery("id");
let db = mysql({
    username: "root",
    password: "",
    hostname: "localhost",
    database: "arcanotech"
});

db.prepare("select * from visibility where visibility_id like :id");
db.bindString(":id",id);
send("\nexploring id "+id);
db.execute().forEach(item=>{
    send("\n"+item.visibility_id);
});
