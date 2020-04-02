<template>
   <div class="navbar-top">
		<div class="button-list">
			<PrimaryButton text="Home"/>
		</div>
      <hr class="navbar-top-bottom-bar"/>
   </div>
</template>

<script>
import PrimaryButton from "./buttons/PrimaryButton.vue";
export default {
	name: "NavBarTop",
	components: {
		PrimaryButton
	}
};
</script>
