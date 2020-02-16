Components.$init("/ToolTip",function(){
    let tooltipHTML;
    let position = "top";
    if(this.innerHTML.trim() !== ""){
        tooltipHTML = this.innerHTML;
        this.innerHTML="";
    }
    if(this.hasAttribute("tooltip-position")){
        position = this.getAttribute("tooltip-position");
    }

    if(tooltipHTML){
        let tooltip =  M.Tooltip.init(this, {
            html: tooltipHTML,
            position: position
        });

        this.addEventListener("focus",()=>{
            this.open();
        });

        this.addEventListener("blur",()=>{
            this.close();
        });

        this.open=function(){
            tooltip.open();
        };
    
        this.close=function(){
            tooltip.close();
        };
    }
});