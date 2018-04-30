# Spring Data Flow Demo

Supporting material for my talk on Spring Data Flow.

## What's included

* customer-data/ - A Spring Cloud Stream application that can be used as a Data Flow application.
* docker-compose.yml - Docker compose definition to set up Data Flow dependencies.
* stream-clicks.groovy - Mock customer activity data.

## Getting started

Getting data flow running is the first task.

* Docker & docker-compose should be installed.
* Groovy should be installed if you want to run the mock customer activity script (else you can manually post using something like Postman)

Run `docker-compose up` in the root folder. This does the following:
* Starts up a mysql
* Starts up rabbitmq
* Starts up redis

The config is specifically set to work with the rest of the commands in this README. Check the docker-compose file for configuration details.

Meanwhile, download the JAR for the [Local Data Flow Server](http://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-shell/1.3.0.RELEASE/spring-cloud-dataflow-shell-1.3.0.RELEASE.jar)

> There are many implementations of the server for different run times. In this
noddy example we simply use the local server which will start apps in a local JVM.

Once the dependencies are all up and running, in a new terminal window, start the Data Flow Server:

    java -jar spring-cloud-dataflow-server-local-1.3.0.RELEASE.jar \
        --spring.datasource.url=jdbc:mysql://localhost:3306/scdf \
	--spring.datasource.username=root \
	--spring.datasource.password=dataflow \
	--spring.datasource.driver-class-name=org.mariadb.jdbc.Driver \
	--spring.rabbitmq.host=127.0.0.1 \
	--spring.rabbitmq.port=5672 \
	--spring.rabbitmq.username=guest \
	--spring.rabbitmq.password=guest \
	--maven.localRepository=mylocal \
	--maven.remote-repositories.repo1.url=file:///C:/Users/jakerman/.m2/repository

This starts the Data Flow Server up pointing at our mysql, redis, & rabbitmq dependencies, as well as pointing it at a local Maven repository. Note that
you will need to change the Maven repository path to your own home directory if you want to be able to follow along all the way.

Next, there are two ways you can interact with the Data Flow Server:
* Via the shell - [download here](http://central.maven.org/maven2/org/springframework/cloud/spring-cloud-dataflow-shell/1.3.0.RELEASE/spring-cloud-dataflow-shell-1.3.0.RELEASE.jar)
* Via the GUI - [here](http://localhost:9393/dashboard/) when the Data Flow Server is runing)

The server will initally start up with no Apps installed. You can import application indivually, via the GUI or the shell.

To register all the Stream Starter applications (included building block applications):

    app import --uri http://bit.ly/Celsius-SR1-stream-applications-rabbit-maven

You can now start creating streams either via the GUI, or via the command line. For example:

    stream create --name httptest --definition "http --server.port=9000 | log" 
    stream deploy --name httptest --properties "app.http.security.basic.enabled=false"

If you check the console window you started your Data Flow Server in, you should see the name of the local log file it created. If you `tail -f` this you should
see the log service logging out the time.

## Custom Streams App

In the customer-data/ folder you'll see a Spring Cloud Stream application that serves as an example of creating your own Data Flow Applications. 
This example app simply receives customer activity json, takes the userId value, and looks up their name in an in memory hash map, adding it to the json and
passing it down to the next app.

Build this project and publish to your local maven repository.

You can then register the application with the data-flow server as follows:
	
    app register --name customer-data --type processor --uri maven://com.scottlogic.tb:customer-data:0.0.4-SNAPSHOT

Create the stream definition (note the path is set to my home - change it to yours):

    stream create --name httptest --definition "http --port=8333 | customer-data | file --directory=c:/Users/jakerman/dataflow-output --name=customer-data"

Deploy it:

    stream deploy --name httptest --properties "app.http.security.basic.enabled=false"

Now the pipeline is up and running, we can run the groovy script in the root directory to start posting fake customer activity data to our endpoint.

    groovy stream-clicks.groovy -u http://localhost:8333

In the same fashion as the first stream example, you should be able to tail the file you set in your file sink, in the stream definition. You should see
the customer activity appearing there, but the activity will have the customer's names appended.

This was written very quickly, so if you have any questions, contact me internally (if you work for Scott Logic) or hit me up on twitter @janakerman if not.
