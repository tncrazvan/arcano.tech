Components.$init("/Menu/TopMenu",function(){
    (async ()=>{
        await this.template("/Menu/TopMenu");
        this.id="menu";
        let buttons = this.querySelectorAll("PrimaryButton");
        let i = 0;
        buttons.forEach(button => {
            i -= 5;
            button.css({
                backgroundColor: "#2e1b43",
                left: pixel(i)
            });
            button.onclick=e=>{
                main_content.data.state = main_content.data[button.getAttribute("state")];
                state(main_content.data.state);
            };
        });
    })();
});