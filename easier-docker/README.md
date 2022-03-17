# Instruction for Docker

 - outputFolder: is specified within the easier config file. Default value is `/mnt/easier-output/`
 - tag: is the tag that specifies the easier version

## Build a Docker image

```bash
cd ..
docker build -t easier:<tag> -f easier-docker/Dockerfile
```
## Run a Docker image

```bash
cd ..
docker run -v /mnt/data/<tag>:$outputFolder [conig file URL]
```
