# BTG Fondos - Backend

Este es el backend del proyecto **BTG Fondos**, desarrollado con **Spring Boot** y **MongoDB** siguiendo una arquitectura hexagonal (puertos y adaptadores).

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3**
- **MongoDB (Atlas)**
- **Gradle** como gestor de dependencias

## 📂 Estructura general
El proyecto está organizado bajo el enfoque de **arquitectura hexagonal**:
- `domain`: Entidades y modelos de negocio.
- `application`: Casos de uso y servicios de aplicación.
- `infrastructure`: Adaptadores de persistencia, controladores REST y configuración.
- `config`: Configuración general (ej. CORS, seguridad, etc.).

## 🔧 Configuración
Las credenciales de conexión a MongoDB se gestionan en el archivo `application.properties` o `application.yml`.  
Es necesario configurar:
- URL de conexión a MongoDB Atlas
- Base de datos y colecciones

## 📌 Endpoints principales
- `POST /funds/subscribe` → Suscribir un cliente a un fondo
- `POST /funds/cancel` → Cancelar suscripción de un fondo
- `GET /funds/history/{clientId}` → Historial de transacciones de un cliente
- `GET /clients` → Listar todos los clientes

## ▶️ Ejecución
1. Clonar el repositorio
   ```bash
   git clone https://github.com/tu-usuario/btg-fondos-back.git

AWSTemplateFormatVersion: '2010-09-09'
Description: Infraestructura para el despliegue de la aplicación BTG Fondos (Back-End)

Parameters:
  KeyName:
    Description: Nombre del Key Pair para acceder vía SSH a la instancia EC2
    Type: AWS::EC2::KeyPair::KeyName
    Default: btg-fondos-key   # Key Pair creado en EC2

Resources:
  ApplicationEC2Instance:
    Type: AWS::EC2::Instance
    Properties:
      InstanceType: t3.micro
      KeyName: !Ref KeyName
      ImageId: ami-0c55b159cbfafe1f0   # Amazon Linux 2 en us-east-2
      SecurityGroupIds:
        - !Ref ApplicationSecurityGroup
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash
          yum update -y
          yum install -y java-17-amazon-corretto
          aws s3 cp s3://btg-fondos-artifacts/fondos-0.0.1-SNAPSHOT.jar /home/ec2-user/fondos.jar
          java -jar /home/ec2-user/fondos.jar --server.port=8081 > /home/ec2-user/app.log 2>&1 &

  ApplicationSecurityGroup:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupDescription: Security group for BTG Fondos back-end
      SecurityGroupIngress:
        - IpProtocol: tcp
          FromPort: 22
          ToPort: 22
          CidrIp: 0.0.0.0/0
        - IpProtocol: tcp
          FromPort: 8081
          ToPort: 8081
          CidrIp: 0.0.0.0/0

Outputs:
  ApplicationURL:
    Description: URL de la aplicación desplegada
    Value: !Sub "http://${ApplicationEC2Instance.PublicDnsName}:8081"

