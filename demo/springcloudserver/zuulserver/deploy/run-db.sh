#!/bin/bash

docker run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 huanwei/mysql-master:0.1