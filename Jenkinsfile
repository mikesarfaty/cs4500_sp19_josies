pipeline {
    agent any
        stages {
            stage('Testing') {
                steps {
                    sh '''
                        echo "Running junit jupiter tests"
                        mvn clean test
                        '''
                }
            }
            stage('Deploying') {
                steps {
                    sh '''
                        echo "Deploying to Heroku"
                        git push heroku master
                        '''
                }
            }
        }
}
