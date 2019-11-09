if(method !== "GET")
    header("@Status",STATUS_BAD_REQUEST);
else{
    let f = file("../../index.html");
    send(f.read());
}