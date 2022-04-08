# Buscaminas
Buscaminas en Java que guarda usuarios y puntuaciones en una base de datos SQLite.

## Instalación

Descargalo, abrelo con un IDE compatible con Gradle y a jugar!

## Características

- Inicia sesión en la aplicación para guardar tu puntuación en un usuario.
- Juega al buscaminas.
- Revisa tus 10 mejores puntuaciones o las 10 mejores puntuaciones globales.


## FAQ

#### Como funciona el nombre de usuario?

El nombre de usuario consta de dos partes, el nombre que escoges y el número de cuenta asignada al crearla (comprueba el nombre de usuario con su número antes de cerrar sesión, si no, no tendrás forma de saber el número).

El nombre de usuario final quedaría así:

```bash
 Usuario#0
```
#### Como se guardan las puntuaciones?

Las puntuaciones se guardan automáticamente al acabar partida (si se sale de la pestaña sin acabar la partida no se guardara puntuación) si has iniciado sesión con un usuario.

#### Como se juega al buscaminas?

Como siempre, click izquierdo para destapar una casilla y click derecho para marcar una casilla como posible mina y desactivarla. 

