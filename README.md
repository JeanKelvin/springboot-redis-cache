**1. Baixar imagem docker Redis**

sudo docker pull redis:3.2.5-alpine

**2. Inicie um container a partir da imagem baixada**

sudo docker run -d -p 6379:6379 -i -t redis:3.2.5-alpine
