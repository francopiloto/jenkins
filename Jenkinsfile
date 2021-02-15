pipeline {
    agent any

    environment {
        ENVIRONMENT = 'dev'
    }

    stages {
        stage('Setup environment') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        ENVIRONMENT = 'prod'
                    }
                }

                echo 'environment: $ENVIRONMENT'
            }
        }

        stage('Stop service') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'pm2 stop sippi-react-$ENVIRONMENT'
                    bat 'pm2 delete sippi-react-$ENVIRONMENT'
                }
            }
        }

        stage('Prepare workspace') {
            steps {
                ws('C:/sippi/$ENVIRONMENT/react')
                checkout scm
                bat 'del .env'
                bat 'rn .env.$ENVIRONMENT .env'
            }
        }

        stage('Build') {
            steps {
                bat 'npm install'
                bat 'npm run build'
            }
        }

        stage('Start service') {
            steps {
                bat 'pm2 start server/index.js --name sippi-react-$ENVIRONMENT'
                bat 'pm2 save'
            }
        }
    }   
}
