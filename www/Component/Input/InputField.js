Components.$init("/Input/InputField",function(){
    let $this=this;
    let id = uuid();
    let input = create("input",null,{
        type:"text",
        id: id
    });
    let label = create("label",$this.getAttribute("label"),{
        for: id
    });
    if(this.hasAttribute("type"))
        input.setAttribute("type",this.getAttribute("type"));
    this.classList.add("input-field");
    this.classList.add("col");
    this.appendChild(input);
    this.appendChild(label);
});