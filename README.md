PRIDE Cluster web-service
=========================

A RESTful web service providing access to the European Bioinformatics Institute PRIDE Cluster resource.

#### End points

Detailed and functional documentation will be available thanks to [Swagger](https://github.com/swagger-api) once the
web-service is deployed. As a summary, these are the available end-points for the web service:

* `ClusterSummary`: an abbreviated version of a cluster. Suitable for listing mainly.
* `ClusterDetail`: a more data loaded version of a given cluster. Suitable for detailed views.
* `PsmSummary`: an abbreviated version of a PSM.  
* `SpectrumSummary`: an abbreviated version of a Spectrum. Includes consensus sepctrum for a given cluster.  

#### Some deign notes
* `ClusterDetail` and `ClusterSummary` are provided as separate resources. Past experiences with the
[PRIDE Archive web service](http://www.ebi.ac.uk/pride/ws/archive/) demonstrated that having a single resource
(e.g. 'project') providing both `ProjectDetail` and `ProjectSummary` depending on what individual end point was
requested, even when absolutely correct, might be not so easy to understand from the point of view of client
libraries and web apps.
* With the same rational behind, spectra and psms will be independent WS endpoints, instead of being part of `ClusterDetail`.
Doing so we can better manage resources in the client, since this end point can potentially return long collections
of results.
