#!/bin/bash

rm -rf apidoc

apidoc -i ../src/ -o apidoc/ -f ".*\\Resource.java$" --encoding utf-8

scp -r apidoc/* root@192.168.3.108:/usr/share/nginx/html/apidoc/unmanned