### SRE WIKI
https://wiki.ciklum.net/display/SR/Autotests

### Prerequisites

- make sure `docker` and `docker-compose` installed

### RUN TESTS LOCALLY
#### in Ubuntu
- run `bin/start.sh`

### DEBUG TESTS LOCALLY
#### in Ubuntu

- install vncviewer `sudo apt-get install vnc-java`
- run `bin/start.sh`
- get ip address of chrome-debug container `docker inspect docker_selenium_1 | grep IPAddress`
- run `vncviewer <ip_adddress>` 
- in VNC enter password `secret`
- see tests results in VNC window

### RUN TESTS ON JENKINS

- build autotest environment by building Jenkins job [sre_autotest](http://ci.pp.ciklum.com/job/SRE_Folder/job/sre_autotest/)
- run tests by building Jenkins job [sre_autotest_run](http://ci.pp.ciklum.com/job/SRE_Folder/job/sre_autotest_run/)
