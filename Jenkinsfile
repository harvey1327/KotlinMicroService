node {

  triggers{
    periodic(5)
  }
  stage('Checkout Project'){
    checkout scm
  }
  stage('Build with Gradlew'){
    sh './gradlew clean build'
  }
  stage('TODO: Build Docker Image'){
    echo "Building Docker Image"
  }
  stage('TODO: Deploy Image to K8 env'){
    echo "Deploying Image to K8 env"
  }
  stage('TODO: Run contract tests on service'){
    echo "Running contract tests on service"
  }
  stage('TODO: Push Docker Image'){
    echo "Pushing Docker Image"
  }
}
