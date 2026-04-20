// Alternative Jenkins pipeline structure using environment and steps block

pipeline {
    agent any

    // Global environment variables
    environment {
        APP_NAME = 'Achat'
    }

    stages {

        stage('Initialize') {
            steps {
                echo " Starting pipeline for ${APP_NAME}"
            }
        }

        stage('Build & Package') {
            steps {
                script {
                    echo ' Building project...'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Testing') {
            steps {
                script {
                    echo ' Executing tests...'
                    sh 'mvn test'
                }
            }
        }

        stage('Execution') {
            steps {
                script {
                    echo ' Running application...'
                    sh 'nohup java -jar target/*.jar > app.log 2>&1 &'
                }
            }
        }
    }
       stage('SonarQube Analysis') {
            steps {
                echo 'Analyzing code with SonarQube...'

                dir('achat/achat') {
                    withSonarQubeEnv('Sonar') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

    post {
        success {
            echo " ${APP_NAME} pipeline executed successfully"
        }
        failure {
            echo " ${APP_NAME} pipeline failed"
        }
        always {
            echo " End of pipeline"
        }
    }
}
