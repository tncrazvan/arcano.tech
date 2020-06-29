<script>
    import {navigate} from 'svelte-routing';
    import {fly} from 'svelte/transition';
	import Coding from './../components/Coding.svelte';
	import {
		Page,
		Block,
        Button,
        Link,
        Icon
	} from 'framework7-svelte';


    let maven_create_cmd = "\
mvn archetype:generate\n\\\
-DgroupId={project-packaging}\n\\\
-DartifactId={project-name}\n\\\
-DarchetypeArtifactId={maven-template}\n\\\
-DinteractiveMode=false";

    let http_json = "\
{\n\
    \"entryPoint\":\"index.html\",\n\
    \"webRoot\": \"www\",\n\
    \"port\": 80,\n\
    \"bindAddress\": \"::\"\n\
}";

	let event_listener = "package com.company.department.myapp;\n\
\n\
import com.github.tncrazvan.arcano.Arcano;\n\
import com.github.tncrazvan.arcano.SharedObject;\n\
import com.github.tncrazvan.arcano.http.HttpResponse;\n\
import com.github.tncrazvan.arcano.tool.system.ServerFile;\n\
\n\
public class Starter{\n\
    public static void main(final String[] args) {\n\
        Arcano server = new Arcano();\n\
\n\
        server.addHttpEventListener(\"*\",\"@404\",e->{\n\
            ServerFile file = new ServerFile(e.reader.so.config.webRoot,String.join(\"/\",e.reader.location));\n\
            if(file.exists())\n\
                return new HttpResponse(file);\n\
            else return SharedObject.RESPONSE_NOT_FOUND;\n\
        });\n\
\n\
        server.addHttpEventListener(\"GET\",\"/test\",e->{\n\
            return \"this is a test!\";\n\
        });\n\
\n\
        server.listen(args);\n\
    }\n\
}\n\
";

    function create(){
        navigate("/create");
    }
</script>

<Page>
    <Block style="margin: auto; width: 75rem">
        <div transition:fly={{x:100,duration:500}}>







            <br />
            <br />
            <br />
            <h1>
                <Icon style="font-size: 3rem; top: -3px" material="info" />
                <span style="font-size: 2rem">&nbsp;&nbsp;&nbsp;What is Arcano?</span>
            </h1>
            <p>
                Arcano is a lightweight web server written in Java.<br />
                It offers a few unique features such as an MVC design pattern, a WebSocket API, 
                an Http API and a shell scripting API, which allows your http controllers to read the standard output of your shell scripts and redirect it
                as an http response, much like apache servers do with php scripts (<i>yes you can <u>technically</u> execute php scripts. For more on this refer to <a target="_blank" href="https://github.com/tncrazvan/jphp">https://github.com/tncrazvan/jphp</a>.</i>).
            </p>
            <h2>How to start</h2>
            <ul class="collection">
                <li class="collection-item">
                    <h4>Make sure you have JDK 13+ installed</h4>
                    You can download the latest JDK from the official oracle website.<br />
                    <Button style="display: inline-block" onClick={()=>window.open("https://www.oracle.com/technetwork/java/javase/downloads/index.html")}>Download JDK</Button>
                </li>
                <li class="collection-item">
                    <h4>Install Maven</h4>
                    In order to import the server library in your project you'll need Maven, a java dependency manager.<br />
                    
                    You can download the latest maven version from the official maven website.<br />
                    <Button style="display: inline-block" onClick={()=>window.open("https://maven.apache.org/download.cgi")}>Download Maven</Button>
                </li>
                <li class="collection-item">
                    <h4>Make a new Maven project</h4>
                    You have 3 options.
                    <ul class="collection">
                        <li class="collection-item">
                            More often than not your IDE will have some way of automatically creating a Maven project for you, for example 
                            <Button style="display: inline-block" onClick={()=>window.open("https://netbeans.org/downloads")}>Netbeans</Button>
                            comes with a default Maven project template.<br />
                        </li>
                        <li class="collection-item">
                            If your IDE does not offer a quick way to create a Maven project you can just make one from your terminal like so:<br />
                            <Coding language="bash">{maven_create_cmd}</Coding><br />
                            This will setup a new maven project and directory layout.
                        </li>
                        <li class="collection-item">
                            You can also use the 
                            <Button style="display: inline-block" onClick={create} >Create&nbsp;&nbsp;&nbsp;
                                <Icon material="add_circle" />
                            </Button> 
                            form on this website.<br />
                            <ol>
                                <li>Fill the form</li>
                                <li>Click the <Button style="display: inline-block">DOWNLOAD</Button> button</li>
                                <li>Extract the zip file</li>
                            </ol><br />
                            This wil not only setup a Maven project for you, but it will also setup all the dependencies needed for Arcano and everything you need to compile and run
                            your project.
                        </li>
                    </ul>
                    <br />
                    After making a new Maven project, you'll have to add Arcano as a dependency to your project.<br />
                    To do so you need to modify your <b>pom.xml</b> file.<br />
                    First you need to define the repository where the library releases are published.
                    The current repository is hosted on github.<br />
                    <Coding language="xml">&lt;repository&gt;
    &lt;id&gt;com.github.tncrazvan.arcano&lt;/id&gt;
    &lt;name&gt;Arcano&lt;/name&gt;
    &lt;url&gt;https://raw.githubusercontent.com/tncrazvan/Arcano/maven-repository&lt;/url&gt;
&lt;/repository&gt;</Coding><br /><br />
        Then you need to add the dependency:<br />
        <Coding language="xml">&lt;dependency&gt;
    &lt;groupId&gt;com.github.tncrazvan&lt;/groupId&gt;
    &lt;artifactId&gt;Arcano&lt;/artifactId&gt;
    &lt;version&gt;1.1.0&lt;/version&gt;
&lt;dependency&gt;</Coding><br /><br />

                    Now you can <b><u>download the dependencies</u></b>.<br />
                    <ul class="collection">
                        <li class="collection-item">
                            If you're using NetBeans you can simply <b>Build</b> your project, it will download your dependencies automatically.
                        </li>
                        <li class="collection-item">
                            If you're using the terminal, navigate to wherever your <a id="pom" href="#pom">pom.xml</a> file is located and run
                            <Coding language="bash">mvn install</Coding><br />
                            This will download all your dependencies.
                        </li>
                        <li class="collection-item">
                            If you used the web form you will notice the project you downloaded comes with a few scripts<br />
                            <Coding language="bash">512 Feb 22 14:44 .
512 Feb 22 14:14 ..
512 Feb 22 14:05 .git
163 Feb 22 01:00 .gitignore
224 Feb 22 01:00 http.json
20 Feb 22 01:00 <u><b>install</b></u>
512 Feb 22 14:08 java
83 Feb 22 01:00 start
86 Feb 22 01:00 update
512 Feb 22 01:00 www</Coding><br />
                                Run the <u><b>install</b></u> script to download your dependencies.
                        </li>
                    </ul>
                </li>
                <li class="collection-item">
                    <h4>Configuration</h4>
                    Before running your server you need to make a configuration file to feed to your server.<br />
                    Create a new <b>http.json</b> file. This will contain a json object.<br />
                    Make sure your object defines the <b>entryPoint</b>, <b>webRoot</b>, <b>port</b> and <b>bindAddress</b> properties, like so:<br />
                    <Coding language="json">{http_json}</Coding><br /><br />
                    The <b>entryPoint</b> property defines the entry point of your application. This is relative to the <b>webRoot</b>.
                    <hr />
                    The <b>webRoot</b> property defines the root of your application. Your <b>entryPoint</b> should be located inside your <b>webRoot</b>.
                    <hr />
                    The <b>port</b> property defines on which port your server should be listening to (usally port 80).
                    <hr />
                    The <b>bindAddress</b> property defines on which interface the server socket should bind on. The bind <b>::</b> means the <a href="https://en.wikipedia.org/wiki/Network_socket" target="_blank" >server socket</a> should bind on all available IPv4 or IPv6 interfaces.
                </li>
                <li class="collection-item">
                    <h4>Start your server</h4>
                    Make a new instance of the <b>Arcano</b> class and define a few http event listeners<br />
                    <Coding language="java">{event_listener}</Coding><br /><br />

                    <br />

                    Now you can compile your application and run it (<u>make sure you've installed the dependencies</u>) with <Coding language="bash">java -jar target/MyApp-1.0.0.jar ../http.json</Coding><br />
                    Alternativaly, if you're using the 
                    <Button style="display: inline-block" onClick={create} >Create&nbsp;&nbsp;&nbsp;
                        <Icon material="add_circle" />
                    </Button> 
                    form on this website, you can run the predefined <b>start</b> script: 
                    <Coding language="bash">sudo ./start</Coding>
                    it contains a normal maven instruction which will compile and run your server.<br /><br />
                </li>
            </ul><hr />
            <p style="text-align:center">
                For more information about the library check the 
                <Button style="display: inline-block">
                    DOCUMENTATION&nbsp;&nbsp;&nbsp;
                    <i style="font-size: 0.9em" class="fa fa-book"></i>
                </Button> (not yet available).
            </p>

            <br />
            <br />








        </div>
    </Block>
</Page>
