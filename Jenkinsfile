pipeline {
    agent any
    
    stages {
        stage('Stop') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'pm2 stop sippi-react-dev'
                }
            }
        }
        
        stage('Build') {
            steps {
                bat 'npm install'
                bat 'npm rum build'
            }
        }
        
        stage('Start') {
            steps {
                bat 'pm2 start ./server/index.js --name sippi-react-dev'
            }
        }
    }
}
