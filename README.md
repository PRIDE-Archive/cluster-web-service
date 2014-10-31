web-service
===========

#### Some deign notes
* `ClusterDetail` and `ClusterSummary` are provided as separate resources. Past experiences with the
[http://www.ebi.ac.uk/pride/ws/archive/](PRIDE Archive web service) demonstrated that having a single resource
(e.g. 'project') providing both `ProjectDetail` and `ProjectSummary` depending on what individual end point was
requested is confusing from the point of view of client libraries and web apps.
* Following a similar logic, spectra and psms will be independent WS endpoints, instead of being part of `ClusterDetail`.
Doing so we can better manage resources in the client, since this end point can potentially return long collections
of results.
