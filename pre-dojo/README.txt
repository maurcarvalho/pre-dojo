Desenvolvedor: Maur�cio Carvalho
Projeto: pre-dojo
descri��o do problema: https://github.com/DevAmil/pre-dojo

Resolu��o: Foi implementado todas as funcionalidades propostas na descri��o do problema.

Valida��es Extras:

Est� sendo validado a ordem de execu��o dos eventos no arquivo LOG. Exemplo:
23/04/2013 15:21:25 - Marck killed Roman using AK47
23/04/2013 15:20:23 - Ruy killed Roman using AK47 --> ERRO
23/04/2013 15:22:30 - Pablo killed Roman using AK47

Em caso de empate oo selecionar player que mais matou, ser� avaliado o n�mero de mortes como crit�rio de desempate.

o arquivo log.txt est� no path --> /pre-dojo/src/br/com/dojo/resources/log.txt