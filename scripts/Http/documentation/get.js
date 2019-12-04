let db = mysql({
    username: "root",
    password: "",
    hostname: "localhost",
    database: "arcanotech"
});

db.prepare("select * from visibility");

db.execute().forEach(item=>{
    send("\n"+item.visibility_id);
});
