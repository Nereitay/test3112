pipeline {
 agent any
 stages {
   stage('Pull') {
    steps {
      checkout([$class: 'GitSCM', branches: [[name: '*/master']],
doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
userRemoteConfigs: [[credentialsId: '68f2087f-a034-4d39-a9ff-1f776dd3dfa8', url:
'git@192.168.66.100:test/test.git']]])
    }
  }
   stage('Build') {
    steps {
      sh label: '', script: 'mvn clean package'
    }
  }
   stage('Deploy') {
    steps {
      deploy adapters: [tomcat8(credentialsId: 'afc43e5e-4a4e-4de6-984f-
b1d5a254e434', path: '', url: 'http://192.168.66.102:8080')], contextPath: null,
war: 'target/*.war'
    }
  }
 }
}