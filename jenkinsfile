pipeline {

  agent {
    label 'Slave4_Induccion'
  }


  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	disableConcurrentBuilds()
  }
	environment {
        PROJECT_PATH_BACK = './microservicio/'
	}

  tools {
    jdk 'JDK8_Centos'
	gradle 'Gradle5.0_Centos'
  }

  stages{
      stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout([
            $class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            gitTool: 'Default',
            submoduleCfg: [],
            userRemoteConfigs: [[
            credentialsId: 'Github_Bethos05',
            url:'https://github.com/Bethos05/comenSal'
            ]]
        ])
      }
    }

    stage('Clean') {
      steps {
		        dir("${PROJECT_PATH_BACK}")
            {
              sh 'chmod +x ./gradlew'
              sh './gradlew --b ./build.gradle clean'
            }

      }
    }


    stage('Build') {
      steps {
		        dir("${PROJECT_PATH_BACK}")
            {
                sh 'chmod +x ./gradlew'
                sh './gradlew --b ./build.gradle build -x test'
            }

      }
    }


	stage('Tests'){
		parallel {
			stage(''){
				steps {
						dir("${PROJECT_PATH_BACK}"){
						sh 'chmod +x ./gradlew'
						sh './gradlew --stacktrace test'
					}
				}
			}
		}
	}

	stage('Compile & Unit Tests') {
        steps{
            echo "------------>compile & Unit Tests<------------"
            sh 'chmod +x ./microservicio/gradlew'
            sh './microservicio/gradlew --b ./microservicio/build.gradle clean'
            sh './microservicio/gradlew --b ./microservicio/build.gradle test'
        }
    }


  stage('Static Code Analysis'){
		steps{
			echo '------------>Analisis de código estático<------------'
			withSonarQubeEnv('Sonar') {
                     sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=./microservicio/sonar-project.properties"
            }
		}
	}
  }
  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
	    mail (to: 'alberto.alvarez@ceiba.com.co',subject: "Success Pipeline:${currentBuild.fullDisplayName}",body: "Success build ${env.BUILD_URL}")
    }
    failure {
      echo 'This will run only if failed'
		  mail (to: 'alberto.alvarez@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}