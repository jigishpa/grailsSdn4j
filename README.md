This is an attempt to integrate Grails and spring-data-neo4j.  Specifically, it attempts to use a simplified version of the example at 
https://spring.io/guides/gs/accessing-data-neo4j/

The issue is that the @Autowire statements in GrailsN4jGraph.java don't appear to be being picked up.  This causes the following failure:

------------------------------

ERROR context.GrailsContextLoaderListener  - Error initializing the application: Error creating bean with name 'neo4jMappingContext' defined in class path resource [grailsSdn4j/GrailsN4jGraph.class]:
...
nested exception is java.lang.IllegalArgumentException: requirement failed: Can't work with a null graph database

------------------------------

(comments by Jigish - http://github.com/jigishpa)
I suspect the issue is related to how component scanning works with Grails and Java code mixed together. It might also
have to do with the order in which beans are created in this situation and proxying of classes behind the scenes.

I made the following changes to make this work:

1. I went back to neo4j xml configuration and added a grails-app/conf/resources.xml with necessary neo4j configuration.

2. I also took out this out from Config.groovy --> grails.spring.bean.packages = ['grailsSdn4j']
(in fact, I made further progress by adding other neo4j and spring-data-neo4j packages to this list like so:
//grails.spring.bean.packages = ['grailsSdn4j', 'org.neo4j', 'org.springframework.data.neo4j']
But there were still other exceptions being caused.)

3. Removed the @EnableNeo4jRepositories annotation from src/java/grailsSdn4j/GrailsN4jGraph.java