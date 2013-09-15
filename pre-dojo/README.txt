Desenvolvedor: Maurício Carvalho
Projeto: pre-dojo
descrição do problema: https://github.com/DevAmil/pre-dojo

Resolução: Foi implementado todas as funcionalidades propostas na descrição do problema.

Validações Extras:

Está sendo validado a ordem de execução dos eventos no arquivo LOG. Exemplo:
23/04/2013 15:21:25 - Marck killed Roman using AK47
23/04/2013 15:20:23 - Ruy killed Roman using AK47 --> ERRO
23/04/2013 15:22:30 - Pablo killed Roman using AK47

Em caso de empate oo selecionar player que mais matou, será avaliado o número de mortes como critério de desempate.

o arquivo log.txt está no path --> /pre-dojo/src/br/com/dojo/resources/log.txt