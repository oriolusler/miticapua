pipeline {
    agent any

    environment {
        DB_PASSWORD = credentials('pua-db-password')
    }

    stages {
        stage('Building JAR') {
            steps {
                echo 'Building JAR'
                script {
                    sh './gradlew clean'
                    sh './gradlew bootJar'
                }
            }
        }
        stage('Building docker image') {
            steps {
                script {
                    sh 'docker build --tag pua .'
                }
            }
        }
        stage('Stoping old docker container') {
            steps {
                script {
                    sh 'docker stop pua-app'
                }
            }
        }
        stage('Remove old docker container') {
            steps {
                script {
                    sh 'docker rm pua-app'
                }
            }
        }
        stage('Run new version') {
            steps {
                script {
                    sh 'docker run  -p 8082:8080 --name pua-app -e DB_USER=root -e DB_NAME=pua -e DB_PASSWORD=$DB_PASSWORD -e DB_HOST=192.168.0.220 -e DB_PORT=8009 pua:latest'
                }
            }
        }
    }
}
