<div align="center">
<img src="https://user-images.githubusercontent.com/57498887/134352674-9837ff29-10ff-44f7-8c46-cb517767be49.png" width="175" height="250">

Instituto Federal de Educação, Ciência e Tecnológica da Paraíba
Tecnologia em Análise e Desenvolvimento de Sistemas
Processo de Desenvolvimento de Software
Professor: Ricardo de Sousa Job
</div>

# Atividade Colaborativa 02 – Session Beans

## EQuipe:
- <a href="https://github.com/camilacasimiro" alt="github">Camila Casimiro</a>
- <a href="https://github.com/FagneFarias" alt="github">Fagne Farias</a>
- <a href="https://github.com/JenniferSilva46" alt="github">Jennifer Silva</a>

<h1>Questões e Respostas</h1>

Teste de objetivos de aprendizagem

1. Qual a diferença entre image e container?

A imagem é um arquivo read-only e envolve o necessário para a execução de um pedaço da aplicação, contendo assim, instruções para criação de containers.
Já um container é uma instância da imagem que envolve os processos, empacotando aplicações para assim permitir executá-las de forma isolada do host padrão em ambientes diversificados.


2. Qual a diferença entre os comandos COPY, EXPOSE e ADD?

O comando COPY tem a função de gerar cópias de diretórios ou arquivos localmente, para assim gerar-los no interior da imagem.
O comando ADD tem a função de gerar cópias tanto locais quanto remotas para o interior da imagem.
O comando EXPOSE tem como função indicar as portas que o container poderá ouvir, sendo assim serve para ficar documentado no Dockerfile quais portas deverão ser liberadas ao criar o container quando iniciado poderá ser acessível através dessas portas.

3. Qual a diferença entre os comandos RUN, CMD e ENTRYPOINT?
 
O comando RUN permite a execução de comandos, especificando qual argumento será executado dentro da imagem do Docker.
O comando CMD é usado como maneira de definir argumentos padrões da imagem iniciada, ou seja, sendo executado na criação do contêiner através da definição de um comando.
Já o ENTRYPOINT indica o comando a ser executado após a inicialização do container, sendo executado sempre que o container for iniciado.

4. Qual a diferença entre as estratégias de shell e exec?

Ambas são estratégias que o docker utiliza ao executar um comando, O exec é utilizado no daemon do docker, facilitando execuções de comandos. Já o shell pode ser utilizado quando precisamos de debugar, ou realizar simples alterações em um container em execução.

5. Qual a diferença entre os comandos docker stop <container_id> e docker kill <container_id>?

Apesar de causarem efeitos parecidos, o docker stop para o processo de maneira menos súbita, enviando um sinal e recebendo um outro em seguida. Já o docker kill irá encerrar o processo de uma maneira repentina, mandando um sinal direto, fazendo com que pare imediatamente.
