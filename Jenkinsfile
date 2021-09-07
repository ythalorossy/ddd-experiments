pipeline {
  agent any
  stages {
    stage('Package') {
      steps {
        sh '/opt/maven/bin/mvn clean package'
      }
    }

    stage('Test') {
      steps {
        sh '/opt/maven/bin/mvn test'
      }
    }

  }
}