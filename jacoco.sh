#!/bin/bash
# Should match with what we run in .circleci/config.yml
./gradlew clean
./gradlew :lce:test
./gradlew jacocoTestReportMerged
open build/reports/jacoco/index.html
