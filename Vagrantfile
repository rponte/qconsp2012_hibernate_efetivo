# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  
  config.vm.box = "precise64-vanilla"
  config.vm.box_url = "http://nitron-vagrant.s3-website-us-east-1.amazonaws.com/vagrant_ubuntu_12.04.3_amd64_virtualbox.box"
  config.vm.provision :shell, :path => "configure_mysql-server.sh", :privileged => false

  config.vm.network :forwarded_port, guest: 3306, host: 3306
  
  config.vm.provider :virtualbox do |vb|
  	 #vb.gui = true
     vb.customize ["modifyvm", :id, "--memory", "256"]
  end
  
end
