pipeline {
  environment {
    jobName = "${env.JOB_NAME}"
  }
  agent any
  triggers {
    pollSCM('H/15 * * * *')
  }
  stages {
    stage('Checkout Project') {
      steps {
        checkout scm
      }
    }
    // stage('Build with Gradlew') {
    //   steps {
    //     sh './gradlew clean build'
    //   }
    // }
    stage('Build Docker Image') {
      steps {
        sh "sudo docker build . -t $jobName:latest"
      }
    }
    stage('TODO: Deploy Image to K8 env') {
      steps {
        echo "Deploying Image to K8 env"
      }
    }
    stage('TODO: Run contract tests on service') {
      steps {
        echo "Running contract tests on service"
      }
    }
    stage('TODO: Push Docker Image') {
      steps {
        echo "Pushing Docker Image"
      }
    }
  }
}
