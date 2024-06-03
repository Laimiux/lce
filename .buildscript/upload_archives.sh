./gradlew clean
./gradlew :lce:build -PreleaseBuild
./gradlew :lce-rxjava3:build -PreleaseBuild
./gradlew :lce-coroutines:build -PreleaseBuild
./gradlew :lce-test:build -PreleaseBuild

# Disabling parallelism and daemon sharing is required by the vanniktech maven publish plugin.
# Without those, the artifacts will be split across multiple (invalid) staging repositories.
./gradlew uploadArchives -PreleaseBuild --no-parallel --no-daemon
