pipeline {
    agent any
    stages {
        stage('test') {
            agent {
               docker {
                   image 'node:9'
               }
            }

            steps {
                sh 'npm i newman -g'
                sh 'newman --verison'
            }
        }
    }
}