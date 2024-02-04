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
                    sh 'ls -al'
                    sh 'cd build'
                    sh 'ls -al'
                    sh 'cd libs'
                    sh 'ls -al'
                    sh 'docker build --tag pua .'
                }
            }
        }
    }
}
