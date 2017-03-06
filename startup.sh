#! /usr/bin/env bash

rm -rf tomcat/webapps/*
cp /vagrant/target/demo.war tomcat/webapps/ROOT.war
bash tomcat/bin/startup.sh
