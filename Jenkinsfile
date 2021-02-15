pipeline {
    agent any

    environment {
        target = 'dev'
        serviceName = ''
        workspace = ''
        apiPort = 5001
        reactPort = 5003
    }

    stages {
        stage('Setup environment') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master') {
                        target = 'prod'
                        apiPort = 80
                        reactPort = 80
                    }

                    serviceName = "sippi-${target}-api"
                    workspace = "C:/sippi/${target}/api"
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

                    script {
                        properties = readProperties file: 'src/main/resources/application.properties'
                        properties['spring.datasource.url'] = 'jdbc:postgresql://localhost:5432/api-teste'
                        properties['server.port'] = ${apiPort}
                        properties['cors.allowed.origins'] = "http://sippi.polodeinovacao.ifce.edu.br:${reactPort}"

                        writeProperties file: 'src/main/resources/application.properties' data: properties
                    }
                }
            }
        }

        stage('Build') {
            steps {
                ws(workspace) {
                    bat './gradlew clean build'
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
                }
            }
        }

        stage('Start service') {
            steps {
                ws(workspace) {
                    bat "pm2 start api.bat --name ${serviceName}"
                    bat 'pm2 save'
                }
            }
        }
    }   
}
