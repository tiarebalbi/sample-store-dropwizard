#!/bin/bash

sudo apt-get remove maven2 -y

sudo add-apt-repository ppa:webupd8team/java -y 
sudo apt-get update
sudo apt-get install oracle-java8-installer maven -y