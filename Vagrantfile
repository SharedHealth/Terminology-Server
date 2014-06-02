# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box = "centos"
  config.vm.box_url = "http://puppet-vagrant-boxes.puppetlabs.com/centos-65-x64-virtualbox-puppet.box"

  config.vm.network :private_network, ip: "192.168.33.17"

  config.vm.provider :virtualbox do |vb|
      vb.customize ["modifyvm", :id, "--memory", "2048", "--cpus", "2"]
  end

  config.vm.provision :ansible do |ansible|
    ansible.verbose = "v"
    ansible.extra_vars = {mysql_password: "password", mysql_username: "root", rpm: "../../build/distributions/bdshr-terminology-server-0.1.noarch.rpm"}
    ansible.playbook = "playbooks/terminologyserver/deploy.yml"
    ansible.inventory_path = "./hosts"
    ansible.limit = "all"
  end

end
