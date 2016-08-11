#!/usr/bin/env groovy

node
{
  currentBuild.result = "SUCCESS"
  def mvnHome = tool 'maven-3.3.3'
  env.PATH = "${mvnHome}/bin:${env.PATH}"

  try
  {
    stage 'Checkout'
      checkout scm

    stage 'Client build'
      dir('client') { sh 'mvn install' }

    stage 'Server build'
      dir('server') {
        sh 'mvn install'
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
      }

    stage 'Publish'
      build 'capra-publisher'
      /* def server = Artifactory.server('503750253@1369127525305')
      def uploadSpec = """{
        "files": [
          {
            "pattern": "client/target/*.jar",
            "target": "libs-snapshot-local"
          },
          {
            "pattern": "server/target/*.jar",
            "target": "libs-snapshot-local"
          }
        ]
      }"""
      server.upload(uploadSpec)*/

    stage 'Docker'
      dir('server') {
        sh "mvn docker:build -DpushImageTags -DdockerImageTag=latest -DdockerImageTag=${env.BUILD_NUMBER}"
      }

    stage 'Cleanup'
      echo 'prune and cleanup'
      dir('client') {
        sh 'mvn clean'
      }
      dir('server') {
        sh 'mvn clean'
      }
  }
  catch ( err )
  {
      currentBuild.result = "FAILURE"
      slackSend "Shame on ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
      throw err
  }
}
