pipeline {
    agent any
        stages {
            stage('Testing') {
                steps {
                    sh '''
                        echo cleaning workspace
                        git clean -d -f
                        git checkout origin/master
                        echo "Running junit jupiter tests"
                        mvn clean test
                        '''
                }
            }
            stage('Deploying') {
                steps {
                    sh '''
                        echo "Deploying to Heroku"
                        ls
                        git remote -v
                        git status
                        git push heroku master
                        '''
                }
            }
        }
}
