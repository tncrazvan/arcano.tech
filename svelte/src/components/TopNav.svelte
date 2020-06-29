<script>
	import {navigate} from 'svelte-routing';
	import {
		Button,
		Icon,
	} from 'framework7-svelte';
</script>
<div class="top-nav">
	<div class="top-nav-content">
		<Button onClick={()=>navigate("/")}>
			<Icon style="top: -1px" material="subject" />
			Home
		</Button>
		<Button onClick={()=>navigate("/create")}>
			<Icon style="top: -1px" material="add_circle" />
			Create
		</Button>
	</div>
</div>

<style>
	:global(.top-nav-content .button){
		display: inline-block;
		color: var(--f7-text-theme-color);
	}
	.top-nav{
		z-index: 2;
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		background-color: var(--f7-theme-color);
	}
	.top-nav .top-nav-content{
		width: 75rem;
		margin: auto;
		padding: 1rem;
	}
</style>