#!/usr/bin/env groovy

node
{
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

    if ( ! env.BRANCH_NAME.startsWith('PR-') ) {
      stage 'Publish'
        build 'capri-publisher'
    }

    stage 'Docker'
      dir('server') {
        if ( ! env.BRANCH_NAME.startsWith('PR-') ) {
          sh "mvn docker:build -DpushImage -DpushImageTags -DdockerImageTag=latest -DdockerImageTag=${env.BUILD_NUMBER}"
        }
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
