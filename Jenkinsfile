pipeline {
    agent any

    environment {
        target = 'dev'
        serviceName = ''
        workspace = ''
    }

    stages {
        stage('Setup environment') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        target = 'prod'
                    }

                    serviceName = "sippi-${target}-react"
                    workspace = "C:/sippi/${target}/react"
                }               
            }
        }

        stage('Prepare workspace') {
            steps {
                ws(workspace) {
                    checkout scm

                    bat 'rd build /s /q'
                    bat 'del .env'
                    bat "rn .env.${target} .env"
                }
            }
        }

        stage('Build') {
            steps {
                ws(workspace) {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Stop service') {
            steps {
                ws(workspace) {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat "pm2 stop ${serviceName}"
                        bat "pm2 delete ${serviceName}"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                ws(workspace) {
                    bat 'rd dist /s /q'
                    bat 'rn build dist'
                }
            }
        }

        stage('Start service') {
            steps {
                ws(workspace) {
                    bat "pm2 start server/index.js --name ${serviceName}"
                    bat 'pm2 save'
                }
            }
        }
    }   
}
