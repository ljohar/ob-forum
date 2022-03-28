**Endpoints:**

**http://127.0.0.1:3333/foro/temas**

GET → precarga preguntas

Además solo se cargan los temas de los cursos a los que tengas acceso

**Filtros**: cursoId, moduloId

**Sort**: ordena primero por los temas fijados

GET by ID → precarga preguntas y **totalRespuestas**

POST, UPDATE Y DELETE → Solo Admin

**Nota:** SOLO el admin puede decir si un tema está fijado


**http://127.0.0.1:3333/foro/preguntas**

GET → precarga user *(id, username, avatar)* y totalRespuestas

**Filtros**: temaId

**Sort**: ordena primero por las preguntas fijadas

GET by ID → respuestas y de respuestas: user *(id, username, avatar)*, totalVotosPositivos y totalVotosNegativos

POST → Cualquier user + admin

UPDATE Y DELETE → Solo del user que creó la pregunta + Admin

**Notas sobre creación/modificación**:

Si se hace POST o PUT con un user que **no es admin**, no hace falta pasar userId. Crea la pregunta con el userId del user.

**SOLO** el admin puede decir si una pregunta está **fijada**

**http://127.0.0.1:3333/foro/respuestas**

GET → precarga user *(id, username, avatar),* totalVotosPositivos y totalVotosNegativos

**Filtros**: preguntaId

**Sort**: ordena primero por las respuestas fijadas

**Para obtener las respuestas con más votos positivos**

[http://127.0.0.1:3333/foro/respuestas?sort=totalVotosPositivos&order=desc](http://127.0.0.1:3333/foro/respuestas?sort=totalVotosPositivos&order=desc)

**Para obtener las primeras / últimas respuestas**

[http://127.0.0.1:3333/foro/respuestas?sort=updated_at&order=asc](http://127.0.0.1:3333/foro/respuestas?sort=updated_at&order=asc) o desc

GET by ID → precarga user *(id, username, avatar),* totalVotosPositivos y totalVotosNegativos

POST → Cualquier user + Admin.

UPDATE Y DELETE → Solo del user que creó la respuesta + Admin

**Notas sobre creación/modificación**:

Si se hace POST o PUT con un user que **no es admin**, no hace falta pasar userId. Crea la respuesta con el userId del user.

**SOLO** el admin puede decir si una respuesta está **fijada**

**http://127.0.0.1:3333/foro/votos**

GET → sin precargas

**Filtros**: respuestaId, preguntaId

GET by ID → sin precargas

POST → Cualquier user + Admin → Hay que indicar: *preguntaId* o *respuestaId*

UPDATE Y DELETE → Solo del user que creó el voto + Admin

**Notas sobre creación/modificación**:

Si se hace POST o PUT con un user que **no es admin**, no hace falta pasar userId. Crea el voto con el userId del user.

[Especificación API](https://www.notion.so/Especificaci-n-API-016876730efc47f9a8866613643c95b9)