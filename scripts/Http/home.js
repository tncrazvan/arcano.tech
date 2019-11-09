if(method !== "GET")
    header("@Status",STATUS_NOT_FOUND);
else{
    f = file("../../index.html");
    send(f.read());
}