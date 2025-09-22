import java.util.*;

public class CalculadoraIMC {
    
    private static String[] tipos = {"Baixo peso", "Peso normal", "Sobrepeso", "Obesidade grau 1", "Obesidade grau 2", "Obesidade grau 3"};
    
    private static int testador(double coiso) {
        if (coiso < 18.5) {
            return 0;
        }
        if (coiso >= 18.5 && coiso < 25) {
            return 1;
        }
        if (coiso >= 25 && coiso < 30) {
            return 2;
        }
        if (coiso >= 30 && coiso < 35) {
            return 3;
        }
        if (coiso >= 35 && coiso < 40) {
            return 4;
        }
        return 5; // Para >= 40
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Object> lista = new ArrayList<>();
        
        while (true) {
            System.out.print("Digite seu nome: ");
            String nome = sc.nextLine().trim(); // Adicionei trim() para remover espaços extras
            if (nome.equalsIgnoreCase("parar")) { // Ignore case para mais flexibilidade
                break;
            }
            lista.add(nome);
            
            System.out.print("Digite seu peso: ");
            String pesoStr = sc.nextLine().trim();
            if (pesoStr.equalsIgnoreCase("parar")) {
                if (!lista.isEmpty()) {
                    lista.remove(lista.size() - 1); // Remove o nome
                }
                break;
            }
            lista.add(pesoStr);
            
            System.out.print("Digite sua altura: ");
            String alturaStr = sc.nextLine().trim();
            if (alturaStr.equalsIgnoreCase("parar")) {
                // Remove os últimos dois elementos (altura não foi adicionada ainda, então peso e nome)
                if (lista.size() >= 2) {
                    lista.remove(lista.size() - 1); // Remove peso
                    lista.remove(lista.size() - 1); // Remove nome
                }
                break;
            }
            lista.add(alturaStr);
            
            double peso = 0;
            double altura = 0;
            boolean validInput = true;
            
            try {
                peso = Double.parseDouble(pesoStr);
                altura = Double.parseDouble(alturaStr);
                if (peso <= 0 || altura <= 0) {
                    throw new NumberFormatException("Valores devem ser positivos!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Peso e altura devem ser números válidos e positivos. Tentativa ignorada.");
                // Remove os últimos três elementos (nome, pesoStr, alturaStr)
                if (lista.size() >= 3) {
                    lista.remove(lista.size() - 1); // alturaStr
                    lista.remove(lista.size() - 1); // pesoStr
                    lista.remove(lista.size() - 1); // nome
                }
                validInput = false;
            }
            
            if (validInput) {
                double imc = peso / (altura * altura);
                lista.add(imc);
            }
        }
        
        // Imprime os resultados
        if (lista.size() >= 4) { // Garante que há pelo menos um registro completo
            for (int l = 0; l < lista.size(); l += 4) {
                if (l + 3 < lista.size()) { // Verifica se o grupo está completo
                    String nome = (String) lista.get(l);
                    double imcVal = (Double) lista.get(l + 3);
                    int qual_imc = testador(imcVal);
                    System.out.println("O IMC de " + nome + " é " + tipos[qual_imc]);
                }
            }
        } else {
            System.out.println("Nenhum dado válido foi processado.");
        }
        
        sc.close();
    }
}
