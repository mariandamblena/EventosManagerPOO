📅 Sistema de Gestión de Eventos
Este proyecto es una aplicación de escritorio desarrollada en Java con Swing que permite planificar y gestionar eventos de distinta escala, desde reuniones pequeñas hasta conferencias. Fue desarrollado como trabajo integrador de la materia Programación Orientada a Objetos.

🧩 Funcionalidades
✅ Funcionalidades Básicas
Registrar eventos: nombre, fecha y lugar.
Editar y eliminar eventos.
Visualizar eventos: lista y detalle individual.
Registrar asistentes por evento.
Interfaz gráfica intuitiva (Swing).
Persistencia con archivos de texto.
🧰 Funcionalidades Adicionales
Gestión de recursos (salas, proyectores, etc.) asignables a eventos.
Vista de calendario mensual con los eventos del mes.
Vista detallada del evento seleccionado con sus asistentes y recursos.
🏅 Puntos de Bonificación Implementados
Notificación en la aplicación al iniciar si hay eventos próximos (3 días).
Diseño modular con patrón MVC.
Uso de herencia y genéricos para repositorios persistentes.
🧱 Estructura del Proyecto
src/
├── modelo/
│   ├── Evento.java
│   ├── Asistente.java
│   ├── Recurso.java
│   ├── RepositorioBase.java
│   ├── RepositorioEventos.java
│   └── RepositorioRecursos.java
│
├── vista/
│   ├── VentanaPrincipal.java
│   ├── FormularioEvento.java
│   ├── FormularioAsistentes.java
│   ├── FormularioRecursos.java
│   ├── DetalleEvento.java
│   └── VistaCalendario.java
│
├── controlador/
│   ├── EventoControlador.java
│   ├── AsistenteControlador.java
│   ├── RecursoControlador.java
│   └── CalendarioControlador.java
│
└── Main.java
🧠 Fundamentos de POO Aplicados
Encapsulamiento: clases con atributos privados y acceso controlado.
Herencia: repositorios que extienden una clase base genérica.
Polimorfismo: uso de RepositorioBase<T> para múltiples entidades.
Abstracción: cada clase cumple una única responsabilidad.
Bajo acoplamiento y alta cohesión gracias a la aplicación del patrón MVC.
💾 Persistencia
Los datos se guardan en archivos .txt ubicados en la raíz del proyecto:

eventos.txt
recursos.txt
▶️ Ejecución
Compilar el proyecto en tu IDE (ej: IntelliJ o Eclipse).
Ejecutar la clase Main.java.
Se abrirá la ventana principal con la lista de eventos y todas las funciones disponibles.
👨‍🎓 Créditos
Trabajo práctico realizado por [Tu nombre] para la materia Programación Orientada a Objetos - UADE.
Año: 2025.
