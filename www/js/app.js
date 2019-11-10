(async function(){
    await app.template("App");
    use.route("home",function(location){
        main_content.data.state = main_content.data.STATE_HOME;
    });
    use.route("quick",function(location){
        main_content.data.state = main_content.data.STATE_QUICK;
    });
    use.route("documentation",function(location){
        main_content.data.state = main_content.data.STATE_DOCUMENTATION;
    });
    use.route("create",function(location){
        main_content.data.state = main_content.data.STATE_CREATE;
    });
})();