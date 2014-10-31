PRIDE Cluster web-service
=========================

A RESTful web service providing access to the European Bioinformatics Institute PRIDE Cluster resource.

#### End points

Detailed and functional documentation will be available thanks to [](Swagger) once the web-service is deployed. As a
summary, these are the available end-points for the web service:

* `ClusterSummary`: an abbreviated version of a cluster. Suitable for listing mainly.
* `ClusterDetail`: a more data loaded version of a given cluster. Suitable for detailed views.  

#### Some deign notes
* `ClusterDetail` and `ClusterSummary` are provided as separate resources. Past experiences with the
[http://www.ebi.ac.uk/pride/ws/archive/](PRIDE Archive web service) demonstrated that having a single resource
(e.g. 'project') providing both `ProjectDetail` and `ProjectSummary` depending on what individual end point was
requested is confusing from the point of view of client libraries and web apps.
* Following a similar logic, spectra and psms will be independent WS endpoints, instead of being part of `ClusterDetail`.
Doing so we can better manage resources in the client, since this end point can potentially return long collections
of results.
