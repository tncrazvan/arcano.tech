<script>
	import {fly} from 'svelte/transition';
	import { 
		Page, 
		Block,
		Card,
		CardHeader,
		CardContent,
		CardFooter,
		Link,
		List,
		ListInput,
		ListItem,
		Icon,
		Button,
		Preloader,
	} from 'framework7-svelte';
	let namespace = "com.company.department";
	let appname = "MyApp";
	let port = "80";
	let bindAddress = "::";
	let webRoot = "www";
	let entryPoint = "index.html";
	let charset = "UTF-8";
	let timeout = "10000";
	let sessionTtl = "1440";
	let sendExceptions = false;
	let responseWrapper = false;
	let downloading = false;
	async function download(){
		downloading = true;
		let tmp = {
			serverRoot: "java",
			webRoot: webRoot,
			namespace: namespace.toLocaleLowerCase().replace(/\s+/g,""),
			appname: appname.toLocaleLowerCase().replace(/\s+/g,""),
			port: port,
			bindAddress: bindAddress,
			entryPoint: entryPoint,
			charset: charset,
			timeout: timeout,
			sendExceptions: sendExceptions,
			responseWrapper: responseWrapper
		};

		let data = {};
		Object.keys(tmp).map(key => {
			if(tmp[key] !== null && tmp[key] !== "")
				data[key] = tmp[key];
		});

		console.log(data);

		let content = JSON.stringify(data);
		const request = await fetch("/create",{
			method: "POST",
			body: content
		});
		const blob = await request.blob();
		const b64 = window.URL.createObjectURL(blob);
		const a = window.document.createElement("a");
		a.href = b64;
		let filename = appname;
		a.download = (filename===""?"app":filename)+".zip";
		a.click();
		window.URL.revokeObjectURL(b64);
		downloading = false;
	}
	let moreOptions;
</script>

<Page>
	<Block style="margin: auto; width: 75rem">
		<div transition:fly={{x:100,duration:500}}>
			<br />
			<br />
			<br />
			<h1>Application configuration</h1>
			<List noHairlinesMd>
				<ListInput
					label="Namespace"
					type="text"
					placeholder="Example: com.company.department"
					floatingLabel={!namespace}
					info="This is the namespace of your application. The value will be cast to lower case and white spaces will be removed automatically."
					clearButton
					bind:value={namespace}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="App Name"
					type="text"
					placeholder="Example: My App"
					floatingLabel={!appname}
					info="This is the name of your product. The value will be cast to lower case and white spaces will be removed automatically."
					clearButton
					bind:value={appname}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Port"
					type="number"
					placeholder="Example: 80"
					floatingLabel={!port}
					info="This is the port of your sockets will bind to."
					clearButton
					bind:value={port}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Bind Address"
					type="text"
					placeholder="Example: 127.0.0.1 for local development or :: for production"
					floatingLabel={!bindAddress}
					info="This is the interface your sockets will bind to."
					clearButton
					bind:value={bindAddress}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Web Root"
					type="text"
					placeholder="Example: www"
					floatingLabel={!webRoot}
					info="This is the directory your server will serve. It is relative to the location of the 'http.json' file"
					clearButton
					bind:value={webRoot}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Entry Point"
					type="text"
					placeholder="Example: index.html"
					floatingLabel={!entryPoint}
					info="This is the file your server will serve when requested with the ambiguous resource '/'."
					clearButton
					bind:value={entryPoint}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Charset"
					type="text"
					placeholder="Example: UTF-8"
					floatingLabel={!charset}
					info="This is the charset your server will use to serve your text data."
					clearButton
					bind:value={charset}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>

				<ListInput
					label="Timeout"
					type="text"
					placeholder="Example: 10000 (10 seconds)"
					floatingLabel={!timeout}
					info="This is the number of milliseconds the server will wait for a client that is not aborted the connection."
					clearButton
					bind:value={timeout}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>
				
				<ListInput
					label="Session TTL"
					type="text"
					placeholder="Example: 1440 (10 seconds)"
					floatingLabel={!sessionTtl}
					info="This is the number of seconds a session is allowed to live."
					clearButton
					bind:value={sessionTtl}
				>
					<i slot="media">
						<Icon material="settings" />
					</i>
				</ListInput>
			</List>
			

			<List mediaList>
				<ListItem
					checkbox
					bind:checked={sendExceptions}
					title="Send Exceptions"
					text="Check this if you want your server to send detailed information to the client whenever an Exception (that is not internally catched) triggers inside a controller."
				/>
				<ListItem
					checkbox
					bind:checked={responseWrapper}
					title="Response Wrapper"
					text="Check this if you want your server to wrap all the response messages in JavaScript Object Notation (JSON)"
				/>
			</List>
					

			<div class="grid-x-center">
				{#if downloading}
					<Preloader></Preloader>
				{:else}
					<Button onClick={download}>Download</Button>
				{/if}
			</div>
			<br />
			<br />
			<br />
			
		</div>
	</Block>
</Page>

<style>
	:global(.list-wrapper .list ul li){
		border: 1px solid;
		border-color: rgba(0,0,0,0.125);
	}
</style>