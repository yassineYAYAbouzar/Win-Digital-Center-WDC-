pipeline{

	agent any
	environment {
		DOCKERHUB_CREDENTIALS=credentials('yassinebouzar')
	}

	stages {

		stage('clean') {

			steps {
				sh 'mvn package'
			}
		}

		stage('Build') {

			steps {
				sh 'docker build -t yassinebouzar/wdc:latest .'
			}
		}

		stage('Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh 'docker push yassinebouzar/wdc:latest'
			}
		}
		stage('run containers') {

			steps {
				sh 'docker-compose up -d'
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}