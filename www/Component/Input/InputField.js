Components.$init("/Input/InputField",function(){
    this.extends("/Input/ToolTip");
    let $this=this;
    let id = uuid();
    let type="text";
    let input,label;
    if(this.hasAttribute("type"))
        type=this.getAttribute("type");
    
    input = create("input",null,{
        type: type,
        id: id
    });
    label = create("label",$this.getAttribute("label"),{
        for: id
    });
    this.classList.add("input-field");
    this.classList.add("col");
    this.appendChild(input);
    this.appendChild(label);

    this.value=function(){
        if(type === "number"){
            if(input.value.trim() === "" || isNaN(input.value))
                return null;
            return parseInt(input.value);
        }
        return input.value;
    };
});