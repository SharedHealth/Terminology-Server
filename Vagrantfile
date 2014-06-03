# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.define "server" do |server|
    server.vm.box = "centos"
    server.vm.box_url = "http://puppet-vagrant-boxes.puppetlabs.com/centos-65-x64-virtualbox-puppet.box"
    server.vm.network :private_network, ip: "192.168.33.17"
    server.vm.provider :virtualbox do |vb|
        vb.customize ["modifyvm", :id, "--memory", "2048", "--cpus", "2"]
    end
    # Install server package
    server.vm.provision :ansible do |ansible|
      ansible.verbose = "v"
      ansible.extra_vars = {ansible_ssh_user: 'vagrant', ansible_ssh_port: 2222, mysql_password: "password", mysql_username: "root", rpm: "../../build/distributions/bdshr-terminology-server-0.1.noarch.rpm"}
      ansible.playbook = "FreeSHR-Playbooks/terminologyserver/deploy.yml"
      ansible.inventory_path = "./hosts"
      ansible.limit = "all"
    end
    #Install the atom feed omod
    server.vm.provision :ansible do |ansible|
      ansible.verbose = "v"
      ansible.extra_vars = {ansible_ssh_user: 'vagrant', ansible_ssh_port: 2222, omod: "../../openmrs-atomfeed/openmrs-atomfeed-omod/target/openmrs-atomfeed-2.0-SNAPSHOT.omod"}
      ansible.playbook = "FreeSHR-Playbooks/terminologyserver/deploy-omod.yml"
      ansible.inventory_path = "./hosts"
      ansible.limit = "all"
    end
    #Install the server omod
    server.vm.provision :ansible do |ansible|
      ansible.verbose = "v"
      ansible.extra_vars = {ansible_ssh_user: 'vagrant', ansible_ssh_port: 2222, omod: "../../openmrs-module-freeshr_terminology_feed/omod/target/freeshr-terminology-feed-1.0-SNAPSHOT.omod"}
      ansible.playbook = "FreeSHR-Playbooks/terminologyserver/deploy-omod.yml"
      ansible.inventory_path = "./hosts"
      ansible.limit = "all"
    end
  end

  config.vm.define "client" do |client|
    client.vm.box = "centos"
    client.vm.box_url = "http://puppet-vagrant-boxes.puppetlabs.com/centos-65-x64-virtualbox-puppet.box"
    client.vm.network :private_network, ip: "192.168.33.18"
    client.vm.provider :virtualbox do |vb|
        vb.customize ["modifyvm", :id, "--memory", "2048", "--cpus", "2"]
    end
    client.vm.provision :ansible do |ansible|
      ansible.verbose = "vvv"
      ansible.extra_vars = {ansible_ssh_user: 'vagrant', ansible_ssh_port: 2222, omod: "../../openmrs-module-freeshr_terminology_feed/omod/target/freeshr-terminology-feed-1.0-SNAPSHOT.omod"}
      ansible.playbook = "FreeSHR-Playbooks/bahmni/site.yml"
      ansible.inventory_path = "./hosts"
      ansible.limit = "all"
    end
  end

end
