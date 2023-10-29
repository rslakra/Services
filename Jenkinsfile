pipeline {
    agent any
    stages {
	    sh 'echo "Step: Building"'
        stage('Build HTML with Lint (Tidy)') {
            steps {
                sh 'tidy -q -e index.html'
            }
        }
        stage('Upload to AWS') {
            steps {
	        	sh 'echo "Step: AWS"'
            	withAWS(region:'us-east-2', credentials:'jenkins-build') {
	            	sh 'echo "Hello with AWS Credentials"'
	            	s3Upload(pathStyleAccessEnabled:true, payloadSigningEnabled:true, file:'build-file-name', bucket:'jenkins-pipeline')
            	}
            }
        }
    }
}
