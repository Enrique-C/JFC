pipeline {
    agent any
    triggers {
       pollSCM('H/5 * * * *')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		bat './gradlew -version'
                bat './gradlew wrapper'
		            archiveArtifacts 'build/libs/*.jar'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat './gradlew clean test'
		            junit 'build/test-results/test/*.xml'
            }
        }
        stage('Publish') {
            steps {
                echo 'Publish artifacts.'
                bat './gradlew artifactoryPublish -Partifactory_password=${USER_PASS} --console verbose'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
      success {
        mail to: "alanescalera.english@gmail.com",
        subject:"SUCCESS: ${currentBuild.fullDisplayName}",
        body: "Result: ${currentBuild.result}\
         Job: ${env.JOB_NAME}\
         Build: ${env.BUILD_NUMBER}\
         URL: ${env.BUILD_URL}"
      }
      failure {
        mail to: "alanescalera.english@gmail.com",
        subject:"FAILURE: ${currentBuild.fullDisplayName}",
        body: "Result: ${currentBuild.result}\
         Job: ${env.JOB_NAME}\
         Build: ${env.BUILD_NUMBER}\
         URL: ${env.BUILD_URL}"
      }
    }
}
