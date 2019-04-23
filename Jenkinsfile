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
        }
}
