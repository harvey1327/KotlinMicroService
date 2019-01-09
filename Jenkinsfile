node {
  currentBuild.displayName = "Branch Name Here"

  stage('Checkout'){
    checkout scm
  }
  stage('Build'){
    sh './gradlew build'
  }
}
