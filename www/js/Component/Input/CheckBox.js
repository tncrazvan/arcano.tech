Components.$init("/CheckBox",function(){
    this.extends("/ToolTip");
    let $this=this;
    let id = uuid();
    if(this.hasAttribute("type"))
        type=this.getAttribute("type");

    let span = create("span",$this.getAttribute("label"));
    let input = create("input.filled-in",null,{
        type: "checkbox",
        id: id
    });
    
    if(this.hasAttribute("checked")){
        input.setAttribute("checked",this.getAttribute("checked"));
    }

    let label = create("label");
    label.appendChild(input);
    label.appendChild(span);
    this.appendChild(label);
    
    this.classList.add("input-field");
    this.classList.add("col");

    this.setChecked=function(value){
        input.setAttribute("checked",value);
    };

    this.value=function(){
        return input.checked;
    };
});