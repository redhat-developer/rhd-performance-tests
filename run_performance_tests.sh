#!/bin/bash

function build {
  echo "\n Building the image \n"
  docker-compose -p rhd_perf_testing build
  echo "\n Test environment up and running. Running performance tests \n"
}

function run_tests_in_docker {
  export PROPERTIES="-DdrupalUser=$RHD_DRUPAL_USER -DdrupalUserPassword=$RHD_DRUPAL_PASSWORD -DbaseUrl=$RHD_PERF_TEST_URL -DmaxDuration=$RHD_MAX_DURATION -DrampUpUsers=$RHD_USER_RAMP -DrampUpAdmins=$RHD_ADMIN_RAMP"
  docker-compose -p rhd_perf_testing run --rm --no-deps rhd_perf_testing sbt "; clean; set javaOptions ++= \"${PROPERTIES}\".split(\" \").toSeq ; gatling:testOnly perfTests.simulations.LoadTest"
  echo "\n Completed performance tests. \n"
}

build
run_tests_in_docker
