### Releases and versions

At the beginning of a release cycle versions are  incremented synchronously for
 - bundles
 - features
 - products, if any
 - other modules of Passage project source base
 
according to the severity of changes, planned for the release. As the result each module version is equal to the release version.
 
Besides that, all EMF models also sustain changes each release, as nsURI of an EMF model in Passage project 
comprises version literal as well. This means public constant `*Package::eNS_URI` evolves every release, 
which change we do not treat as critical.
