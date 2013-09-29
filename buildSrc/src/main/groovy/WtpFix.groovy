import org.gradle.api.Project
import groovy.util.Node

public class WtpFix {
	def static removeDeployment(Node xml) {
		xml.'classpathentry'.each { entry ->
			if (entry.'attributes') {
				entry.remove(entry.'attributes')
			}
		}
	}

	def static customizeDeployment(Project gradleProject, Node xmlNode) {
		['dependent-module'].each { elementName ->
			xmlNode.'wb-module'."$elementName".each { elementNode ->
				if (elementNode.@handle.startsWith('module:/classpath/lib')) {
					xmlNode.'wb-module'[0].remove(elementNode)
				}
			}
		}

		gradleProject.configurations.runtime.each {
			String b = it.toString()
			if (!isSiblingProjectPath(gradleProject, b)) {
				addLibrary xmlNode.'wb-module'[0], '/WEB-INF/lib', 'module:/classpath/lib/' + b.replaceAll("\\\\","/") 
			}
		}
	}

	def static isSiblingProjectPath(project, libraryPath) {
		if (project.getParent() == null)
		return false

		def parentDir = project.getParent().projectDir.path
		if (libraryPath.startsWith(parentDir)) {
			return true
		}
		return false
	}

	def static addLibrary(parentNode, deployPath, handle) {
		Node node = parentNode.appendNode("dependent-module", ['deploy-path': deployPath, 'handle': handle])
		node.appendNode('dependency-type').setValue('uses')
	}
}