pipeline {
    agent any
    
    stages {
        stage('Stop') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'pm2 stop sippi-react-dev'
                    bat 'pm2 delete sippi-react-dev'
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
                bat 'pm2 start teste.js --name sippi-react-dev'
                bat 'pm2 save'
            }
        }
    }
}
