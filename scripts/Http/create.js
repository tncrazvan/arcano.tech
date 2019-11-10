if(method === "GET")
    require("index.html.js");
else{
    try{
        require("../lib/uuid.js");
        let archive = zip(uuid()+".zip");
        let jar = http.get("https://github.com/tncrazvan/Arcano/raw/maven-repository/com/github/tncrazvan/Arcano/1.1.0/Arcano-1.1.0.jar");
        if(!jar.isNull()){
            let data = JSON.parse(input.getString());
            
            archive.addEntry(data.webRoot+"/"+data.entryPoint,"<!DOCTYPE html>\n<html>\n\t<head></head>\n\t<body></body>\n</html>");

            archive.addEntry("http.json",input.getString().replace(/,/g,",\n\t").replace(/\{/g,"{\n\t").replace(/\}/g,"\n}"));
            archive.addEntry("arcano.jar",jar.getBytes());
            archive.make();
            let f = archive.getFile();
            send(f.read());
            f.delete();
        }
    }catch(e){
        send(e.message);
    }
}