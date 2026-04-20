pipeline {
    agent any

    environment {
        APP_NAME = 'Achat'
    }

    stages {

        stage('Initialize') {
            steps {
                echo "Starting pipeline for ${APP_NAME}"
            }
        }

        stage('Build & Package') {
            steps {
                echo 'Building project...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Testing') {
            steps {
                echo 'Executing tests...'
                sh 'mvn test'
            }
        }

        stage('Execution') {
            steps {
                echo 'Running application...'
                sh 'nohup java -jar target/*.jar > app.log 2>&1 &'
            }
        }

        // ✅ FIX: moved inside stages
        stage('SonarQube Analysis') {
            steps {
                echo 'Analyzing code with SonarQube...'

                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        // ✅ (optional but important for your project)
        stage('Deploy to Nexus') {
            steps {
                echo 'Deploying artifact to Nexus...'
                sh 'mvn deploy'
            }
        }
    }

    post {
        success {
            echo "${APP_NAME} pipeline executed successfully"
        }
        failure {
            echo "${APP_NAME} pipeline failed"
        }
        always {
            echo "End of pipeline"
        }
    }
}