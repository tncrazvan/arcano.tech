Components.$init("/Input/InputField",function(){
    this.extends("/Input/ToolTip");
    let $this=this;
    let id = uuid();
    let type="text";
    let input,label="",value="";
    if(this.hasAttribute("type"))
        type=this.getAttribute("type").trim();
    
    if(this.hasAttribute("value"))
        value=this.getAttribute("value").trim();

    input = create("input",null,{
        type: type,
        id: id
    });
    let firstClick=true;
    input.addEventListener("focus",e=>{
        if(!firstClick) return;
        firstClick = false;
        input.value = value;
    });

    label = create("label",$this.getAttribute("label"),{
        for: id
    });
    this.classList.add("input-field");
    this.classList.add("col");
    this.appendChild(input);
    this.appendChild(label);

    this.value=function(...v){
        if(v.length === 0){
            if(firstClick) return value;
            if(type === "number"){
                if(input.value.trim() === "" || isNaN(input.value))
                    return null;
                return parseInt(input.value);
            }
            return input.value.trim();
        }else{
            input.value = [...v].join("");
        }
    };
});