package projecttracker

class ProjectsController {
	def scaffold=true;
	
	def index = { redirect (action: current) }

	def current = {
		def allProjects = ListProjects.list()
		[allProjects:allProjects]
	}

	def overdue = { render "Order Valentines Day Package" }
}
