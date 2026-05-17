pipeline {
    agent any

    stages {

        stage('GitLeaks Scan') {
            steps {
                sh '''
                cd /vagrant/Projet-Devops-DevsSeCops

                docker run --rm \
                -v $(pwd):/path \
                zricethezav/gitleaks:latest \
                detect --source="/path"
                '''
            }
        }
        stage('OWASP Dependency Check') {
            steps {
                sh '''
                mvn org.owasp:dependency-check-maven:check
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                rm -rf target
                mvn dependency:purge-local-repository
                mvn clean package -U -DskipTests
                '''
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=achat \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=$SONAR_TOKEN
                        '''
                    }
                }
            }
        }

        stage('Nexus Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                cd /vagrant/Projet-Devops-DevsSeCops
                docker build -t achat-app .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                sh '''
                cd /vagrant/Projet-Devops-DevsSeCops
                docker-compose down || true
                docker-compose up -d --build
                '''
            }
        }
        stage('Docker Security Scan') {
            steps {
                sh '''
                trivy image --scanners vuln --timeout 20m projet-devops-devssecops_app
                '''
            }
        }
    }
}