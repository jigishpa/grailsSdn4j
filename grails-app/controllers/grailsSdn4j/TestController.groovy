package grailsSdn4j

import grailsSdn4j.GrailsN4jGraph

class TestController {

    GrailsN4jGraph grailsGraph

    def index() { 
	render grailsGraph.testThatWeCanAccessDatabase()
	}
}
