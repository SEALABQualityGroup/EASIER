# Instruction for Docker

## Build a Docker image

```bash
cd ..
docker build -t easier:<tag> -f easier-docker/Dockerfile
```
## Run a Docker image

```bash
cd ..
docker run --mount type=bind,source=/mnt/data/<tag>,target=/mnt/easier-output/ [conig file URL]
```
