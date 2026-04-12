// Jenkins pipeline for Spring Boot project
// This pipeline automates build, test and execution steps

pipeline {
    agent any  // Run on any available Jenkins agent

    stages {

        // ==============================
        // BUILD STAGE
        // ==============================
        stage('Build') {
            steps {
                echo ' Building the project...'

                // Clean previous builds and package the application into a JAR
                // - clean: removes old target directory
                // - package: compiles code and creates executable JAR
                // -DskipTests: skip tests for faster build
                sh 'mvn clean package -DskipTests'
            }
        }

        // ==============================
        // TEST STAGE
        // ==============================
        stage('Test') {
            steps {
                echo ' Running tests...'

                // Execute unit tests to verify code correctness
                sh 'mvn test'
            }
        }

        // ==============================
        // RUN STAGE
        // ==============================
        stage('Run') {
            steps {
                echo ' Starting application...'

                // Run Spring Boot application in background
                // nohup: keeps app running after Jenkins finishes
                // > app.log: save logs into file
                // 2>&1: merge error and output logs
                // &: run in background
                sh 'nohup java -jar target/*.jar > app.log 2>&1 &'
            }
        }
    }

    // ==============================
    // POST ACTIONS
    // ==============================
    post {

        // Executed if pipeline succeeds
        success {
            echo ' Build and execution SUCCESS'
        }

        // Executed if pipeline fails
        failure {
            echo ' Build FAILED'
        }

        // Always executed
        always {
            echo ' Pipeline finished'
        }
    }
}