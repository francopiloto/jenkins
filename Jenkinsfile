pipeline {
    agent any

    environment {
        target = 'dev'
        serviceName = ''
        workspace = ''
        port = 5003
    }

    stages {
        stage('Setup environment') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        target = 'prod'
                        port = 80
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

                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        bat 'rd build /s /q'
                    }
                    
                    bat 'del .env'
                    bat "ren .env.${target} .env"
                }
            }
        }

        stage('Install dependencies') {
            steps {
                ws(workspace) {
                    bat 'npm install'
                }
            }
        }

        stage('Build') {
            steps {
                ws(workspace) {
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
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                        bat 'rd dist /s /q'
                    }

                    bat 'ren build dist'
                    bat 'rd src /s /q'
                    bat 'rd public /s /q'
                }
            }
        }

        stage('Start service') {
            steps {
                ws(workspace) {
                    bat "pm2 start server/index.js --name ${serviceName} -- ${port}"
                    bat 'pm2 save'
                }
            }
        }
    }   
}
