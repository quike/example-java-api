COMPONENT					:= example-java-api
OWNER							:= quike
VERSION						:= $(shell (git describe --tags --always --dirty 2>/dev/null | sed 's/^v//') || echo "0.0.0-SNAPSHOT")

ifeq ($(VERSION),HEAD)
	VERSION := 0.0.0-SNAPSHOT
endif

.PHONY: version
version:
	@echo "Version: $(VERSION)"

.PHONY: build
build:
	@echo "Build"
	./mvnw spotless:check verify -B -Drevision=$(VERSION)

.PHONY: run-only
run-only:
	@echo "Run Only"
	java -jar "./target/$(COMPONENT)-$(VERSION).jar"

.PHONY: run
run: build run-only
	@echo "Run"

.PHONY: format
format:
		@echo "Format"
		./mvnw spotless:apply -B -Drevision=$(VERSION)
