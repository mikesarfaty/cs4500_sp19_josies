pipeline {
    agent any
        stages {
            stage('Testing') {
                steps {
                    sh '''
                        echo "Running junit jupiter tests"
                        git checkout origin master
                        mvn clean test
                        '''
                }
            }
            stage('Deploying') {
                steps {
                    sh '''
                        echo "Deploying to Heroku"
                        ls
                        git remove -v
                        git status
                        git push heroku master
                        '''
                }
            }
        }
}
