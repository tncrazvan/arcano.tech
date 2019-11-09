Components.$init("/Menu/TopMenu",function(){
    this.id="menu";
    let buttons = this.querySelectorAll("PrimaryButton");
    let i = 0;
    buttons.forEach(button => {
        i -= 5;
        button.css({
            backgroundColor: "#0f1114",
            left: pixel(i)
        });
        button.onclick=e=>{
            main_content.data.state = main_content.data[button.getAttribute("state")];
            state(main_content.data.state);
        };
    });
});