#!/bin/bash

wget -O config.ini $1
java -Xmx12g -jar easier.jar @./config.ini
