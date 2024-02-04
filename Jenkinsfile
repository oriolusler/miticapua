pipeline {
    agent any

    stages {
        stage('Building JAR') {
            steps {
                echo 'Building JAR'
                script {
                    sh './gradlew clean'
                    sh './gradlew jar'
                }
            }
        }
        stage('Docker') {
            steps {
                echo 'Building image'
                script {
                    sh 'docker build --tag pua .'
                }
            }
        }
    }
}
