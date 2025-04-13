list of docker commands 

list of docker 
docker pull imagename
docker images (show images)
docker ps -a (shows all containers )
docker run --name java2 -d OpenJDK/id (just create onpen and close )
docker run --name  anyname  -it -d imagename   (used to create and keep open container)

docker exec -it c0305d271392  commandname (exec in inrective container)
/exit   (to come out)

docker inspect containerid  (give details about container)
docker pull MySQL 
docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=root  -d  cmdname 
docker exec -it mysqldb bash  (go towards mysl ) ->  MySQL -p   -> password : root
docker stop containername 
docker restart containername
docker rm containername 
docker rumi repo:tag

docker pull  nginx 
docker run --name nginxserver -d  -p 8080:80 nginx     (-d means run in background)
localhost:8080 

docker pull httpd  (to pull the tomcst server)
docker run --name apacherserver -d  -p 8081:80 httpd
localhost:8081


docker login  
docker commit (
docker push 
docker copy
docker logs container name
docker volume 
docker logout


