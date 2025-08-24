    package CodigoPostal;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.TreeMap;

    /* Programa que analiza un archivo CSV que cuenta con codigos postales y sus asentamientos, este cuenta
       cuantos asentamientos le corresponden a cada codigo postla y los muestra ordenados de manera ascendente. */

    public class CodigoPostal {
        public static void main(String[] args) {

            String archivo = "src\\codigos_postales_hmo.csv"; //Ruta del archivo CSV
            BufferedReader lector = null; //Lector que leerá el archivo
            String linea = ""; //Variable temporal para almacenar las lineas del archivo

            HashMap<String, Integer> conteoA = new HashMap<>();  //HashMap que almacena clave = código postal y valor = núm de asentamientos

            try {
                lector = new BufferedReader(new FileReader(archivo)); //Abrir el archivo para lectura

                while ((linea = lector.readLine()) != null) { //Leer línea por línea

                    String[] fila = linea.split(",", -1); //Separar cada linea en columnas mediante las comas
                    //-1 para que no se ignoren columnas vacías

                    if (fila.length >= 2) { //Valida que se tengan al menos 2 columnas
                        String codigoPostal = fila[0].trim(); // Columna 0: Código Postal

                        conteoA.put(codigoPostal, conteoA.getOrDefault(codigoPostal, 0) + 1);  // Incrementar el contador del código postal
                    }
                }

                TreeMap<String, Integer> ordenarCP = new TreeMap<>(conteoA); //Usa TreeMap para ordenar por clave, en nuestro caso de menor a mayor

                for (Map.Entry<String, Integer> entrada : ordenarCP.entrySet()) {  //Recorre cada clave y valor del HashMap
                    System.out.println("Código postal: " + entrada.getKey() +
                            " - Número de asentamientos: " + entrada.getValue());
                }
                //Imprimir los resultados ordenados
                //getKey obtiene la clave, codigo postal, y getValue el valor, núm de asentamientos

            } catch (Exception e) {
                e.printStackTrace(); //Mostrar error si ocurre (Error en la lectura del archivo)
            } finally {
                try {
                    if (lector != null) {
                        lector.close(); //Cierra el archivo
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
