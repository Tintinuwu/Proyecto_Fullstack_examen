# Proyecto_Fullstack_examen
🎬 Sistema de Reservas de Cine — Arquitectura de Microservicios (V2.0)

**Asignatura:** Desarrollo FullStack I (DSY1103)  
**Institución:** Duoc UC  
**Estado:** Arquitectura orientada a servicios con despliegue en contenedores.

---

## 🏗️ Arquitectura de la Solución

Esta versión del sistema implementa un ecosistema completo de microservicios, utilizando un **API Gateway** como puerta de entrada única y un servidor **Eureka** para el descubrimiento dinámico de servicios.

```mermaid
graph TD
    Client[Cliente / Postman] --> Gateway[API Gateway - Puerto 8080]
    Gateway --> Eureka[Eureka Server - Puerto 8761]
    Gateway --> MS1[MS-Usuarios - Puerto 8082]
    Gateway --> MS2[MS-Peliculas - Puerto 8083]
    Gateway --> MS3[MS-Reservas - Puerto 8085]
    Gateway --> MS4[MS-Pagos - Puerto 8085]

    MS1 -.-> Eureka
    MS2 -.-> Eureka
    MS3 -.-> Eureka

Desarrollado para la Evaluación Semestral de Dessarollo Fullstack - Duoc UC 2026
