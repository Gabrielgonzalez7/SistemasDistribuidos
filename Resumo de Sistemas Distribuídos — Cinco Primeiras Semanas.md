# Resumo de Sistemas Distribuídos

Resumo aprofundado das cinco primeiras semanas de aula, abordando:

- Fundamentos de Sistemas Distribuídos
- Comunicação
- Threads
- Memória compartilhada
- Sincronização
- Relógios físicos e lógicos
- Exclusão mútua
- Eleição
- Thread Pool

---

## 1. O que é um Sistema Distribuído?

### Conceito

Um Sistema Distribuído é um conjunto de computadores ou processos independentes que trabalham em conjunto através de uma rede.

Cada computador possui seus próprios recursos, como:

- CPU
- Memória
- Armazenamento

Os participantes cooperam para realizar tarefas ou oferecer serviços.

### Para que serve?

Pode ser utilizado para:

- Aumentar o desempenho;
- Compartilhar recursos;
- Processar grandes volumes de dados;
- Distribuir tarefas;
- Aumentar a disponibilidade;
- Evitar que todo o processamento fique concentrado em uma única máquina.

### Exemplo

Em uma aplicação:

- Um computador pode cuidar do banco de dados;
- Outro pode executar o servidor web;
- Outro pode realizar o processamento;
- Outro pode armazenar arquivos.

Para o usuário, tudo pode funcionar como um único sistema.

---

## 2. Processamento Concomitante e Paralelo

### Concomitância

Várias tarefas podem progredir durante o mesmo período, com alternância de execução quando necessário.

Isso não significa obrigatoriamente que todas estejam executando exatamente no mesmo instante.

### Paralelismo

Diferentes tarefas são executadas simultaneamente, normalmente utilizando diferentes núcleos ou processadores.

### Para memorizar

> **Concomitância = várias tarefas progredindo.**  
> **Paralelismo = várias tarefas executando simultaneamente.**

---

## 3. Cluster e Grid

### Cluster

Conjunto de computadores que trabalham de maneira bastante integrada, geralmente em um ambiente controlado, buscando:

- Desempenho;
- Disponibilidade;
- Paralelismo.

### Grid

Conjunto de recursos computacionais distribuídos, que podem estar geograficamente separados e compartilhar capacidade de processamento.

### Diferença simples

- **Cluster:** maior integração entre as máquinas.
- **Grid:** reúne recursos distribuídos que podem estar em locais diferentes.

---

## 4. Comunicação em Sistemas Distribuídos

Computadores e processos precisam trocar informações pela rede para cooperar.

Os principais conceitos estudados foram:

- IP;
- Máscara;
- Porta;
- Socket;
- TCP;
- UDP.

### IP

Identifica um equipamento ou interface na rede.

**Exemplo:**

```text
192.168.1.10
```

### Porta

Identifica o serviço ou processo que deve receber a comunicação em determinado computador.

**Exemplo:**

```text
192.168.1.10:8080
```

### Socket

É uma interface utilizada por uma aplicação para realizar comunicação pela rede.

Uma forma simples de visualizar:

```text
IP + Porta → Socket → Comunicação
```

---

## 5. Modelo TCP/IP

Na abordagem da disciplina, o modelo é apresentado como:

```text
Aplicação
    ↓
Transporte
    ↓
Rede/Internet
    ↓
Interface de Rede
```

### Aplicação

Onde ficam os serviços e protocolos utilizados diretamente pelas aplicações, como:

- HTTP;
- DNS.

### Transporte

Responsável pela comunicação entre processos.

Os principais protocolos estudados são:

- TCP;
- UDP.

### Rede/Internet

Responsável pelo encaminhamento dos pacotes entre redes, principalmente através do IP.

### Interface de Rede

Relacionada à comunicação através da tecnologia de rede utilizada.

---

## 6. TCP e UDP

### TCP

**Transmission Control Protocol**

É orientado à conexão e oferece mecanismos de comunicação confiável, como:

- Controle da transmissão;
- Confirmação;
- Retransmissão quando necessário.

### UDP

**User Datagram Protocol**

É mais simples, possui menor sobrecarga e não garante:

- Entrega dos dados;
- Ordenação dos dados.

### Para memorizar

> **TCP = confiabilidade e controle.**  
> **UDP = simplicidade e menor sobrecarga.**

---

## 7. Unicast, Multicast e Broadcast

### Unicast

Um emissor envia para um único receptor.

```text
A → B
```

### Multicast

Um emissor envia para vários participantes de um grupo.

```text
        → B
A  →    → C
        → D
```

### Broadcast

Um emissor envia para todos os dispositivos de determinado domínio de broadcast.

### Para memorizar

| Tipo | Comunicação |
|---|---|
| Unicast | Um para um |
| Multicast | Um para vários de um grupo |
| Broadcast | Um para todos |

---

## 8. Comunicação Bloqueante

### Conceito

Uma operação bloqueante faz o programa esperar até que a operação possa continuar.

Em uma leitura de rede, por exemplo, a aplicação pode ficar aguardando uma mensagem.

### Relação com Threads

Threads podem ser usadas para que uma tarefa fique bloqueada esperando comunicação enquanto outras tarefas continuam executando.

---

## 9. Arquiteturas

### Cliente-Servidor

Um servidor oferece serviços e os clientes fazem solicitações.

**Exemplo:**

```text
Cliente → Solicitação → Servidor
Cliente ← Resposta ← Servidor
```

Exemplo real: um navegador solicitando informações a um servidor web.

### Ponto-a-Ponto

Os participantes podem atuar como clientes e servidores, sem necessariamente existir um servidor central único.

---

## 10. O que é uma Thread?

### Conceito

Thread é uma unidade de execução dentro de um processo.

Um processo pode possuir várias threads, cada uma responsável por uma tarefa.

### Exemplo

```text
Processo
├── Thread 1 → Ler arquivo
├── Thread 2 → Processar dados
└── Thread 3 → Enviar resultados
```

### Processo x Thread

**Processo:** é uma instância de um programa em execução.

**Thread:** é uma unidade de execução dentro desse processo e pode compartilhar recursos com outras threads do mesmo processo.

---

## 11. Quando Usar e Quando Não Usar Threads

### Quando usar

Threads são adequadas para tarefas independentes ou que podem progredir de forma concomitante, como:

- Processamento;
- Mineração;
- Análise de dados;
- Leitura de arquivos;
- Servidores.

### Quando evitar

Podem ser desnecessárias:

- Em tarefas muito simples;
- Quando há forte dependência entre operações;
- Quando a seção crítica é difícil de controlar;
- Em determinadas tarefas de interação direta com o usuário.

### Ideia principal

> Criar e gerenciar threads tem custo. O uso deve trazer benefício real.

---

## 12. Threads sem Memória Compartilhada

### Conceito

Cada thread trabalha com seu próprio recurso ou conjunto de dados.

Isso reduz a disputa entre threads.

### Exemplo

Uma thread pode ler:

```text
numeros.txt
```

Enquanto outra pode ler:

```text
nomes.txt
```

Cada uma popula sua própria lista.

### Característica

Como não existe disputa pelo mesmo recurso, a necessidade de sincronização é muito menor.

---

## 13. Threads com Memória Compartilhada

### Conceito

Duas ou mais threads acessam o mesmo recurso, como:

- Uma lista;
- Uma variável;
- Um saldo.

### Problema

Se duas threads modificarem o mesmo dado simultaneamente, uma pode interferir na outra.

### Consequência

Surge a necessidade de controlar o acesso ao recurso compartilhado.

---

## 14. Seção Crítica

### Conceito

Seção crítica é o trecho do programa que acessa ou modifica um recurso compartilhado e que precisa de controle de concorrência.

### Exemplo

```java
saldo = saldo + 10;
```

Essa operação é uma seção crítica quando várias threads podem alterar o mesmo saldo.

### Objetivo

Impedir que execuções concorrentes produzam resultados inconsistentes.

---

## 15. Condição de Corrida

### Conceito

Condição de corrida ocorre quando o resultado depende da ordem em que threads concorrentes executam determinadas operações.

### Exemplo

Considere:

```text
saldo = 100
```

Duas threads precisam adicionar R$ 10.

Se ambas lerem o valor `100` antes de realizar a soma, ambas podem gravar:

```text
110
```

Porém, o resultado esperado seria:

```text
120
```

### Para memorizar

> **Condição de corrida = o resultado depende da ordem de execução concorrente.**

---

## 16. Sincronização

### Conceito

Sincronização controla o acesso concorrente aos recursos compartilhados.

### Fluxo

```text
Thread 1 entra na seção crítica
        ↓
Outras threads esperam
        ↓
Thread 1 termina
        ↓
Recurso é liberado
        ↓
Outra thread pode entrar
```

### Java

Em Java, `synchronized` é um mecanismo comum para proteger regiões de acesso compartilhado.

### Exemplo

```java
public synchronized void adicionarVenda(double valor) {
    saldo += valor;
}
```

---

## 17. Exclusão Mútua

### Conceito

Exclusão mútua garante que apenas um participante execute determinada seção crítica por vez.

### Analogia

É como um recurso com uma única chave:

```text
           CHAVE
             ↓
       ┌───────────┐
       │ Recurso   │
       └───────────┘
          ↑
     Uma thread
     por vez
```

Quem possui a chave utiliza o recurso; os demais esperam.

### Objetivo

Evitar:

- Condições de corrida;
- Corrupção de dados;
- Resultados incorretos.

---

## 18. Relógios Físicos

### Conceito

Cada computador possui seu próprio relógio físico.

Esses relógios podem apresentar pequenas diferenças.

### Clock Drift

É o desvio do relógio em relação a outros relógios ou a uma referência.

Por isso, computadores diferentes podem registrar horários diferentes para eventos próximos.

### Problema

Em sistemas distribuídos, o horário local de cada máquina não é suficiente para determinar com segurança a ordem dos eventos.

---

## 19. Relógios Lógicos e Lamport

### Objetivo

Relógios lógicos não tentam representar o horário real.

Eles ajudam a representar a **ordem dos eventos**.

### Relógio de Lamport

Cada processo mantém um contador lógico.

- Eventos locais incrementam o contador.
- Quando uma mensagem é recebida, o relógio é ajustado para um valor maior que o timestamp recebido.

### Regra

```text
Novo relógio = max(relógio local, timestamp recebido) + 1
```

### Exemplo

Se B está em `3` e recebe uma mensagem com timestamp `5`:

```text
max(3, 5) + 1 = 6
```

Portanto:

```text
B = 6
```

### Causalidade

Se A precede ou causa B, o relógio lógico deve refletir:

```text
timestamp(A) < timestamp(B)
```

---

## 20. Relógio Físico x Relógio Lógico

| Relógio | Representa |
|---|---|
| Físico | Tempo real |
| Lógico | Ordem dos eventos |

### Para memorizar

> **Físico → tempo**  
> **Lógico → ordem**

---

## 21. Exclusão Mútua Distribuída

### Conceito

Em um sistema distribuído, computadores diferentes podem precisar acessar o mesmo recurso, mas não possuem necessariamente uma memória compartilhada comum.

A coordenação precisa ocorrer por comunicação de rede.

### Exemplo

Vários computadores disputam uma impressora compartilhada.

O sistema precisa:

1. Decidir qual processo utiliza o recurso primeiro;
2. Fazer os demais processos esperarem;
3. Liberar o recurso quando o processo terminar.

### Relógios Lógicos

Timestamps podem ajudar a ordenar solicitações e estabelecer uma ordem consistente para acesso ao recurso.

---

## 22. Eleição

### Conceito

Eleição é o processo utilizado para escolher um coordenador entre os participantes do sistema.

### Por que existe?

Se o coordenador falhar, os processos precisam escolher outro para manter a coordenação.

### Exemplo

Considere:

```text
P1 ─┐
P2 ─┤
P3 ─┤
P4 ─┤ → Participantes
P5 ─┘
```

Se `P5` falhar, um processo ativo pode iniciar uma nova eleição.

---

## 23. Algoritmo Bully

### Ideia

Os processos possuem identificadores.

No algoritmo Bully, processos de maior identificador possuem prioridade para assumir a coordenação.

### Exemplo

Se `P5` falha e os processos ativos são:

```text
P1, P2, P3, P4
```

Então:

```text
P4
```

pode se tornar o novo coordenador.

### Fluxo

```text
Detectar falha
      ↓
Iniciar eleição
      ↓
Consultar processos de maior prioridade
      ↓
Escolher o processo de maior ID ativo
      ↓
Informar os demais processos
```

---

## 24. Thread Pool

### Conceito

Thread Pool é um conjunto limitado de threads reutilizáveis para executar tarefas.

### Problema Resolvido

Criar uma nova thread para cada tarefa pode consumir muitos recursos.

O pool:

- Limita a quantidade de threads;
- Reutiliza threads;
- Organiza as tarefas.

### Funcionamento

As tarefas são colocadas em uma fila.

As threads disponíveis retiram as tarefas, executam e depois ficam disponíveis novamente.

### Exemplo

Um pool com cinco threads:

```text
Thread 1 → Tarefa
Thread 2 → Tarefa
Thread 3 → Tarefa
Thread 4 → Tarefa
Thread 5 → Tarefa
```

Se chegarem 20 tarefas, as restantes aguardam na fila.

### Java

```java
ExecutorService pool = Executors.newFixedThreadPool(5);
```

### Vantagens

- Controle da quantidade de threads;
- Reutilização;
- Redução do custo de criação e destruição;
- Organização das tarefas.

---

## 25. Divisão e Conquista + Threads

### Ideia

Um problema grande é dividido em problemas menores, que podem ser processados independentemente e depois combinados.

### Exemplo

Uma lista com 1.000 números pode ser dividida em quatro partes:

```text
Lista com 1.000 números
          ↓
 ┌────────┼────────┬────────┐
 ↓        ↓        ↓        ↓
250      250      250      250
 ↓        ↓        ↓        ↓
T1       T2       T3       T4
```

Cada thread pode processar uma parte.

### Relação com Threads

Quando as partes são independentes, o uso de threads pode permitir:

- Processamento concomitante;
- Processamento paralelo.

---

## 26. Passagem de Parâmetros para Threads

### Java

Uma thread pode receber dados por meio de atributos inicializados em seu construtor.

Exemplo:

```java
class MinhaThread extends Thread {

    private int valor;

    public MinhaThread(int valor) {
        this.valor = valor;
    }

    @Override
    public void run() {
        System.out.println(valor);
    }
}
```

Criação:

```java
MinhaThread thread = new MinhaThread(10);
thread.start();
```

### Python

Argumentos podem ser passados na criação da thread, por exemplo, utilizando `args`.

### Ideia principal

> A thread precisa receber os dados necessários para realizar sua tarefa sem depender indevidamente de um recurso compartilhado.

---

# Resumo para Prova

| Conteúdo | Ideia principal |
|---|---|
| Sistema Distribuído | Computadores/processos trabalhando em conjunto |
| Concomitância | Tarefas progredindo no mesmo período |
| Paralelismo | Tarefas executando simultaneamente |
| Cluster | Máquinas fortemente integradas |
| Grid | Recursos distribuídos |
| IP | Identifica equipamento/interface |
| Porta | Identifica serviço/processo |
| Socket | Interface de comunicação |
| TCP | Confiável e orientado à conexão |
| UDP | Simples e menor sobrecarga |
| Unicast | Um para um |
| Multicast | Um para vários de um grupo |
| Broadcast | Um para todos |
| Thread | Unidade de execução |
| Memória compartilhada | Threads acessam o mesmo recurso |
| Seção crítica | Trecho que acessa recurso compartilhado |
| Condição de corrida | Resultado depende da ordem de execução |
| Sincronização | Controla acesso concorrente |
| Exclusão mútua | Apenas um acessa a seção crítica por vez |
| Relógio físico | Representa tempo |
| Relógio lógico | Representa ordem dos eventos |
| Lamport | Usa contador lógico para ordenar eventos |
| Eleição | Escolhe um coordenador |
| Bully | Maior ID ativo assume |
| Thread Pool | Conjunto limitado e reutilizável de threads |
| Divisão e Conquista | Divide problema em partes menores |
| Parâmetros | Permitem fornecer dados às threads |

---

# Principais pontos para memorizar

### Threads

```text
Thread = unidade de execução
```

### Memória compartilhada

```text
Mesmo recurso → possível condição de corrida
                       ↓
                 sincronização
                       ↓
                 exclusão mútua
```

### Relógios

```text
Físico  → tempo
Lógico  → ordem
```

### Comunicação

```text
TCP → confiabilidade
UDP → simplicidade
```

### Eleição

```text
Bully → maior ID ativo
```

### Thread Pool

```text
Tarefas → Fila → Threads disponíveis → Execução
```