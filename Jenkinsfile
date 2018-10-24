pipeline{
    agent any
    stages{
        stage('Checkout'){
            steps{
                checkout scm
            }
            
        }
        stage('checking the file system'){
            steps{
                sh 'pwd'
                sh 'ls -lart'
            }
            
        }



    }

}

