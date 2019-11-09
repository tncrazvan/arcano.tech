Components.$init("/MainContent",function(){
    this.data={
        state: "home",
        STATE_HOME:"home",
        STATE_QUICK:"quick",
        STATE_DOCUMENTATION:"documentation"
    };
    this.data.state = this.data.STATE_HOME;
    this.$origin=function(){
        this.id="main_content";
    };
});