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
        if (coiso >= 40) {
            return 5;
        }
        return -1; // Caso inválido, mas não deve ocorrer
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Object> lista = new ArrayList<>();
        
        while (true) {
            System.out.print("Digite seu nome: ");
            String nome = sc.nextLine();
            if (nome.equals("parar")) {
                break;
            }
            lista.add(nome);
            
            System.out.print("Digite seu peso: ");
            String pesoStr = sc.nextLine();
            if (pesoStr.equals("parar")) {
                lista.remove(lista.size() - 1); // Remove o nome adicionado
                break;
            }
            lista.add(pesoStr);
            
            System.out.print("Digite sua altura: ");
            String alturaStr = sc.nextLine();
            if (alturaStr.equals("parar")) {
                // Remove os últimos dois elementos (peso e nome)
                lista.remove(lista.size() - 1); // Remove peso
                lista.remove(lista.size() - 1); // Remove nome
                break;
            }
            lista.add(alturaStr);
            
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);
            
            double imc = peso / (altura * altura);
            lista.add(imc);
        }
        
        // Agora, itera pela lista em grupos de 4 elementos
        for (int l = 0; l < lista.size(); l += 4) {
            String nome = (String) lista.get(l);
            double imcVal = (double) lista.get(l + 3);
            int qual_imc = testador(imcVal);
            System.out.println("O IMC de " + nome + " é " + tipos[qual_imc]);
        }
        
        sc.close();
    }
}
