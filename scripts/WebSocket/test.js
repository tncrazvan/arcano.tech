onOpen(function(){
    log("Connected");
    send("Hello there");
});

onMessage(function(data){
    log("RECEIVED: "+data);
});

onClose(function(){
    log("closed");
});