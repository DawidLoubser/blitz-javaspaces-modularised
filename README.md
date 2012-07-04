Blitz JavaSpaces (packaged for Maven/Rio)
=========================================

JavaSpaces, as an implementation of Space-Based architecture (tuple spaces) is a very powerful computing paradigm. Blitz (http://www.dancres.org/blitz/) provides a performant, persistent implementation of the JavaSpaces API, which is much more suitable for enterprise-grade use than the (in-memory only) reference implementation, Outrigger.

The Rio Project (https://github.com/dreedyman/Rio) is an open source technology that provides a dynamic architecture for developing, deploying and managing distributed systems composed of services.

About this project
------------------

Blitz Javaspaces is historically built, provisioned, and configured using legacy technology: Built via apache ant, manually installed, configured via JINI configuration files. 

This project is a re-packaging of the Blitz project to build with Maven (suitable for deployment to the maven repo of your choice), and provides samples of how to dynamically provision Blitz instances as "first class" services in Rio.
