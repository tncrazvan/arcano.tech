Components.$init("/Menu/TopMenu",function(){
    this.id="menu";
    let buttons = this.querySelectorAll("PrimaryButton");
    buttons.forEach(button => {
        button.onclick=e=>{
            main_content.data.state = main_content.data[button.getAttribute("state")];
            state(main_content.data.state);
        };
    });
});