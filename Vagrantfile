# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  
  config.vm.box = "hashicorp/precise64"
  config.vm.provision :shell, :path => "configure_mysql-server.sh", :privileged => false

  config.vm.network :forwarded_port, guest: 3306, host: 3306
  
  config.vm.provider :virtualbox do |vb|
  	 #vb.gui = true
     vb.customize ["modifyvm", :id, "--memory", "256"]
  end
  
end
