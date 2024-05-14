# Kotlin Spring Boot boilerplate project

Boilerplate to create a kotlin spring boot project

## Project structure

* configuration
* controller
* gateway
* model
  * requests
* repository
* service
* utils


## TODO

- [ ] Add makefile and docker instructions
- [ ] Add example readme with instructions on how to run project
- [ ] Add db support example (to postgres)
- [ ] Update mockito kotlin or deprecate?
- [ ] ...

---

# Sample README

## Development

* Run locally: `make run`
* Dockerized tests: `make test`
* Local Tests: `make start-local-tests-dependencies`, then run the tests locally.

## Run the project via Intellij

Add the dev profile to the **VM Options**
`-Dspring.profiles.active=dev`

## Code formatting

We expected that you always send your code formatted, this is part of the Talkdesk approach for all Kotlin projects.
The configuration for Ktlint can be checked on the build.gradle.

To verify if code is formatted as expected just execute:

`./gradlew ktlintCheck`

To format the code just execute:

`./gradlew ktlintFormat`
