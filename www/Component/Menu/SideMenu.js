Components.$init("/Menu/SideMenu",function(){
    this.id="side_menu";
    let instance = M.Sidenav.init(this.querySelector("ul"), {
        draggable: true
    });

    this.open=function(){
        instance.open();
    };
    this.close=function(){
        instance.close();
    };

    let buttons = this.querySelectorAll("PrimaryButton");
    buttons.forEach(button => {
        button.css({
            width: percent(100)
        });
        button.onclick=e=>{
            main_content.data.state = main_content.data[button.getAttribute("state")];
            state(main_content.data.state);
            side_menu.close();
        };
    });
});