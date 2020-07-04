<script>
	import OtherFramework7Styles from './../shared/components/styles/OtherFramework7Styles.svelte';
	import GridStyles from './../shared/components/styles/GridStyles.svelte';
	import PaddingStyles from './../shared/components/styles/PaddingStyles.svelte';
	import MarginStyles from './../shared/components/styles/MarginStyles.svelte';
	import OtherStyles from './../shared/components/styles/OtherStyles.svelte';
	import TopNav from './components/TopNav.svelte';
	import CreatePage from './pages/CreatePage.svelte';
  import HomePage from './pages/HomePage.svelte';
  import {Router,Route,navigate} from 'svelte-routing';
  import {
    App,
    Panel,
    View,
    Page,
    Block,
    List,
    ListButton,
    Icon,
    FabButtons,
    FabButton,
  } from 'framework7-svelte';
  let url = window.location.pathname;
  let sidenav;
</script>
<App>
  <Router url="{url}">
    <Route path="{window.cordova?'/android_asset/www':''}/" component="{HomePage}" />
    <Route path="{window.cordova?'/android_asset/www':''}/home" component="{HomePage}" />
    <Route path="{window.cordova?'/android_asset/www':''}/index.html" component="{HomePage}" />
    <Route path="{window.cordova?'/android_asset/www':''}/create" component="{CreatePage}" />
  </Router>
  <TopNav />
</App>
<!-- STYLES COMPONENTS START -->
<OtherStyles />
<MarginStyles />
<PaddingStyles />
<GridStyles />
<OtherFramework7Styles />
<!-- STYLES COMPONENTS END -->
<style>
  :global(.list ul::before,.list ul::after){
    background-color: rgba(0,0,0,0.125);
  }
  :global(.list ul li){
    border-top: 1px solid rgba(0,0,0,0.125);
  }
  :global(#pre-style,#pre-loader){
    display: none;
  }
  /*############## MAIN STYLE ###############*/
  :global(*:not(input,textarea,p,span)){
    -webkit-touch-callout: none !important;
    -webkit-user-select: none !important;
  }
  :global(html){
    overscroll-behavior: none;
  }
  /* Custom color theme properties */
  :global(:root) {
    --f7-theme-color: #93280f;
    --f7-text-theme-color: #f1f1f1;
    --f7-text-color: #16101e;
    --f7-theme-color-shade: #93280f;
    --f7-theme-color-tint: #93280f;
  }
  :global(.hljs){
    background: rgba(0,0,0,0.05);
    border-radius: 0.3rem;
  }
  :global(hr){
		border: 0;
		height: 1px;
		background: rgba(0,0,0,0.125);
	}
</style>