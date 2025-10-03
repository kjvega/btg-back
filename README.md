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

   📌 Backend (Spring Boot) - Guía de Despliegue
Requisitos

Java 17 o superior instalado en el servidor.

Gradle configurado en el proyecto.

Instancia EC2 en AWS o servidor Linux disponible.

Pasos

Construir el proyecto

./gradlew clean build -x test


Esto genera el archivo JAR en:

build/libs/fondos-0.0.1-SNAPSHOT.jar


Copiar el JAR al servidor
Desde tu máquina local:

scp -i "tu-clave.pem" build/libs/fondos-0.0.1-SNAPSHOT.jar ubuntu@<IP_EC2>:/home/ubuntu/app.jar


Ejecutar la aplicación en el servidor

cd /home/ubuntu
java -jar app.jar --server.port=8081


Probar el backend
Accede desde el navegador o Postman:

http://<IP_EC2>:8081/funds/history/1
