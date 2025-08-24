package CodigoPostal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodigoPostalProf {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        BufferedReader br = null;

        try (FileReader fileReader = new FileReader("src//codigos_postales_hmo.csv")){
            br = new BufferedReader(fileReader);

            String linea;

            int contador = 1;
            int zip = 0; //Ultimo valor de codigo postal leido.
            while ((linea = br.readLine()) != null){
                String cp = linea.substring(0, 5);

                if (zip == Integer.parseInt(cp)){ //Comparar el ultimo valor que leido con el nuevo
                    contador++;
                } else {
                    System.out.printf("%d %d\n", zip, contador);
                    zip = Integer.parseInt(cp);
                    contador = 1;
                }

            }

        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
