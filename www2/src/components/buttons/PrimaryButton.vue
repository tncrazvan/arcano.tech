<template>
   <a class="waves-effect waves-dark-purple btn PrimaryButton">
      <i class="material-icons left" v-html="icon"></i>
      <span v-text="text"></span>
   </a>
</template>

<script>
export default {
   name: "PrimaryButton",
   data: function() {
      return{
         text: "",
         icon: ""
      }
   },
   methods: {
      __construct(text,icon){
         this.text = text;
         this.icon = icon;
      }
   },
   mounted(){
      this.__construct(this.$el.getAttribute("text"),this.$el.getAttribute("icon"));
   }
};
</script>