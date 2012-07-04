Blitz JavaSpaces (packaged for Maven/Rio)
=========================================

JavaSpaces, as an implementation of Space-Based architecture (tuple spaces) is a very powerful computing paradigm. Blitz (http://www.dancres.org/blitz/) provides a performant, persistent implementation of the JavaSpaces API, which is much more suitable for enterprise-grade use than the (in-memory only) reference implementation, Outrigger.

The Rio Project (https://github.com/dreedyman/Rio) is an open source technology that provides a dynamic architecture for developing, deploying and managing distributed systems composed of services.

About this project
------------------

Blitz Javaspaces is historically built, provisioned, and configured using legacy technology (built with apache ant + manual dependency management, manually installed, configured via JINI configuration files). 
**In other words, difficult.**

This project is a re-packaging of the Blitz project to build with Maven (suitable for deployment to the maven repo of your choice), and provides samples of how to dynamically provision Blitz instances as "first class" services in Rio. 
It is now **easy** to provision a reliable, persistent JavaSpace with Rio.

Building
--------

* Required software: Apache Maven (3.0+ recommended), Java (JDK) 1.6+, Rio 5.0+
* To build artifacts and install in local maven repo, run `mvn install`
* To run the integration tests (deploys to RIO, needs RIO_HOME set) run `mvn verify -Pintegration-test`
* See [sample deployment opstring](https://github.com/DawidLoubser/Blitz-JavaSpaces-for-Maven-and-Rio/blob/master/src/test/conf/deployment.groovy) for provisioning a Blitz instance with Rio
* See [Blitz Configuration] (http://www.dancres.org/bjspj/docs/docs/install_guide.html#start) for configuration options