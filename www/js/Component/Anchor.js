Components.$init("/A",function(){
    if(this.hasAttribute("btn")){
        this.extends("/Buttons/PrimaryButton");
    }else if(this.hasAttribute("secondary-btn")){
        this.extends("/Buttons/SecondaryButton");
    }
});