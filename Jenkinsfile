pipeline {
    agent {
        label {
            label ""
            customWorkspace "C:/sippi/dev/react"
        }
    }
    
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
                bat 'npm run build'
            }
        }
        
        stage('Start') {
            steps {
                bat 'pm2 start server/index.js --name sippi-react-dev'
                bat 'pm2 save'
            }
        }
    }
}
