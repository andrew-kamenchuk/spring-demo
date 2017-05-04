#! /usr/bin/env bash
export DEBIAN_FRONTEND=noninteractive

dpkg --add-architecture i386
apt-get update && apt-get -y upgrade
apt-get --no-install-recommends install virtualbox-guest-utils
apt-get install libc6-i386

debconf-set-selections <<< "mysql-server mysql-server/root_password password root"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password root"

apt-get install -y mysql-server

mysql -uroot -proot -e"CREATE DATABASE local_products" 2>/dev/null
mysql -uroot -proot -e'CREATE USER "local"@"localhost" IDENTIFIED BY "local"' 2>/dev/null
mysql -uroot -proot -e'GRANT ALL PRIVILEGES ON local_products.* TO "local"@"localhost"' 2>/dev/null

mysql -uroot -proot local_products < /vagrant/data/schema.sql
mysql -uroot -proot local_products < /vagrant/data/products.sql

echo "Installing jre..."

wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" \
  "http://download.oracle.com/otn-pub/java/jdk/8u112-b15/jdk-8u112-linux-x64.tar.gz"


mkdir -p /usr/lib/jvm


tar -C /usr/lib/jvm/ -xzf jdk-8u112-linux-x64.tar.gz


rm jdk-8u112-linux-x64.tar.gz


update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jdk1.8.0_112/bin/java" 100


echo "Done!"

echo 'export JAVA_HOME="/usr/lib/jvm/jdk1.8.0_112"' | tee -a "/home/vagrant/.profile"
export JAVA_HOME="/usr/lib/jvm/jdk1.8.0_112"

echo "Installing solr..."
wget http://www-us.apache.org/dist/lucene/solr/6.4.1/solr-6.4.1.tgz
tar -xzf solr-6.4.1.tgz solr-6.4.1/bin/install_solr_service.sh --strip-components=2
./install_solr_service.sh solr-6.4.1.tgz
rm solr-6.4.1.tgz
service solr status

usermod -aG solr vagrant

wget "https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.41.tar.gz" -O mysql-connector-java.tar.gz
tar -xvzf mysql-connector-java.tar.gz

mkdir /opt/solr/contrib/dataimporthandler/lib
cp mysql-connector-java-5.1.41/mysql-connector-java-5.1.41-bin.jar /opt/solr/contrib/dataimporthandler/lib

rm -rf mysql-connector-java*

sudo -i -H -u solr bash -c "/opt/solr/bin/solr create -c 'products' -d '/vagrant/data/solr/conf'"

curl "http://localhost:8983/solr/products/dataimport?command=full-import"

wget http://apache.volia.net/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
mkdir maven
tar -xvzf apache-maven-3.3.9-bin.tar.gz -C maven --strip-components=1
./maven/bin/mvn -f /vagrant/pom.xml install

wget http://www-eu.apache.org/dist/tomcat/tomcat-7/v7.0.77/bin/apache-tomcat-7.0.77.tar.gz
mkdir tomcat
tar -xvzf apache-tomcat-7.0.75.tar.gz -C tomcat --strip-components=1
