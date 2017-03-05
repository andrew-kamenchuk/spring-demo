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
  "http://download.oracle.com/otn-pub/java/jdk/8u112-b15/jre-8u112-linux-x64.tar.gz"


mkdir -p /usr/lib/jvm


tar -C /usr/lib/jvm/ -xzf jre-8u112-linux-x64.tar.gz


rm jre-8u112-linux-x64.tar.gz


update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jre1.8.0_112/bin/java" 100


echo "Done!"

echo 'export JAVA_HOME="/usr/lib/jvm/jre1.8.0_112"' | tee -a "/home/vagrant/.profile"

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
