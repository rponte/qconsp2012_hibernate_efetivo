#!/bin/bash

###############################
# MySQL Server dependencies #
###############################

# The latest source code can be found at https://gist.github.com/rponte/7561856

set -e # Exit script immediately on first error.
#set -x # Print commands and their arguments as they are executed.

# Check if MySQL environment is already installed
RUN_ONCE_FLAG=~/.mysql_env_build_time
MYSQL_PASSWORD="root"

if [ -e "$RUN_ONCE_FLAG" ]; then
  echo "MySQL Server environment is already installed."
  exit 0
fi

# Update Ubuntu package index
sudo apt-get update -y

# Installs MySQL 5.5
echo "mysql-server-5.5 mysql-server/root_password password $MYSQL_PASSWORD" | sudo debconf-set-selections
echo "mysql-server-5.5 mysql-server/root_password_again password $MYSQL_PASSWORD" | sudo debconf-set-selections
sudo apt-get install -y mysql-server-5.5 mysql-client

# Configures MySQL
sudo sed -i 's/127.0.0.1/0.0.0.0/g' /etc/mysql/my.cnf
sudo sed -i '/\[mysqld\]/a\lower_case_table_names=1' /etc/mysql/my.cnf
echo "MySQL Password set to '${MYSQL_PASSWORD}'. Remember to delete ~/.mysql.passwd" | tee ~/.mysql.passwd; 
mysql -uroot -p$MYSQL_PASSWORD -e "GRANT ALL ON *.* TO root@'%' IDENTIFIED BY '$MYSQL_PASSWORD'; FLUSH PRIVILEGES;";
mysql -uroot -p$MYSQL_PASSWORD -e "CREATE SCHEMA qconsp DEFAULT CHARACTER SET utf8";
sudo service mysql restart

# Installs basic dependencies
sudo apt-get install -y unzip git curl

# Configures prompt color
sed -i 's/#force_color_prompt=yes/force_color_prompt=yes/g' ~/.bashrc
echo 'source ~/.bashrc' >> ~/.bash_profile
source ~/.bash_profile

# Cleaning unneeded packages
sudo apt-get autoremove -y
sudo apt-get clean

# sets "run once" flag
date > $RUN_ONCE_FLAG