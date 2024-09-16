# Highway
Projeto 4 para a cadeira "Laboratórios de Programação"
# Projeto
Neste exercício vamos considerar a simulação de uma via rápida com vários pórticos de
portagens, cada um deles com a sua fila de espera.
A simulação dos pórticos segue o modelo do Tipo de Dados Abstrato (TDA) Queue System,
que representa operações sobre uma sequência de filas de espera e cujos requisitos se
indicam a seguir:

- Cada fila de espera é identificada por um índice não negativo. A primeira fila na
sequência tem índice 0, a segunda fila tem índice 1, e assim sucessivamente;
- Cada fila de espera pode estar ativa ou inativa;
- Os comandos típicos das filas (enqueue, dequeue, front, isEmpty) apenas são
válidos se aplicados a uma fila ativa;
- Todas as filas inativas têm de estar vazias;
- É sempre possível criar mais filas, ficando cada nova fila no fim da sequência
existente;
- Uma fila nova inicia-se como inativa;
- Existe o conceito de fila atual;
- Queue System tem sempre uma e uma única fila atual;
- Quando uma operação de fila é invocada, essa operação é executada na fila atual;
- A fila atual tem necessariamente de estar ativa.
# Objetivo
Usar uma implementação do Tipo de Dados Abstrato Queue System para simular, durante
um determinado período de tempo, o comportamento de filas de espera em pórticos de
portagens.
# O que aprendi
- Conhecimento sobre Queue
- Utilização de ArrayQueue
