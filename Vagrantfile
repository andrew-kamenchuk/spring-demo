VAGRANTFILE_API_VERSION="2"

app_name = "products"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
    config.vm.box = "ubuntu/trusty64"

    config.vm.network :forwarded_port, guest: 8080, host: 8080
    config.vm.network :forwarded_port, guest: 8983, host: 8983

    config.vm.provision :shell, path: "bootstrap.sh"
    config.vm.provision :shell, path: "startup.sh", run: "always"

    config.vm.provider :virtualbox do |vb|
        vb.name = "ubuntu-trusty64-#{app_name}"
        vb.cpus = 2
        vb.memory = 4096
    end

end
