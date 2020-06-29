<script>
	import { onMount } from 'svelte';
	export let language = "";
	let code;
	onMount(()=>{
		window.hljs.highlightBlock(code);
	});
</script>

<pre><code bind:this={code} class="{language}" style="padding: 1rem"><slot></slot></code></pre>

<style>
	code{
		display: "inline";
		background: "transparent";
		padding: 0;
		margin: 0;
	}
	pre{
		margin: 0;
		padding-left: "0.5em";
		padding-right: "0.7em";
		padding-top: "0.1em";
		padding-bottom: "0.1em";
		border: "1px solid #2e2a38";
	}
</style>