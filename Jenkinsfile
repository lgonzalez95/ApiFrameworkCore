pipeline{
    agent any
    stages{
        stage("clean"){
            steps{
                bat "mvn clean"
            }
        }

        stage("install"){
            steps{
                bat "mvn install"
            }
        }

        stage("test"){
            steps{
                bat "mvn test"
            }
        }
    }
}