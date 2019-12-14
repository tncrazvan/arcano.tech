if(!server.issetUrlQuery("id")){
    status(STATUS_BAD_REQUEST);
    return send("Please specify an id name");
}
require("../../lib/database/connection.js");
let id = server.getUrlQuery("id");

db.prepare("select * from visibility where visibility_id like ?");
db.setString(1,id);
send("\nexploring id "+id);
db.execute().forEach(item=>{
    send("\n"+item.visibility_id);
});
db.close();