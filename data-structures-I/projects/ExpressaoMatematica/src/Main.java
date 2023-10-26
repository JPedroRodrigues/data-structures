/* Nome: João Pedro Rodrigues Vieira - TIA: 32281730
* Turma: 03G
* */

import java.util.Scanner;

public class Main {
    /* Função que avalia a consistência de uma expressão matemática quanto ao uso de parênteses e outros símbolos.
    * Retorna true se os parênteses foram utilizados corretamente
    * Retorna falso, caso contrário*/
    public static boolean match(String exp) {
        PilhaGenerica<Character> stack = new PilhaGenerica<>(exp.length());

        for (int i = 0; i < exp.length(); i++){
            char c = exp.charAt(i);
            if (c == '(') {
                stack.push(c);
            }
            if (!stack.isEmpty()) {
                if (stack.topo() == '(' && c == ')') stack.pop();
            }
            else if (c == ')') return false;
        }
        return stack.isEmpty();
    }

    /* Função que verifica se há duas variáveis em sequência na expressão
    * Retorna true se sim
    * Retorna falso, caso contrário*/
    public static boolean singleVariable(String exp) {
        for (int i = 0; i < exp.length() - 1; i++) {
            if (Character.isAlphabetic(exp.charAt(i)) && Character.isAlphabetic(exp.charAt(i + 1))) return false;
        }
        return true;
    }

    /* Função que avalia se um determinado caractere é válido dentro deste sistema
    * Retorna true se o operador for válido no programa
    * Retorna falso, caso contrário*/
    public static boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '/' || op == '*' || op == '^' || op == '–';
    }

    /* Função que verifica se os operadores corretos foram usados, bem como se foram ou não digitados operadores em sequência
    * Retorna falso se forem utilizados dois operadores em sequência ou se for utilizado um fora dos permitidos pelo programa
    * Retorna true, caso contrário
    * */
    public static boolean correctOperators(String exp) {
        // Verificação de operadores em sequência
        for (int i = 0; i < exp.length() - 1; i++) {
            if (isOperator(exp.charAt(i)) && isOperator(exp.charAt(i + 1))) return false;
        }

        // Verificação do uso de outros operadores proibidos
        exp = exp.replaceAll("\\p{IsAlphabetic}", "");

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (!isOperator(c) && c != '(' && c != ')') return false;
        }
        return true;
    }

    /* Função que verifica se um operador tem prioridade sobre outro
    * Retorna 1 se o primeiro operador tem prioridade sobre o segundo
    * Retorna 0 se os operadores têm a mesma prioridade
    * Retorna -1 se o primeiro operador não tem prioridade sobre o segundo
    * */
    public static int priorityOperator(char op1, char op2) {
        if (op1 == '+' || op1 == '-') {
            if (op2 == '-' || op2 == '+') return 0;
            else if (op2 == '*' || op2 == '/' || op2 == '^') return -1;
            else return 1;

        } else  if (op1 == '*' || op1 == '/') {
            if (op2 == '+' || op2 == '-') return 1;
            else if (op2 == '*' || op2 == '/') return 0;
            else if (op2 == '^') return -1;
            else return 1;

        } else {
            if (op2 == '^') return 0;
            else return 1;
        }
    }

    /* Função que realiza uma operação matemática com os operandos "a" e "b" conforme a operação definida em "c"
    * Retorna o resultado da operação*/
    public static float operation(float a, float b, char c) {
        if (c == '+') return b + a;
        else if (c == '-' || c == '–') return b - a;
        else if (c == '/') return b / a;
        else if (c == '*') return b * a;
        else return (float) Math.pow(b, a);
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String expression = null;

        FilaGenerica <Character> output = new FilaGenerica<>();
        PilhaGenerica <Character> stack = new PilhaGenerica<>();

        FilaGenerica <Float> values = new FilaGenerica<>();
        PilhaGenerica <Float> results = new PilhaGenerica<>();

        char[] outputArray;
        float[] valuesArray;

        while(true) {
            // Apresentação do menu de opções
            System.out.println("Menu de opções\n");
            System.out.println("1. Entrada da expressão aritmética na notação infixa.");
            System.out.println("2. Entrada dos valores numéricos associados às variáveis.");
            System.out.println("3. Conversão da expressão, da notação infixa para a notação posfixa, e exibição da "+
                            "expressão convertida para posfixa.");
            System.out.println("4. Avaliação da expressão (apresentação do resultado do cálculo, mostrando a expressão "+
                    "e os valores das variáveis).");
            System.out.println("5. Encerramento do programa.");

            // Escolha do usuário
            System.out.print("\nDigite o número da opção (1/2/3/4/5): ");

            String option = s.nextLine();
            int opt = Integer.parseInt(option);

            // Verificação adicional das opções
            if (opt < 1 || opt > 5) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            // Encerramento do programa
            if (opt == 5) {
                s.close();
                return;
            }
            // Leitura da expressão
            else if (opt == 1) {
                System.out.print("Digite a expressão: ");
                expression = s.nextLine().trim().replace(" ", "").toUpperCase();

                while (!match(expression) || !correctOperators(expression) || !singleVariable(expression)) {
                    System.out.print("Expressão inválida. Por favor, insira novamente: ");
                    expression = s.nextLine().trim().replace(" ", "").toUpperCase();
                }
                // "Limpamos" as filas para não concatenar as expressões
                values.clear();
                output.clear();
            }
            // Entrada dos valores da expressão
            else if (opt == 2) {
                if (expression == null) {
                    System.out.println("Erro: crie uma expressão para informar os valores.");
                    continue;
                }

                // Limpeza da fila de valores
                values.clear();

                // Entrada dos valores de cada variável
                for (int i = 0; i < expression.length(); i++) {
                    if (Character.isAlphabetic(expression.charAt(i))) {
                        System.out.printf("Valor para a variável \"%c\": ", expression.charAt(i));
                        values.enqueue(Float.parseFloat(s.nextLine()));
                    }
                }
            }
            // Conversão da notação infixa para a posfixa
            else if (opt == 3) {
                // "Limpamos" a pilha e fila para não concatenar as expressões
                output.clear();
                stack.clear();

                if (expression == null) {
                    System.out.println("Erro: crie uma expressão para convertê-la.");
                    continue;
                }

                for (int i = 0; i < expression.length(); i++) {
                    char c = expression.charAt(i);

                    if (Character.isAlphabetic(c)) {
                        output.enqueue(c);
                    } else if (c == '(') {
                        stack.push(c);
                    } else if (c == ')') {
                        while (!stack.isEmpty() && stack.topo() != '(') {
                            output.enqueue(stack.pop());
                        }
                        stack.pop();
                    } else if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        while (!stack.isEmpty() && priorityOperator(c, stack.topo()) <= 0) {
                            output.enqueue(stack.pop());
                        }
                        stack.push(c);
                    }
                }

                // Apresentação da nova expressão na tela
                while (!stack.isEmpty()) {
                    output.enqueue(stack.pop());
                }
                System.out.printf("Expressão: %s.\nNotação posfixa: %s.\n", expression, output);
            }
            // Cálculo da expressão
            else if (opt == 4) {
                if (expression == null) {
                    System.out.println("Erro! crie uma expressão para convertê-la.");
                    continue;
                } else if (output.isEmpty()) {
                    System.out.println("Erro! converta a expressão para a notação posfixa.");
                    continue;
                } else if (values.isEmpty()) {
                    System.out.println("Erro! acrescente os valores para realizar os cálculos.");
                    continue;
                }

                outputArray = new char[output.queueSize()];
                valuesArray = new float[values.queueSize()];
                int i = 0, j = 0;

                results.clear();

                while (!output.isEmpty()) {
                    char c = output.dequeue();
                    outputArray[i++] = c;
                    if (Character.isAlphabetic(c)) {
                        valuesArray[j++] = values.front();
                        results.push(values.dequeue());
                    }
                    else {
                        float operando1 = results.pop();
                        float operando2 = results.pop();
                        results.push(operation(operando1, operando2, c));
                    }
                }
                j = 0;
                // Preenchimento das filas "esvaziadas" após o cálculo e apresentação dos valores de cada variável
                output.clear();
                for (i = 0; i < outputArray.length; i++) {
                    output.enqueue(outputArray[i]);

                    // Apresentação dos valores de cada variável
                    if (Character.isAlphabetic(outputArray[i])) {
                        System.out.printf("Valor da variável \"%c\": %.2f.\n", outputArray[i], valuesArray[j]);
                        j++;
                    }
                }

                values.clear();
                for (i = 0; i < valuesArray.length; i++) {
                    values.enqueue(valuesArray[i]);
                }

                System.out.printf("Resultados:\nExpressão: %s.\nNotação posfixa: %s.\n", expression, output);
                System.out.printf("Resultado de acordo com os valores informados: %s.\n", results);
            }
        }
    }
}