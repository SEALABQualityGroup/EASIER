#!/bin/bash

wget -O config.ini $1
java -jar easier.jar @./config.ini
