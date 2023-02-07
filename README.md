# Plants API (Spring Boot)

Este proyecto es una aplicación CRUD que permite gestionar información sobre plantas. La aplicación fue desarrollada con el framework Spring Boot y es un proyecto inicial que permite trabajar con distintas respuestas HTTP para poder entender su funcionamiento.

La aplicación se ejecutará en la ruta `localhost:8080` y el endpoint inicial es `/plants`.

### Endpoints
- #### `/plants` [POST]
Este endpoint permite crear una nueva planta con los datos entregados en el body y asignar un ID unico.  
Tipo de request HTTP: `Create`

- #### `/plants` [GET]
Este endpoint permite listar todas las plantas que existen en la base de datos.  
Tipo de request HTTP: `Read`

- #### `/plants/{id}` [GET]
Este endpoint permite listar la planta con un ID especifico.  
Tipo de request HTTP: `Read`

- #### `/plants/{id}` [PUT]
Este endpoint permite actualizar la información para una planta con ID especificado. Incluye un body en JSON con los datos de la nueva planta para actualizar.  
Tipo de request HTTP: `Update`

- #### `/plants/{id}` [DELETE]
Este endpoint permite eliminar la planta con un ID especificado. Si no se encuentra dicho ID, retornará null.  
Tipo de request HTTP: `Delete`
